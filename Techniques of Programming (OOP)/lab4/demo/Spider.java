// Author: K. Walsh
// Date: 21 Sept, 2021
//
// Makes a spider-web themed ornament using a laser engraver and Laser library.
// The design has a spider web in the center, with central spokes radiating
// outwards and a spiral-shaped web, and it cuts an outer circle around the
// entire design to the final ornament is round.

public class Spider {

    public static void main(String[] args) {
        Laser.PLAYBACK_SPEED = 20; // this makes the simulated animation go quick!
        
        Laser.setSpeed(10);

        // Draw six outward rays from the center.
        for (int i = 0; i < 6; i++) {
            Laser.setPower(3); // medium power
            Laser.move(25); // drive forward
            Laser.turn(180); // turn around
            Laser.setPower(0); // laser off
            Laser.move(25); // drive forward, back to where we were
            Laser.turn(120); // turn most of the way around
        }

        // Next, go to a new starting location for the "web".
        Laser.goTo(25, 22, 0); // a little below the center, facing right

        // Make a hex spiral by repeatedly: drive a little distance, turn 60
        // degrees, drive a short distance (but a little more than before), turn 60, etc.
        // etc. We move at high speed medium power to burn a nice line.
        // The starting distance and incremeent amount were chosen by trial and
        // error.
        Laser.setSpeed(10);
        Laser.setPower(5);
        double d = 1; // 1 mm as the starting distance
        for (int i = 0; i < 25; i++) {
            Laser.move(d);
            d += 1; // increase distance
            Laser.turn(60); // 60 degree counter-clockwise
        }

        // Move quickly to the left center, facing north, with laser off.
        Laser.setSpeed(10);
        Laser.setPower(0);
        Laser.goTo(0, 25, 90);

        // Lastly, cut out a circle around the design.

        // To make a circle, we repeatedly: drive a tiny distance, turn a tiny
        // bit, drive a tiny distance, turn a tiny bit, etc. We move the laser
        // slowly at high power, to burn through the work piece.
        // The angle (2 degees) and distance (0.85 mm) were chosen by trial and
        // error.
        Laser.setSpeed(3);
        Laser.setPower(5);
        for (int i = 0; i < 360; i += 2) {
            Laser.move(0.85); // 0.85 mm
            Laser.turn(-2); // 2 degree clockwise
        }

        // Move quickly back to the bottom left corner, with laser off.
        Laser.setSpeed(10);
        Laser.setPower(0);
        Laser.goTo(0, 0, 0);

    } // end main

} // end Spider
