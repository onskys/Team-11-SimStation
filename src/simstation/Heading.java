package simstation;

import java.util.*;

/* Class "Heading" Datalog
4/4/2023 - Owen Semersky: Created file
                          Added headings and random method

 */

public enum Heading {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    private static final Random rng = new Random();
    public static Heading random() {
        Heading[] headings = values();
        return headings[rng.nextInt(headings.length)];
    }
}