/*----------------------------------------------------------------
 *  TODO: FILL THIS IN
 *
 *  Checks whether the MultiplyDice game follows Benford's law.
 *
 *  Usage: java Benford count
 *  Example: java Benford 1000
 *----------------------------------------------------------------*/

public class Benford {

    public static void main(String[] args) { 

        // TODO: Use first command-line argument for n, the repetition count.
        // TODO: Use second command-line argument for k, the target value.
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int count = 0;
        int c = 0;

        
        for(int g = 0; g < n; g++){
            long score = 1;
            // TODO: Roll dice twenty times, and each time multiply score by the value.
            for(int i = 0; i < 20; i++){
                long roll = StdRandom.uniform(1, 6);
                score = roll * score;    
            }
            // TODO: Print out the score after 20 rolls.
            // TODO: Calculate the leading digit by repeatedly dividing
            // the score by 10, so long as the score is larger than 9.
            long leadingDigit = score;
            while(leadingDigit > 9){
                leadingDigit = leadingDigit / 10; 
            }
            // TODO: Print the leading digit.
            
            if(leadingDigit == k){
                count++;
            }
        }
        // Print a summary.
        System.out.println("Out of " + n + " games, a " + k + " appears as the leading digit");
        System.out.println("of the score " + count + " times, or "+(count*100.0/n)+"% of the time.");

    } // end of main

} // end of Benford
