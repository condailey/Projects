// Author: K. Walsh
// Date: 21 Sept, 2021
//
// Makes a 1980s-themed ornament using a laser engraver and Laser library. The
// design has random-sized triangles etched at random x, y locations, and it
// cuts a large hexagon (six-sided polygon) around the entire design to make a
// nice shape.
public class Eighties {

    public static void main(String[] args) {
        Laser.PLAYBACK_SPEED = 20; // this makes the simulated animation go quick!

        // First, make a pattern of 60 random triangles, all pointing down.
        Laser.setSpeed(10);
        Laser.setPower(0);
        for (int i = 0; i < 60; i++) {
            double x = StdRandom.uniform(0.0, 50.0); // random x and y coordinates between 0 and 50.0
            double y = StdRandom.uniform(0.0, 50.0);
            double s = StdRandom.uniform(2.0, 10.0); // size between 2.0 and 10.0
            System.out.println("making triangle #" + i +" at " + x + ", " + y + " with size " + s);
            Laser.goTo(x, y, -60); // angle=-60 points towards the south-east
            Laser.setPower(3);
            for (int j = 0; j < 3; j++) {
                Laser.move(s);
                Laser.turn(120); // turn 120 degrees
            }
            Laser.setPower(0);
        }

        // Lastly, cut out a six-sided border around the design.
        
        // Turn the laser off, and move to the right edge facing north.
        Laser.setSpeed(10);
        Laser.setPower(0);
        Laser.goTo(50, 25, 90); // angle=90 is north
        Laser.turn(30); // then turn 30 degrees counter-clockwise to get started

        // Now do the six sides
        Laser.setSpeed(3);
        Laser.setPower(5);
        for (int i = 0; i < 6; i++) {
            Laser.move(25);
            Laser.turn(60);
        }

        // Move the laser out of the way.
        Laser.setPower(0);
        Laser.goTo(0, 0, 0);

    } // end main

} // end Eighties
