package mvc;

import java.awt.*;
import java.beans.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* Class "AppPanel" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of AppPanel

 */

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener {

    protected Model model;
    protected AppFactory factory;
    protected View views;
    protected ControlPanel controls;
    private SafeFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        super();
        this.factory = factory;
        model = factory.makeModel();
        views = factory.makeView(model);
        if (model != null) model.addPropertyChangeListener(this);
        views.setBackground(Color.LIGHT_GRAY);

        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(views);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        cp.add(this);
    }

    public void addControl(JComponent c){
        controls.add(c);
    }
/*
    public void addView(View view) {
        views.add(view);
    }
    @Override
    public Component add(Component c) {
        if (c instanceof View) addView((View) c);
        return super.add(c);
    }
 */

    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    public Model getModel() {
        return model;
    }


    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.removePropertyChangeListener(this);
        this.model = newModel;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        //for(View view: views) view.setModel(this.model);
        //alternatively: this.model.copy(model);
    }


    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        Command cmmdObject = factory.makeEditCommand(model, cmmd, null);
        try {
            switch (cmmd) {

                case "Save": {
                    String fName = Utilities.getFileName((String) null, true);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Save As": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        model = (Model) is.readObject();
                        //view.setModel(model);
                        is.close();
                    }
                    break;
                }

                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        model = new Model();
                        views.setModel(model);
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(factory.about());
                    break;
                }

                case "Help": {
                    String[] cmmds = factory.getHelp();
                    Utilities.inform(cmmds);
                    break;
                }

                default: {
                    factory.makeEditCommand(model, cmmd, this).execute();
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex); // all error handling done here!
        }

//        try{
//            cmmdObject.execute();
//        } catch (Exception ex){
//            Utilities.error(ex);
//        }
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.LIGHT_GRAY);
            setLayout(new GridLayout(4, 2));
            JPanel p = new JPanel();
            Dimension buttonSize = new Dimension(50, 25);

        }
    }
}