//Author: Connor Dailey

public class Divisibility{
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){
            int n = Integer.parseInt(args[i]);
            for(int j = 1; j < n; j++){
                if(n % j == 0){
                    StdOut.println("Yes, " + n + " is divisible by " + j);
                }else{
                    StdOut.println("Nope, " + n + " is not divisible by " + j);
                }
            }
        }
    }
}