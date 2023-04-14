package simstation;

import mvc.*;

/* Class "SimStaionFactory" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added methods, may need edits
 */

public class SimStationFactory implements AppFactory {
    public Model makeModel() {return new Simulation(); }
    public View makeView(Model m) {
        return new SimulationView((Simulation) m);
    }
    public String[] getEditCommands() {return new String[] {"Resume", "Start", "Stats", "Stop", "Suspend"}; }
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type == "Resume") {
            return new ResumeCommand(model);
        }
        else if (type == "Start") {
            return new StartCommand(model);
        }
        else if (type == "Stats") {
            return new StatsCommand(model);
        }
        else if (type == "Stop") {
            return new StopCommand(model);
        }
        else if (type == "Suspend") {
            return new SuspendCommand(model);
        }
        return null;
    }
    public String getTitle() {return "Simulation"; }
    public String[] getHelp() {
        return new String[] {
                "Start: Starts the simulation.",
                "Suspend: Pauses the simulation.",
                "Resume: Resumes the simulation after pausing.",
                "Stop: Ends/stops the simulation.",
                "Stats: Shows the current stats for the simulation."
        };
    }

    public String about() {
        return "Owen Semersky, Sanjana Jagarlapudi" + "\n2023. All rights reserved.";
    }

}