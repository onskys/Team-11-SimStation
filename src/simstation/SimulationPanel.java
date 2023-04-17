package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

/* Class "SimulationPanel" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added buttons and main
4/9/2023 - Owen Semersky: Minor edits to use controlPanel
 */

public class SimulationPanel extends AppPanel {
    private JButton resume;
    private JButton stop;
    private JButton start;
    private JButton stats;
    private JButton suspend;

    public SimulationPanel(AppFactory factory) {
        super(factory);
        Simulation s = (Simulation) model;
        s.addPropertyChangeListener(this);

        Dimension buttonSize = new Dimension(40, 20);
        controlPanel.setLayout(new GridLayout(5, 1, 5, 5));

        resume = new JButton("Resume");
        resume.addActionListener(this);
        resume.setPreferredSize(buttonSize);
        controlPanel.add(resume);

        stop = new JButton("Stop");
        stop.addActionListener(this);
        stop.setPreferredSize(buttonSize);
        controlPanel.add(stop);

        start = new JButton("Start");
        start.addActionListener(this);
        start.setPreferredSize(buttonSize);
        controlPanel.add(start);

        stats = new JButton("Stats");
        stats.addActionListener(this);
        stats.setPreferredSize(buttonSize);
        controlPanel.add(stats);

        suspend = new JButton("Suspend");
        suspend.addActionListener(this);
        suspend.setPreferredSize(buttonSize);
        controlPanel.add(suspend);
    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
