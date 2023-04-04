package simstation;

import mvc.*;
import java.util.*;

/* Class "Simulation" Datalog
4/4/2023 - Owen Semersky: Created File
                          Added mvc import and Model extension, added given code
                          Added outline (variables and methods)


 */

public class Simulation extends Model {

    private Timer timer;
    private int clock; // clock: int = 0

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

    // start()
    // suspend()
    // resume()
    // stop()
    // getNeighbor(a: Agent, radius: Double): Agent
    // populate()
    // addAgent() ???
}
