//Author: Connor Dailey

public class Calc{
    public static void main(String[] args){
        StdOut.println("Please enter a math problem. ");
        double answer = 0;
        
        for(int i = 0; i < 1; i++){
            double num1 = StdIn.readDouble();
            String operator = StdIn.readString();
            double num2 = StdIn.readDouble();
            if(operator.equals("+")){
            answer = num1 + num2;
            }else if(operator.equals("-")){
            answer = num1 - num2;
            }else if(operator.equals("*")){
            answer = num1 * num2;
            }else if(operator.equals("/")){
            answer = num1 / num2;
            }

        }
        StdOut.println(answer);
    }
}