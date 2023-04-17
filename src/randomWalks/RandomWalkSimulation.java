package randomWalks;

import mvc.*;
import simstation.*;
import javax.swing.*;


/* Class "RandomWalkSimulation" Datalog
4/4/2023 - Owen Semersky: Created file
                          Added given code
4/13/2023 - Owen Semersky: Added stats

 */

class Drunk extends Agent {

    public Drunk() {
        super("Drunk");
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

}


class RandomWalkFactory extends SimStationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}

public class RandomWalkSimulation extends Simulation {

    // @Override
    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Drunk());
    }

    public void showStats() {
        int numAgents = getAgents().size();
        int time = getClock();

        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "#agents = " + numAgents + "\nclock = " + time);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
