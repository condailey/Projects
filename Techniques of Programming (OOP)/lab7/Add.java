//Author: Connor Dailey

public class Add{
    public static void main(String[] args){
        StdOut.println("Please enter two numbers to be added. ");
        int sum = 0;
        for(int i = 0; i < 2; i++){
            int number = StdIn.readInt();
            sum += number;

        }

        StdOut.println(sum);
    }
}