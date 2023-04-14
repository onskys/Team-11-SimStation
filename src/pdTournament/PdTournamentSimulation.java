package pdTournament;

import simstation.*;
import mvc.*;
import javax.swing.*;

/* Class "PdTournamentSimulation" Datalog
4/12/2023 - Owen Semersky: Created file
4/13/2023 - Owen Semersky: Outlined classes/methods
                           Implemented several methods
                           Added strategies
4/14/2023 - Owen Semersky: Edited showStats method
                           Scrapped rng for even fourths
                           Various improvements

 */

public class PdTournamentSimulation extends Simulation {
    public void populate() {
        int numPrisoners = 20; // Please change only this num to change num of prisoners
                               // This number should be divisible by 4 for best functionality
        int fourth = numPrisoners / 4;

        for(int i = 0; i < fourth; i++) {
            addAgent(new Prisoner(new Cooperate()));
            Prisoner p = (Prisoner) getAgents().get(i);
            p.strategy.setClassification("Cooperate");
        }
        for (int i = fourth; i < 2 * fourth; i++) {
            addAgent(new Prisoner(new Cheat()));
            Prisoner p = (Prisoner) getAgents().get(i);
            p.strategy.setClassification("Cheat");
        }
        for (int i = 2 * fourth; i < 3 * fourth; i++) {
            addAgent(new Prisoner(new RandomlyCooperate()));
            Prisoner p = (Prisoner) getAgents().get(i);
            p.strategy.setClassification("Random");
        }
        for (int i = 3 * fourth; i < numPrisoners; i++) {
            addAgent(new Prisoner(new Tit4Tat()));
            Prisoner p = (Prisoner) getAgents().get(i);
            p.strategy.setClassification("Tit4Tat");
        }


        for (Agent a : getAgents()) {
            Prisoner p = (Prisoner) a;
            System.out.println(p.strategy);
        }
    }

    public void showStats() {
        int fourths = getAgents().size() / 4;

        int coopFitness = 0;
        int cheatFitness = 0;
        int randomFitness = 0;
        int tit4tatFitness = 0;

        for (Agent a : getAgents()) {
            Prisoner p = (Prisoner) a;
            switch (p.strategy.classification) {
                case "Cooperate":
                    coopFitness += p.getFitness();
                case "Cheat":
                    cheatFitness += p.getFitness();
                case "Random":
                    randomFitness += p.getFitness();
                default:
                    tit4tatFitness += p.getFitness();
            }
        }

        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "Avg Fitness:" + "\nCoop: " + coopFitness / fourths
                                      + "\nCheat: " + cheatFitness / fourths + "\nRandom: "
                                      + randomFitness / fourths + "\nTit4Tat: " + tit4tatFitness / fourths);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }
}

class Prisoner extends Agent {
    private int fitness;
    private boolean partnerCheated;
    Strategy strategy;
    public Prisoner(Strategy strat) {
        super("Prisoner");
        heading = Heading.random();
        fitness = 0;
        partnerCheated = false;

        strategy = strat;
        strategy.setPrisoner(this);
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Prisoner neighbor = (Prisoner) world.getNeighbor(this, 15.0);

        if (cooperate() && !partnerCheated) {
            // System.out.println("Adding 3");
            updateFitness(3);
            neighbor.updateFitness(3);
        }
        else if (cooperate() && partnerCheated) {
            // System.out.println("Adding 0 and 5");
            neighbor.updateFitness(5);
            partnerCheated = true;
        }
        else if (!cooperate() && !partnerCheated) {
            // System.out.println("Adding 5 and 0");
            updateFitness(5);
        }
        else {
            // System.out.println("Adding 1 and 1");
            updateFitness(1);
            neighbor.updateFitness(1);
            partnerCheated = true;
        }

        // System.out.println("Fitness: " + fitness);
    }

    public void updateFitness(int amount) {
        fitness += amount;
    }

    public boolean cooperate() {
       return strategy.cooperate();
    }

    public boolean getPreviousCheat() {
        return partnerCheated;
    }

    public int getFitness() {
        return fitness;
    }
}

abstract class Strategy {
    Prisoner myPrisoner;
    String classification;
    abstract boolean cooperate();
    public void setPrisoner(Prisoner p) {
        myPrisoner = p;
    }

    public void setClassification(String name) {
        classification = name;
    }
}

class Cooperate extends Strategy {
    public boolean cooperate() {
        return true;
    }
}

class Cheat extends Strategy {
    public boolean cooperate() {
        return false;
    }
}

class RandomlyCooperate extends Strategy {
    public boolean cooperate() {
        int cheat = Utilities.rng.nextInt(2);

        // If cheat == 0, cooperate
        // If cheat == 1, do not cooperate
        return cheat == 0;
    }
}

class Tit4Tat extends Strategy {
    public boolean cooperate() {
        // If previous did not cheat, cooperate
        // If previous did cheat, do not cooperate
        return !myPrisoner.getPreviousCheat();
    }
}

class PrisonerFactory extends SimStationFactory {
    public Model makeModel() {return new PdTournamentSimulation(); }
    public String getTitle() {return "Prisoner's Dilemma Tourney"; }

}
