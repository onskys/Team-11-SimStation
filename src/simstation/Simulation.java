package simstation;

import mvc.*;
import java.io.Serializable;
import java.util.*;

/* Class "Simulation" Datalog
4/4/2023 - Owen Semersky: Created File
                          Added mvc import and Model extension, added given code
                          Added outline (variables and methods)
4/7/2023 - Owen Semersky: Made edits to some methods
                          Added method headers
4/11/2023 - Owen Semersky: Implemented some methods
4/12/2023 - Owen Semersky: Minor edits
4/12/2023 - Sanjana Jagarlapudi: Implemented getNeighbor method
4/15/2023 - Owen Semersky: getNeighbor bug fix
 */

public class Simulation extends Model {

    transient private Timer timer;
    private ArrayList<Agent> agents;
    private int clock; // clock: int = 0
    private boolean hasStarted;

    public Simulation() {
        agents = new ArrayList<Agent>();
        clock = 0;
    }

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
            changed();
        }
    }

    // Starts the simulation.
    public void start() {
        System.out.println("Simulation Starting");
        hasStarted = true;
        startTimer();
        for (Agent a : agents) {
            // System.out.println("Agent exists");
            Thread thread = new Thread(a);
            thread.start();
        }
    }

    // Pauses the simulation.
    public void suspend() {
        System.out.println("Simulation Suspended");
        for (Agent a : agents) {
            a.suspend();
        }
    }

    // Resumes the simulation.
    public void resume() {
        System.out.println("Simulation Resuming");
        for (Agent a : agents) {
            a.resume();
        }
    }

    // Stops the simulation entirely.
    public void stop() {
        stopTimer();
        System.out.println("Simulation Stopping");
        for (Agent a : agents) {
            a.stop();
        }
    }

    // Gets a nearby neighboring agent in the simulation.
    public Agent getNeighbor(Agent a, Double radius) {
        boolean done = false;
        while(!done){
            int index = Utilities.rng.nextInt(agents.size());
            Agent randAgent = agents.get(index);
            double distance = Math.sqrt(Math.pow((randAgent.getX() - a.getX()), 2) +
                    Math.pow((randAgent.getY() - a.getY()), 2));
            if(distance <= radius){
                return randAgent;
            }
        }
        return null;
    }

    // Populate is empty, specified in subclasses.
    public void populate() {}

    // Adds an agent to this simulation.
    public void addAgent(Agent a) {
        a.setWorld(this);
        agents.add(a);
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> newAgents) {
        agents = newAgents;
    }

    // Specific stats specified in customizations.
    public void showStats() {}

    public int getClock() {
        return clock;
    }

    public void setStarted() {hasStarted = true; }

    public boolean hasStarted() {
        return hasStarted;
    }

    public void clearAgents() {
        agents = new ArrayList<Agent>();
    }
}