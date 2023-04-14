package pdTournament;

import simstation.*;
import mvc.*;
import javax.swing.*;

/* Class "PdTournamentSimulation" Datalog
4/12/2023 - Owen Semersky: Created file
4/13/2023 - Owen Semersky: Outlined classes/methods

 */

public class PdTournamentSimulation extends Simulation {
    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Prisoner());
    }

    public void showStats() {
        // Code

        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "etc");
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }
}

class Prisoner extends Agent {
    private int fitness;
    private boolean partnerCheated;
    public Prisoner() {
        super("Prisoner");
        heading = Heading.random();
        fitness = 0;
    }

    public void update() {
        int fitnessToAdd;

        fitnessToAdd = 0; // Temporary

        Prisoner neighbor = (Prisoner) world.getNeighbor(this, 15.0);
        // Code

        fitness = fitness + fitnessToAdd;
    }

    public boolean cooperate() {
       return false;
    }
}

class PrisonerFactory extends SimStationFactory {
    public Model makeModel() {return new PdTournamentSimulation(); }
    public String getTitle() {return "Prisoner's Dilemma Tourney"; }

}
