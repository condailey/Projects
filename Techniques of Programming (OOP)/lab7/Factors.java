//Author: Connor Dailey

public class Factors{
    public static int factors(int n){
        int a = 0;
        for(int i = 2; i < n; i++){
            
            if(n % i == 0){
                a++;
            }
        
        }
        return a;
    }
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int f = factors(n);
        System.out.printf("There are %d numbers that evenly dvide %d\n", f, n);
    }
}