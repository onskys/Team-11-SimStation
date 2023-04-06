package mvc;

/* Class "Command" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of Command
4/6/2023 - Owen Semersky: Added professor's version of Command

 */

public abstract class Command {
    protected Model model;

    public Command(Model model) {
        super();
        this.model = model;
    }

    public abstract void execute() throws Exception ;

}
