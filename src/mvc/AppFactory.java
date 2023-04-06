package mvc;

/* Class "AppFactory" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of AppFactory
4/6/2023 - Owen Semersky: Added professor's version of AppFactory

 */

public interface AppFactory {
    public Model makeModel();
    public View makeView(Model model);
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type, Object source);
    public String getTitle();
    public String[] getHelp();
    public String about();
}
