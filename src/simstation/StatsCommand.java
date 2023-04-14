package simstation;

import mvc.*;

/* Class "StatsCommand" Datalog
4/6/2023 - Owen Semersky: Created file
                          Added outline
4/9/2023 - Owen Semersky: Added method headers
4/13/2023 - Owen Semersky: Added to execute method

 */

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation sim = (Simulation) model;
        sim.showStats();
    }
}
