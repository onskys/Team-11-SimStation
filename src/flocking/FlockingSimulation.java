package flocking;

import simstation.*;
import mvc.*;
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
        speed = random.nextInt(9) + 1; // Random speed between 1 and 10
    }

    public void update() {
        // Find neighbor and copy heading and speed
        move(speed); // Move at certain speed
    }
}

class FlockingFactory extends SimStationFactory {
    public Model makeModel() {return new FlockingSimulation();}
    public String getTitle() {return "Flocking";}
}