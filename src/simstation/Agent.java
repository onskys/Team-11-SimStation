package simstation;

import java.io.Serializable;
import java.util.Random;

/* Class "Agent" Datalog
4/4/2023 - Owen Semersky: Created File
                          Implemented Serializable and Runnable
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Added instance variables and headers
                          Added implementation for move method
4/9/2023 - Owen Semersky: Made edits to methods
                          Changed class and update method to be abstract
4/11/2023 - Owen Semersky: Implemented some methods, added some methods
4/12/2023 - Owen Semersky: Made edits to methods, added synchronization
 */

public abstract class Agent implements Serializable, Runnable {

    protected String name;
    protected Heading heading;
    private int xc;
    private int yc;
    private boolean suspended;
    private boolean stopped;
    protected Thread myThread;
    protected Simulation world;

    // Constructor
    public Agent (String name) {
        this.name = name;
        suspended = false;
        stopped = false;
        myThread = null;

        // Hard coded for now, should change to a random spot in the view.
        xc = 100;
        yc = 100;
    }

    // Run method, active agent movement or change.
    public void run() {
        myThread = Thread.currentThread();
        // myThread.start();
        onStart();
        while (!isStopped()) {
            try {
                // System.out.println("We have a problem");
                update();
                Thread.sleep(100); // Suggested speed is 20, too fast for now.
                checkSuspended();
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        onExit();
    }

    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Start method, starts running of the agent.
    public synchronized void start() {
        System.out.println("Starting");
        suspended = false;
        run();
    }

    // Suspend method, pauses running of the agent.
    public synchronized void suspend() {
        suspended = true;
    }

    // Resume method, resumes running of the agent.
    public synchronized void resume() {
        // suspended = false;
        notify();
    }

    // Stop method, stops all activity of the agent.
    public synchronized void stop() {
        stopped = true;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    // Update method, updates current qualities of agent, such as position.
    public abstract void update();

    // Moves the Agent in a direction depending on their heading. 8 cases.
    // Still requires wrapping functionality.
    public void move(int steps) {
        // System.out.println("We're moving!");
        if (heading == Heading.NORTH) {
            yc = yc - steps;
        }
        else if (heading == Heading.NORTHEAST) {
            yc = yc - steps;
            xc = xc + steps;
        }
        else if (heading == Heading.EAST) {
            xc = xc + steps;
        }
        else if (heading == Heading.SOUTHEAST) {
            yc = yc + steps;
            xc = xc + steps;
        }
        else if (heading == Heading.SOUTH) {
            yc = yc + steps;
        }
        else if (heading == Heading.SOUTHWEST) {
            yc = yc + steps;
            xc = xc - steps;
        }
        else if (heading == Heading.WEST) {
            xc = xc - steps;
        }
        else if (heading == Heading.NORTHWEST) {
            yc = yc - steps;
            xc = xc - steps;
        }
        world.changed();
        // notify();
    }

    public String getName() {
        return name;
    }

    public synchronized String toString() {
        String result = name;
        if (stopped) result += "(stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }

    public void setWorld(Simulation s) {
        this.world = s;
    }

    public int getX() {
        return xc;
    }

    public int getY() {
        return yc;
    }

    public void onStart() {}

    public void onExit() {}

    public void onInterrupted() {}
}