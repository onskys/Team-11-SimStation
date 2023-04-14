package simstation;

import mvc.*;

/* Class "StartCommand" Datalog
4/6/2023 - Owen Semersky: Created file
                          Added outline
4/9/2023 - Owen Semersky: Added method headers
4/9/2023 - Sanjana Jagarlapudi: continued implementation of execute method.
 */

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation sim = (Simulation) model;
        sim.start();
    }
}