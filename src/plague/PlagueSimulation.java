package plague;


import simstation.*;
import mvc.*;

import javax.swing.*;
import java.awt.*;

/* Class "PlagueSimulation" Datalog
4/12/2023 - Owen Semersky: Created file
4/16/2023 - Sanjana Jagarlapudi: Created Host Class, added construtor
                                    added update method and implemented it
                                    added setter and getter methods for infected field.

 */
class Host extends Agent {
    boolean infected;
    Color color;
    int virulence;
    int resistance;


    public Host() {
        super("Drunk");
        heading = Heading.random();
        infected = false;
        this.color = Color.GREEN;
        //Resistance and Virulence are random numbers between 0 and 100
        //Resistance is the immunity the Host has towards the plague
        //Virulence is the sevierity of the plague.
        resistance = Utilities.rng.nextInt(100) ;
        virulence = Utilities.rng.nextInt(100);
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
        this.color = Color.RED;
    }

    public void update() { //Fix to look better
        //If host is uninfected, then they do the drunk walk
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
        //Infecting proccess, add virulence functionality
        Host neighbor = (Host) world.getNeighbor(this, 10.0);
        //Trying to infect the neighbor based on their resistance and virulence
        if (neighbor != null && !neighbor.infected) {
            int infectedChance = this.resistance - this.virulence;
            int randomInfect = Utilities.rng.nextInt(100);
            if(randomInfect > infectedChance) {
                neighbor.setInfected(true);
            }
        }
    }
}
class PlagueSimulationFactory extends SimStationFactory {
    public Model makeModel() { return new PlagueSimulation(); }
    public String getTitle() { return "Plague";}
}
public class PlagueSimulation extends Simulation {
    public void populate() {
        for (int i = 0; i < 15; i++)
            addAgent(new Host());
    }
    public void showStats() {
        int numAgents = getAgents().size();
        int time = getClock();

        JFrame frame = new JFrame(""); //Finish stats
        JOptionPane.showMessageDialog(frame, "#Hosts = " + numAgents + "\nclock = " + time + "\n#infected = ");
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueSimulationFactory());
        panel.display();
    }
}
