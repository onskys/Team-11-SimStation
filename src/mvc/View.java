package mvc;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/* Class "View" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of View

 */
public class View extends JPanel implements PropertyChangeListener {
    public Model model;

    public View(Model model){
        this.model = model;
        setSize(500, 300);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setBackground(Color.WHITE);
    }

    public void setModel(Model model){
        this.model = model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){

        String event = evt.getPropertyName();

        if("New".equals(event) || "Open".equals(event)){
            this.model = (Model)evt.getNewValue();
            model.initSupport();
            repaint();
        }
    }
}