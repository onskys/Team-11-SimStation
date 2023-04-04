package mvc;

/* Class "Command" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of Command

 */

public abstract class Command {

    public Model model;

    public Command(Model model){
        this.model = model;
    }

    public abstract void execute();
}