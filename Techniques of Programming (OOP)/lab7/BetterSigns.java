//Author: Connor Dailey

public class BetterSigns{
    public static void main(String[] args){
        
        for(int i = 0; i < args.length; i++){
            int n = Integer.parseInt(args[i]);
            if(n > 0){
                StdOut.print(args[i] + " is positive ");
            }if(n == 0){
                StdOut.print(args[i] + " is zero ");
            }if(n < 0){
                StdOut.print(args[i] + " is negative ");
            }

        }
    }
}