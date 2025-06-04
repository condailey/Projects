//Author: Connor Dailey

public class Bart{
    public static void main(String[] args) {
        StdOut.println("Please enter a sentence. ");
        String sentence1 = StdIn.readLine();

        StdOut.println("How many times should the sentence be printed?");
        int num = StdIn.readInt();

        for (int i = 0; i < num; i++){
            StdOut.println(sentence1);
        }
    }
}