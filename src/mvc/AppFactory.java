package mvc;

/* Class "AppFactory" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of AppFactory

 */

public interface AppFactory {
    Model makeModel();

    View makeView(Model m);

    String getTitle();

    String[] getHelp();

    String about();

    String[] getEditCommands();

    Command makeEditCommand(Model model, String name, Object source);
}