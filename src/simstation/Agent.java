package simstation;

import java.io.Serializable;

/* Class "Agent" Datalog
4/4/2023 - Owen Semersky: Created File
                          Implemented Serializable and Runnable
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Added instance variables and implemented some methods.

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
    // start()
    
    // Suspend method, pauses running of the agent.
    // suspend()
    
    // Resume method, resumes running of the agent.
    // resume()
    
    // Stop method, stops all activity of the agent.
    // stop()
    
    // Update method, updates current qualities of agent, such as position.
    // update()

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
