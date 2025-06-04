//Author: Connor Dailey

public class French{
    public static void main(String[] args){

        for(int i = 0; i < args.length; i++){
            if(args[i].equalsIgnoreCase("red")){
                StdOut.print("rouge ");
            }else if(args[i].equalsIgnoreCase("green")){
                StdOut.print("vert ");
            }else if(args[i].equalsIgnoreCase("blue")){
                StdOut.print("bleu ");
            }else{
                StdOut.print("Je ne sait pas! ");
            }
        }
    }
}