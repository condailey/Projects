public class test{
    public static void main(String[] args){
        double [] grades = {50, 80, 85, 90};
        double quizAvg = StdStats.mean(grades);
        StdOut.println("You earned " + quizAvg + " semester quiz average.");

    
        
        double bestEarlyQuiz = StdStats.max(grades, 0, 2);
        double bestLaterQuiz = StdStats.max(grades, 2, 3);
        if (bestLaterQuiz > bestEarlyQuiz){
            StdOut.println("Congrats! You imporved!");
        }


        StdOut.println(SongTime.toSeconds("2:15"));

        StdOut.println(SongTime.toMMSS(75+SongTime.toSeconds("3:05")));

    }
}

