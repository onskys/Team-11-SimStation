package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

/* Class "SimulationView" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added method headers and some basic code
4/11/2023 - Owen Semersky: Minor edits

 */

public class SimulationView extends View {

    Simulation sim;

    public SimulationView(Simulation s) {
        super(s);
        sim = s;
        sim.populate();
        // Continued
    }

    public void paintComponent(Graphics gc) { // Unsure if needed
        super.paintComponent(gc);
        for (Agent a : sim.getAgents()) {
            // Draw agent?
        }
    }
}
