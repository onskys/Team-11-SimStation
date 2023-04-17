package mvc;

import java.awt.*;
import java.beans.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/* Class "AppPanel" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of AppPanel
4/6/2023 - Owen Semersky: Added professor's version of AppPanel
4/16/2023 - Owen Semersky: Major edits to actionPerformed
                           Added some of Sanjana's version of AppPanel
 */

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        super();
        this.factory = factory;
        model = factory.makeModel();
        view = factory.makeView(model);
        view.setBackground((Color.GRAY));

        controlPanel = new JPanel();
        controlPanel.setBackground((Color.PINK));
        setLayout(new GridLayout(1, 2));
        add(controlPanel);
        add(view);

        if (model != null) {
            model.addPropertyChangeListener(this);
            model.addPropertyChangeListener(view);
        }

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() { frame.setVisible(true); }

    public void propertyChange(PropertyChangeEvent evt) {
        /* override in extensions if needed */
    }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.removePropertyChangeListener(this);
        this.model = newModel;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();

        try {
            switch (cmmd) {
                case "Save":
                    Utilities.save(model, false);
                    break;
                case "Save As":
                    Utilities.save(model, true);
                    break;
                case "Open":
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        Model oldModel = model;
                        model.removePropertyChangeListener(view);
                        model = (Model) is.readObject();
                        model.initSupport();
                        setModel(model);
                        model.addPropertyChangeListener(view);
                        model.firePropertyChange("Open", oldModel, model);
                        is.close();
                    }
                    break;
                case "New":
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        Model oldModel = model;
                        model.removePropertyChangeListener(view);
                        model = factory.makeModel();
                        setModel(model); // set model
                        model.addPropertyChangeListener(view);
                        model.initSupport();
                        model.firePropertyChange("New", oldModel, model);
                    }
                    break;
                case "Quit":
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        System.exit(1);
                    }
                    break;
                case "About":
                    Utilities.inform(factory.about());
                    break;
                case "Help":
                    Utilities.inform(factory.getHelp());
                    break;
                default:
                    Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
                    command.execute();
            }

        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}
