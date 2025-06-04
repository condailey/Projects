//Author: Connor Dailey

public class Divisibles52 {
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){
            int n = Integer.parseInt(args[i]);

            if(n % 52 == 0){
                StdOut.println("Yes, " + n + " is divisible by 52.");
            }else{
                StdOut.println("Nope, " + n + " is not divisible by 52.");
            }
        }
    }
}