//Author: Connor Dailey

public class Prime{
    public static boolean isPrime(int n){
        int j = 0;
        for(int i = 2; i <= n / 2; i++){
            if(n % i == 0){
                j = j + 1;
            }
        }
        if(j == 0){
            return true;
        }else{
            return false;
        }
    }
    
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        if (isPrime(n)) StdOut.println(n + " is prime.");
        else StdOut.println(n+ " is not prime.");
    }
}