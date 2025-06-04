/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/4/2015
 *  Updated:  7/7/2015 - renamed from Dice to RedBlueGreen
 *  
 *  User picks a color and the program simulates rolling two dice.
 *
 *  Example: java RedBlueGreen blue
 *  Output: You picked blue and rolled a pair of 3s.
 *----------------------------------------------------------------*/

public class RedBlueGreen {
    public static void main(String[] args) { 
        // Error check: ensure one command-line argument was provided.
        if (args.length != 1) {
            System.out.println("Please provide one command line argument.");
            System.out.println("For example: java RedBlueGreen blue");
            System.exit(1);
        }

        // Put the command-line argument into a variable.
        String pick = args[0]; // the user's guess
        double valid = 0.0;

        //validity of the pick input and error check
        if (pick.equalsIgnoreCase("red")){
            valid = valid + 1.0;
        } else if (pick.equalsIgnoreCase("blue")){
            valid = valid + 1.0;
        } else if (pick.equalsIgnoreCase("green")){
            valid = valid + 1.0;
        } else {
            System.out.println("Please provide one of the valid inputs.");
            System.out.println("For example: 'red', 'blue', or 'green'");
            System.exit(1);
        }


        // Pick two random integers each between 1 and 6, inclusive.
        int a = (int)(1 + Math.random() * 6); // first dice
        int b = (int)(1 + Math.random() * 6); // second dice

        // Sum of the two dice rolls
        int sum = a + b;

        //Result color corresponding with the dice roll.

        String result = "";
        double resultDivide = sum % 2;

        if(resultDivide != 0) {
            result = "red";
        } else if(sum == 8){
            result = "green";
        } else if(resultDivide == 0){
            result = "blue";
        }


        //Winning determination

        String status;

        if(pick.equals(result) && sum == 8) {
            status = "You win!\nYour prize is 4$";
        } else if(pick.equals(result)) {
            status = "You win!\nYour prize is 1$";
        } else{
            status = "Sorry, you lost!";
        }




        // Print the results
        if (a == b) {
            System.out.print("You picked " + pick);
            System.out.println(" and rolled a pair of " + a + "s.");
            System.out.println("The sum is " + sum);
            System.out.println("The result is " + result);
            System.out.println(status);
        } else {
            System.out.print("You picked " + pick);
            System.out.println(" and rolled a " + a + " and a " + b + ".");
            System.out.println("The sum is " + sum);
            System.out.println("The result is " + result);
            System.out.println(status);
        }
        
    } //end of main
} //end of RedBlueGreen
