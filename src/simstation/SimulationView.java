package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

/* Class "SimulationView" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added method headers and some basic code

 */

public class SimulationView extends View {

    Simulation sim;
    
    public SimulationView(Simulation s) {
        super(s);
        sim = s;
        // Continued
    }
    
    public void paintComponent(Graphics gc) { // Unsure if needed
        
    }
}
