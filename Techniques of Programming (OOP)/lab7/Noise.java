//Author: Connor Dailey

public class Noise{
    public static double[] noise(int n){
        double[] array =  new double[n];
            for(int i = 0; i < noise.length; i++){
                noise[i] = StdRandom.uniform(-1.0, 1.0);
            }
            return array;
        }
    
    public static void main(String[] args) {
        int n = 400;
        double[] sound = noise(400);
        StdPlot.plot(sound); 
    }
    
}