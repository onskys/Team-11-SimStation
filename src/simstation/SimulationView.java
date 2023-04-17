package simstation;

import mvc.*;
import randomWalks.RandomWalkSimulation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

/* Class "SimulationView" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added method headers and some basic code
4/11/2023 - Owen Semersky: Minor edits
4/12/2023 - Owen Semersky: Edits to paintComponent, drawing agents
4/15/2023 - Owen Semersky: Added getSim method
 */

public class SimulationView extends View {

    Simulation sim;

    public SimulationView(Simulation s) {
        super(s);
        sim = s;
        sim.populate();
    }

    public void paintComponent(Graphics gc) { // Unsure if needed
        super.paintComponent(gc);
        Graphics2D gc2d = (Graphics2D) gc;
        for (Agent a : sim.getAgents()) {
            gc2d.fillRect(a.getX(), a.getY(), 5, 5);
        }
    }
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        repaint();
        System.out.print("check");

        if (evt.getPropertyName() == null) {
            repaint();
            return;
        }

        if (evt.getPropertyName().equals("New") || evt.getPropertyName().equals("Open")) {
            removeAll();
            Simulation s = new RandomWalkSimulation();
            setSim(s);
//            sim.getAgents().clear();
//
//            sim.populate();
//            sim.start();
        }
    }

    public Simulation getSim() {
        return sim;
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
        sim.populate();
        sim.start();
    }
}
