package mvc;

import javax.swing.*;
import java.awt.event.WindowEvent;

/* Class "SafeFrame" Datalog

4/4/2023 - Owen Semersky: Created file
                          Imported provided code

 */

public class SafeFrame extends JFrame {

    protected void processWindowEvent(WindowEvent ev) {
        super.processWindowEvent(ev);
        if(ev.getID() == WindowEvent.WINDOW_CLOSING) {
            if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                System.exit(0);
            }
        }
    }

    public SafeFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}