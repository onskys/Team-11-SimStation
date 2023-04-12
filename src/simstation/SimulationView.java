package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

/* Class "SimulationView" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added method headers and some basic code
4/11/2023 - Owen Semersky: Minor edits
4/12/2023 - Owen Semersky: Edits to paintComponent, drawing agents

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
}
