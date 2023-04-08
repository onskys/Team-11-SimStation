package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

/* Class "SimulationPanel" Datalog
4/4/2023 - Owen Semersky: Created file
4/7/2023 - Owen Semersky: Added buttons and main

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

        JPanel p = new JPanel();
        Dimension buttonSize = new Dimension(40, 20);
        p.setLayout(new GridLayout(5, 1, 5, 5));

        resume = new JButton("Resume");
        resume.addActionListener(this);
        resume.setPreferredSize(buttonSize);
        p.add(resume);
        add(p);

        stop = new JButton("Stop");
        stop.addActionListener(this);
        stop.setPreferredSize(buttonSize);
        p.add(stop);
        add(p);

        start = new JButton("Start");
        start.addActionListener(this);
        start.setPreferredSize(buttonSize);
        p.add(start);
        add(p);

        stats = new JButton("Stats");
        stats.addActionListener(this);
        stats.setPreferredSize(buttonSize);
        p.add(stats);
        add(p);

        suspend = new JButton("Suspend");
        suspend.addActionListener(this);
        suspend.setPreferredSize(buttonSize);
        p.add(suspend);
        add(p);

        controlPanel.add(p);
    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
