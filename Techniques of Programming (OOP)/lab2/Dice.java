/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/4/2015
 *  Updated:  7/7/2015 - added better comments
 *  
 *  Prints a simulation of an n-sided dice roll.
 *
 *  Example: java Dice 6
 *
 *----------------------------------------------------------------*/

public class Dice {
   public static void main(String args[]) {

      // Put the three command-line arguments into variables.
      int n = Integer.parseInt(args[0]);

      // Print a friendly greeting.


      // Pick a random number (between 0.0 and 1.0), multiply
      // it by some factor, then add some extra.
      double r = Math.random();
      double bigger = r * n;
      long answer = Math.round(bigger + 0.5);
      

      // Print out the results and a goodbye message.
      System.out.println("Using a 6 sided dice, you rolled a " + answer + ".");
      }
   }
