//Author: Connor Dailey

public class Waves{
    public static double[] wave(int n, double f){
        double[] array = new double[n];
        double x = Math.PI;
        for(int i = 0; i < array.length; i++){
            array[i] = Math.sin(f * x * (i/22000.0));
        }
        return array;
    }
    public static void main(String[] args){
        int n = 100;
        double f = 440.0;
        double[] w = wave(100, 440.0);
        StdPlot.plot(w);
    }
}