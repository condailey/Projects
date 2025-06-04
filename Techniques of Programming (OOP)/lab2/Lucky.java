/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/4/2015
 *  Updated:  7/7/2015 - added better comments
 *  
 *  Prints a randomly-generated lucky number to the console.
 *
 *  Example: java Lucky Alice -5.0 10.0
 *
 *----------------------------------------------------------------*/

public class Lucky {
   public static void main(String args[]) {

      // Put the three command-line arguments into variables.
      String username = args[0];
      double extra = Double.parseDouble(args[1]);
      double factor = Double.parseDouble(args[2]);

      // Print a friendly greeting.
      System.out.println("Hello, " + username + "!");
      System.out.println("Welcome to the lucky-number generator!");
      System.out.println("Please be patient while I pick your lucky number!");

      // Pick a random number (between 0.0 and 1.0), multiply
      // it by some factor, then add some extra.
      double r = Math.random();
      System.out.println("First, " + r + " is chosen as a random number between 0.0 and 1.0.");
      double bigger = r * factor;
      System.out.println("After multiplying by " + factor + ", it becomes " + bigger + ".");
      double answer = bigger+     extra  ;
      System.out.println("After adding " + extra +", it becomes " + answer);

      // Print out the results and a goodbye message.
      System.out.println("Thank you for your patience!");
      System.out.println("After many calculations, I have decided");
      System.out.println("that your lucky number is " + answer  +  ".");
      System.out.println("Bye!");
      }
   }
