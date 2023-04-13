package simstation;

import mvc.*;

/* Class "SuspendCommand" Datalog
4/6/2023 - Owen Semersky: Created file
                          Added outline
4/9/2023 - Owen Semersky: Added method headers
4/11/2023 - Owen Semersky: Made edits to execute

 */

public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
        // Continued
    }

    public void execute() {
        Simulation sim = (Simulation) model;
        sim.suspend();
    }
}
