package simstation;

import mvc.*;
import java.util.*;

/* Class "Simulation" Datalog
4/4/2023 - Owen Semersky: Created File
                          Added mvc import and Model extension, added given code
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Made edits to some methods.


 */

public class Simulation extends Model {

    private Timer timer;
    private int clock = 0; // clock: int = 0

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

    // Starts the simulation.
    // start()

    // Pauses the simulation.
    // suspend()

    // Resumes the simulation.
    // resume()

    // Stops the simulation entirely.
    // stop()

    // Gets a nearby neighboring agent in the simulation.
    public Agent getNeighbor(Agent a, Double radius) {
        // Code here
        return null; // Returns nearby agent
    }

    // Populate is empty, specified in subclasses.
    public void populate() {

    }

    // Adds an agent to this simulation.
    // addAgent() ???
}
