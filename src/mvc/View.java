package mvc;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/* Class "View" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of View
4/6/2023 - Owen Semersky: Added professor's version of View

 */
public class View extends JPanel implements PropertyChangeListener {

    protected Model model;

    public View(Model model) {
        super();
        this.model = model;
        //model.addPropertyChangeListener(this);
        // optional border around the view component
        setBorder(LineBorder.createGrayLineBorder());//.createBlackLineBorder());
    }

    public Model getModel() {return model; }

    // called by File/Open and File/New
    public void setModel(Model newModel) {
        this.model = newModel;
        if (newModel != null) {
            this.model.addPropertyChangeListener(this);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent arg0) { this.repaint(); }

}
