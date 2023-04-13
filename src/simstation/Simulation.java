package simstation;

import mvc.*;
import java.util.*;


/* Class "Simulation" Datalog
4/4/2023 - Owen Semersky: Created File
                          Added mvc import and Model extension, added given code
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Made edits to some methods.
                          Added method headers
4/12/2023 - Sanjana Jagarlapudi: Implemented getNeighbor method
            Added "agentList" field and implemented addAgent method


 */

public class Simulation extends Model {

    private Timer timer;
    ArrayList<Agent> agentList;
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
    public void start() {
        // Call agent start methods
    }

    // Pauses the simulation.
    public void suspend() {
        // Call agent suspend methods
    }

    // Resumes the simulation.
    public void resume() {
        // Call agent resume methods
    }

    // Stops the simulation entirely.
    public void stop() {
        // Call agent stop methods
    }

    // Gets a nearby neighboring agent in the simulation.
    public Agent getNeighbor(Agent a, Double radius) {
        // Code here
        boolean done = false;
        while(!done){
            int index = Utilities.rng.nextInt(1, agentList.size());
            Agent randAgent = agentList.get(index);
            double distance = Math.sqrt(Math.pow((randAgent.getXc() - a.getXc()), 2) +
                    Math.pow((randAgent.getYc() - a.getYc()), 2));
            if(distance <= 10){
                return randAgent;
            }
        }
        return null;
    }

    // Populate is empty, specified in subclasses.
    public void populate() {

    }

    public void addAgent(Agent a){
        agentList.add(a);
    }
}
