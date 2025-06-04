public class Engraving {
    public static void main(String[] args){
        Laser.PLAYBACK_SPEED = 150;

        Laser.setSpeed(150);
        Laser.setPower(0);
        for(int j = 0; j < 3; j++){
            int x = StdRandom.uniform(1, 40);
            int y = StdRandom.uniform(1, 40);
                Laser.goTo(x, y, 0);
                for (int i = 0; i < 50; i++) {
                    Laser.setPower(3); 
                    Laser.move(5); 
                    Laser.turn(5); 
                    Laser.setPower(0); 
                    Laser.move(5); 
                    Laser.turn(120);
                    
                }
                Laser.goTo(x + 5, y + 3, 0);
                for (int i = 0; i < 50; i++) {
                    Laser.setPower(3); 
                    Laser.move(5); 
                    Laser.turn(5); 
                    Laser.setPower(0); 
                    Laser.move(5); 
                    Laser.turn(120);
                }
                Laser.goTo(x - 5, y -3, 0);
                for (int i = 0; i < 50; i++) {
                    Laser.setPower(3); 
                    Laser.move(5); 
                    Laser.turn(5); 
                    Laser.setPower(0); 
                    Laser.move(5); 
                    Laser.turn(120);
                }
        }
    }

}