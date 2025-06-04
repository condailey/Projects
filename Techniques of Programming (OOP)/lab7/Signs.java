//Author: Connor Dailey

public class Signs{
    public static void main(String[] args){
        
        for(int i = 0; i < args.length; i++){
            int n = Integer.parseInt(args[i]);
            if(n > 0){
                StdOut.print("positive ");
            }if(n == 0){
                StdOut.print("zero ");
            }if(n < 0){
                StdOut.print("negative ");
            }

        }

    }
}