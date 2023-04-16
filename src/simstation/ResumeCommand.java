package simstation;

import mvc.*;

/* Class "ResumeCommand" Datalog
4/6/2023 - Owen Semersky: Created file
                          Added outline
4/9/2023 - Owen Semersky: Added method headers
4/12/2023 - Sanjana Jagarlapudi: implemented body of execute method
 */

public class ResumeCommand extends Command {
    public ResumeCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation sim = (Simulation) model;
        sim.resume();
    }

}

}

