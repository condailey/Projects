/* WordSearch.java
 * Author: Connor Dailey
 * Date: 10/7/2021
 * Course: CSCI 131
 *
 * A word-search game, demonstrating use of 2d arrays, 1d arrays, and input
 * files in Java.
 */
public class WordSearch {
    public static void main(String[] args) {

        StdOut.printf("Welcome to Word Search!\n");

        // Open word-list.txt as an input file.
        In wordFile = new In("word-list.txt");
        // Get an integer from the file, the number of words in the puzzle.
        int numWords = wordFile.readInt();
        // Make an array big enough to hold that many words,
        // and get them from the file.
        String[] words = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            words[i] = wordFile.readString();
        }
        // Close the file.
        wordFile.close();

        StdOut.printf("This word search involves %d words.\n", numWords);

        // Open high-score.txt as an input file.
        // TODO: Add code here
        In highScoreFile = new In("high-score.txt");

        // Get one complete line from, the name of the high scoring player.
        // TODO: Add code here
        String bestPlayer = highScoreFile.readLine();

        // Get on integer from the file, the score that player earned.
        // TODO: Add code here
        int bestScore = highScoreFile.readInt();;


        // Close the file.
        // TODO: Add code here
        highScoreFile.close();

        StdOut.printf("So far, the high score is held by '%s', who found %d words.\n", bestPlayer, bestScore);
        StdOut.printf("Can you do better?\n");

        // Open word-search.txt as an input file.
        // TODO: Add code here

        In wordSearch = new In("word-search.txt");

        // Read two integers, numRows and numCols, from the file.
        int numRows = wordSearch.readInt();
        int numCols = wordSearch.readInt();
        // TODO: Add code here
       
             

        StdOut.printf("This word-search involves a %d-row by %d-column grid.\n", numRows, numCols);

        // Make an array big enough to hold a grid of that size.
        char grid[][] = new char[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
                for(int j = 0; j < numCols; j++){
                    grid[i][j] = wordSearch.readString().charAt(0);
                    
                } 
            }

        // Now read letters from the file. Starting with row 0, read one string
        // and put the first character of that string into the grid, and repeat
        // similarly for the other columns in row 0.  Next repeat all of that
        // for row 1, for row 2, and for all the other rows.
        // TODO Add code here

        // Close the file.
        // TODO: Add code here
        wordSearch.close();


        boolean keepGoing = true;
        int numFound = 0;
        while (keepGoing && numFound < numWords) { // start of main game loop

            StdOut.printf("Here is the letter grid:\n");
            // First, print the row numbers across the top
            StdOut.printf("     ");
            for (int c = 0; c < numCols; c++) {
                StdOut.printf(" %2d", c);
            }
            StdOut.printf("\n");
            // Next, print a row of dashes
            StdOut.printf("    +-");
            for (int c = 0; c < numCols; c++) {
                StdOut.printf("---");
            }
            StdOut.printf("\n");

            
            // Next, print each rows of letters, prefixed by the row number
            for (int r = 0; r < numRows; r++) {
                // The row number comes first, in a 3-wide column.
                StdOut.printf("%3d |", r);
                // Then the letters, spaced out in 3-wide columns.
                for (int c = 0; c < numCols; c++) {
                    StdOut.printf("%3c", grid[r][c]);
                }
                // Then a newline to finish the row.
                StdOut.printf("\n");
            }

            StdOut.printf("You are searching for these words:\n");
            for (int i = 0; i < numWords; i++) {
                // Print each word in a 12-wide column.
                StdOut.printf(" %-12s", words[i]);
                // Print a newline every 4th word and after the last word.
                if ((i+1 == numWords) || ((i+1) % 4 == 0))
                    StdOut.printf("\n");
            }

            // Ask user what they want to do.
            StdOut.printf("Choose down, across, diagonal, or quit... what next? ");
            String choice = StdIn.readString();
            if (choice.equalsIgnoreCase("quit")) {
                keepGoing = false;
            } else {
                StdOut.printf("Starting row and column? ");
                int a = StdIn.readInt();
                int b = StdIn.readInt();
                StdOut.printf("How many letters? ");
                int n = StdIn.readInt();

                String word = "";
                // Use a loop to take n letters from the grid and add them to
                // the word. Letters should be taken according to what the user
                // chooses: the first letter should be the one from row a,
                // column b. The next letter should be either just to the right
                // (if the user choice was "down") or just to the left (if the
                // choice was "across").
                if (choice.equalsIgnoreCase("down")) {
                    // TODO: Add code here
                    for(int i = 0; i < n; i++){
                        char letter = grid[a+i][b];
                        word += letter;
                    } 

                } else if (choice.equalsIgnoreCase("across")) {
                    // TODO: Add code here
                    for(int i = 0; i < n; i++){
                        char letter = grid[a][b+i];
                        word += letter;
                    }

                } else if (choice.equalsIgnoreCase("diagonal")) {
                    // TODO: Add code here
                    for(int i = 0; i < n; i++){
                        char letter = grid[a+i][b+i];
                        word += letter;
                    }

                }

                StdOut.printf("Your selection: %s\n", word);

                // Check if the word they found matches any of the words in the
                // wordList. If so, then count it, and erase that word from the
                // wordList.
                for (int i = 0; i < numWords; i++) {
                    if (word.equalsIgnoreCase(words[i])) {
                        StdOut.printf("Congrats! You found a word.\n");
                        numFound++;
                        words[i] = "";
                        break;
                    }
                }
                StdOut.printf("You have found %d of %d words so far.\n", numFound, numWords);
            } // end of user choice condition
        } // end of main game loop

        // At end of game, check if they have won.
        if (numFound == numWords) {
            StdOut.printf("Hurray! You found them all!\n");
        } else {
            StdOut.printf("Sorry, maybe try again later?\n");
        }

        if (numFound > bestScore) {
            // If they found more words than the previous best score, then open the
            // high-score.txt file as an output file, and print the user's name and
            // score into that file. The name should be printed on the first line,
            // and the score on the second line.
            // Be sure to close the output file when finished.
            StdOut.printf("You got the high score. What is your name? ");
            String name = StdIn.readString();
            // TODO: Add code here
            Out highScoreFile1 = new Out("high-score.txt");
            highScoreFile1.println(name);
            highScoreFile1.println(numFound);
            highScoreFile1.close();

        }

    } // end of main function
}
 // end of class WordSearch
