package simstation;

import java.io.Serializable;

/* Class "Agent" Datalog
4/4/2023 - Owen Semersky: Created File
                          Implemented Serializable and Runnable
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Added instance variables and headers
                          Added implementation for move method
4/9/2023 - Owen Semersky: Made edits to methods
                          Changed class and update method to be abstract
 */

public abstract class Agent implements Serializable, Runnable {

    private String name;
    protected Heading heading;
    private int xc;
    private int yc;
    private boolean suspended = false;
    private boolean stopped = false;
    private Thread myThread;

    // Run method, active agent movement or change.
    public void run() {
        while (suspended == false && stopped == false) {
            update();
        }
    }

    // Start method, starts running of the agent.
    public void start() {
        suspended = false;
        run();
    }

    // Suspend method, pauses running of the agent.
    public void suspend() {
        suspended = true;
    }

    // Resume method, resumes running of the agent.
    public void resume() {
        suspended = false;
        run();
    }

    // Stop method, stops all activity of the agent.
    public void stop() {
        stopped = true;
    }

    // Update method, updates current qualities of agent, such as position.
    public abstract void update();

    // Moves the Agent in a direction depending on their heading. 8 cases.
    public void move(int steps) {
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
    }
}
