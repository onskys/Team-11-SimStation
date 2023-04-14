package flocking;

import simstation.*;
import mvc.*;

import javax.swing.*;
import java.util.Random;

/* Class "FlockingSimulation" Datalog
4/12/2023 - Owen Semersky: Created file
                           Added inner classes and some code

 */

public class FlockingSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Bird());
    }

    public void showStats() {
        int speed1 = 0;
        int speed2 = 0;
        int speed3 = 0;
        int speed4 = 0;
        int speed5 = 0;

        for (Agent a : getAgents()) {
            Bird b = (Bird) a;

            if (b.getSpeed() == 1) {
                speed1++;
            }
            else if (b.getSpeed() == 2) {
                speed2++;
            }
            else if (b.getSpeed() == 3) {
                speed3++;
            }
            else if (b.getSpeed() == 4) {
                speed4++;
            }
            else if (b.getSpeed() == 5) {
                speed5++;
            }
        }

        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "#birds @ speed 1 = " + speed1 + "\n3birds @ speed 2 = "
                                      + speed2 + "\n#birds @ speed 3 = " + speed3 + "\n#birds @ speed 4 = "
                                      + speed4 + "\n#birds @ speed 5 = " + speed5);

    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}

class Bird extends Agent
{
    int speed;
    public Bird() {
        super("Bird");
        heading = Heading.random(); // Random heading

        Random random = new Random();
        speed = random.nextInt(4) + 1; // Random speed between 1 and 10
    }

    public void update() {
        Bird neighbor = (Bird) world.getNeighbor(this, 10.0);

        if (neighbor != null) {
            speed = neighbor.getSpeed();
            heading = neighbor.getHeading();
        }

        move(speed); // Move at certain speed with new heading
    }

    public int getSpeed() {
        return speed;
    }
}

class FlockingFactory extends SimStationFactory {
    public Model makeModel() {return new FlockingSimulation();}
    public String getTitle() {return "Flocking";}
}