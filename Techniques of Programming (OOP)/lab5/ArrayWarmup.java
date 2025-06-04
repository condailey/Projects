//**************************************************************************
// ArrayWarmup.java
// Author:
// Date:
// Class: CSCI 131
// Purpose: Compute a few basic statistics for a list of grades, using a
// partially-full, fixed-size array to hold the values. Data is input by the
// user or generated randomly.
//**************************************************************************

public class ArrayWarmup {

    static final int MAX_NUM_GRADES = 20;

    public static void main(String[] args) {

        int[ ] grades = new int[MAX_NUM_GRADES];  // Array with space for up to 20 grades.
        int numGrades = 0;                        // How many entries in the array are filled.

        if (args.length == 0) {
            // If no command-line arguments were given, ask the user to input
            // the grades one by one, stopping when they enter a negative (i.e.
            // nonsensical) number.
            boolean keepGoing = true;
            while (keepGoing) {
                StdOut.print("Enter a grade: ");
                int x = StdIn.readInt();
                if (x < 0) {
                    keepGoing = false;
                } else {
                    // Put the entered number into the array, and increment our
                    // count of how many entries are filled.
                    grades[numGrades] = x;
                    numGrades++;
                }
            }
        } else if (args.length == 1) {
            // If one command-line argument (an integer) was given, generate
            // that many random grades to use for testing.
            numGrades = Integer.parseInt(args[0]);
            for (int i = 0; i < numGrades; i++) {
                grades[i] = StdRandom.uniform(0, 101);
            }
        } else {
            StdOut.println("Ooops, this program expects zero or one command-line arguments!");
            StdOut.println("Try running it like this:");
            StdOut.println("  java ArrayWarmup     # will ask for grades interactively");
            StdOut.println("or:");
            StdOut.println("  java ArrayWarmup 10  # will generate 10 random grades");
            System.exit(1);
        }

        // Print all grades, on one line with spaces between, and a newline at the end.
        StdOut.println("There are " + numGrades + " filled entries in the grades array.");
        for (int i = 0; i < numGrades; i++) {
            StdOut.print(grades[i] + " ");
        }
        StdOut.println( );
        
        
        // Print out some statistics...


        StdOut.print("First grade is " + grades[0]); 

        StdOut.println( );                // Leave one blank line

        StdOut.print("Last grade is " + grades[numGrades -1]);   
        StdOut.println( );                // Leave one blank line

        StdOut.print("Middle grade is " + grades[numGrades/2]); 
        StdOut.println( );                // Leave one blank line

        // Find the average of values in the array
        int sum = 0;
        for (int i = 0; i < numGrades; i++) {
            sum += grades[i];
        }
        double average = (double)sum/(double)numGrades;
        StdOut.println("The average of grade is: " + average);

        // Find the maximum value in the array
        int max = grades[0];  // maximum element seen so far
        for (int i = 0; i < numGrades; i++) {
            // Check if this next grade is higher than the max seen so far.
            if (grades[i] > max) {
                max = grades[i];
            }
        }
        StdOut.println("The maximum grade is: " + max);
    }
}
