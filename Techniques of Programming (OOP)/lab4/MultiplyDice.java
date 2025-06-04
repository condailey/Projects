/*----------------------------------------------------------------
 *  TODO: FILL THIS IN
 *
 *  Example: java MultiplyDice
 *----------------------------------------------------------------*/

public class MultiplyDice {

    public static void main(String[] args) { 

        // Begin the game with a score of 1. 
        // Note: We use long, rather than int, for the score, because the scores
        // can get quite large, far larger than allowable for an int variable.
        long score = 1;
        // TODO: Roll dice twenty times, and each time multiply score by the value.
        for(int i = 0; i < 20; i++){
            long roll = StdRandom.uniform(1, 6);
            score = roll * score;
            StdOut.println("You rolled a " + roll + ", " + "your score is now " + score);
        }
        // TODO: Print out the score after 20 rolls.
        StdOut.println("After 20 rolls, your score is " + score);
        // TODO: Calculate the leading digit by repeatedly dividing
        // the score by 10, so long as the score is larger than 9.
        long leadingDigit = score;
        while(leadingDigit > 9){
            leadingDigit = leadingDigit / 10; 
        }
        // TODO: Print the leading digit.
        StdOut.println("The leading digit is " + leadingDigit);       
    } // end of main

} // end of class MultiplyDice
