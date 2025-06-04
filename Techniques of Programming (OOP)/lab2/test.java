public class test{
    public static void main(String args[]) {
       
       String username = args[0];

       double a = 2;
       double b = 2;
       double answer = Math.sqrt(a + b);
       double answerA = Math.sqrt(a);
       double answerB = Math.sqrt(b);
       double answerAB = answerA + answerB;
       double differenceAnswer = (answerAB - answer);

       System.out.println("Hello " + username + ", " + "The result of the square root of a and b's sum is " + answer);
       System.out.println("Howdy " + username + " the sum of the square roots of a and b is " + answerAB);
       System.out.println("Hey " + username + " the difference between the last two answers is " + differenceAnswer);

    }
}