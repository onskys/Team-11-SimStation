package simstation;

import java.io.Serializable;

/* Class "Agent" Datalog
4/4/2023 - Owen Semersky: Created File
                          Implemented Serializable and Runnable
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Added instance variables and headers
                          Added implementation for move method
 */

public class Agent implements Serializable, Runnable {

    // name: String
    private String name;
    // heading: Heading
    private Heading heading;
    // xc: int
    private int xc;
    // yc: int
    private int yc;
    // suspended: boolean = false
    private boolean suspended = false;
    // stopped: boolean = false
    private boolean stopped = false;
    // myThread: Thread
    private Thread myThread;

    // Run method, active agent movement or change.
    public void run() {

    }

    // Start method, starts running of the agent.
    public void start() {
        
    }

    // Suspend method, pauses running of the agent.
    public void suspend() {
        suspended = true;
        // Continued
    }

    // Resume method, resumes running of the agent.
    public void resume() {
        suspended = false;
        // Continued
    }

    // Stop method, stops all activity of the agent.
    public void stop() {
        stopped = true;
        // Continued
    }

    // Update method, updates current qualities of agent, such as position.
    public void update() {
        
    }

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
