//Author: Connor Dailey

public class RandomArray {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        double [] array = new double[n];
        int negative = 0;
        int positive = 0;
        int zero = 0;

        for(int i = 0; i < array.length; i++){
            array[i] = StdRandom.uniform(-1.0, 1.0);
            if(array[i] < 0){
                negative++;
            }if(array[i] > 0){
                positive++;
            }if(array[i] == 0){
                zero++;
            }
        }

        StdOut.println("There are " + negative + " negative numbers");
        StdOut.println("There are " + positive + " positive numbers");
        StdOut.println("There are " + zero + " zero numbers");

    }
}
