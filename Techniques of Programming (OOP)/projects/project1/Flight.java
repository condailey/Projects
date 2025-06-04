import java.security.Key;

/*------------------------------------------------------ *
 *  Author: Connor Dailey
 *  Email: cddail25@g.holycross.edu
 *  Written: 9/9/2021
 * 
 *  Prints the home and destination points and also prints the distance
 *  between them along with prompts for incorrect inputs. If inputs are
 *  correct then the program prompts the user if they want to see a map
 *  of their trip.
 *  
 *  Example: java Flight 42.2381 N 71.8109 W 37.362517 N 122.034760 W
 * ------------------------------------------------------ */

 public class Flight {
    
    //Variable for the Earth's Radius

    public static final double earthRadius = 3963.1676;
    public static void main(String args[]) {
        
        //Establishing variables for validity

        double validH = 0;
        double validNSEWH = 0;
        double validD = 0;
        double validNSEWD = 0;

        //Home latitude and longitude variables
        
        double latitudeH = Double.parseDouble(args[0]);
        char northSouthH = args[1].charAt(0);
        double longitudeH = Double.parseDouble(args[2]);
        char eastWestH = args[3].charAt(0);
        
        //Destination latitude and longitude variables
        
        double latitudeD = Double.parseDouble(args[4]);
        char northSouthd = args[5].charAt(0);
        double longituded = Double.parseDouble(args[6]);
        char eastWestd = args[7].charAt(0);

        //Noting invalid inputs

        if (latitudeH > 0){
            validH = validH + 0.0;
        } else {
            validH = validH + 1.0;
        }

        if (longitudeH > 0){
            validH = validH + 0.0;
        } else {
            validH = validH + 1.0;
        }

        if (latitudeD > 0){
            validD = validD + 0.0;
        } else {
            validD = validD + 1.0;
        }

        if (longituded > 0){
            validD = validD + 0.0;
        } else {
            validD = validD + 1.0;
        }
        
        if (latitudeH <= 90){
            validH = validH + 0.0;
        } else {
            validH = validH + 1.0;
        }

        if(latitudeD <= 90) {
            validD = validD + 0.0;
        } else {
            validD = validD + 1.0;
        }

        if(longitudeH <= 180){
            validH = validH + 0.0;
        } else {
            validH = validH + 1.0;
        }

        if(longituded <= 180){
            validD = validD + 0.0;
        } else {
            validD = validD + 1.0;
        }

        if (northSouthH != 'N' && northSouthH != 'S'){
            validNSEWH = validNSEWH + 1.0;
        }

        if (northSouthd != 'N' && northSouthd != 'S'){
            validNSEWD = validNSEWD + 1.0;
        }
        

        if (eastWestH != 'E' && eastWestH != 'W'){
            validNSEWH = validNSEWH + 1.0;
        }

        if (eastWestd != 'E' && eastWestd != 'W'){
            validNSEWD = validNSEWD + 1.0;
        }

        //Printing coordinates

        System.out.println("Home latitude is " + latitudeH + " " + northSouthH);
        System.out.println("Home longitude is " + longitudeH + " " + eastWestH);
        System.out.println("");
        System.out.println("Destination latitude is " + latitudeD + " " + northSouthd);
        System.out.println("Destination longitude is " + longituded + " " + eastWestd);
        System.out.println();

        //Converting longitude and latitudes to negatives and positives
  
        if (northSouthH == 'N') {
            latitudeH = latitudeH * 1.0;
        } else{
            latitudeH = latitudeH * -1.0;
        }

        if (eastWestH == 'E') {
            longitudeH = longitudeH * 1.0;
        } else{
            longitudeH = longitudeH * -1.0;
        }

        if (northSouthd == 'N') {
            latitudeD = latitudeD * 1.0;
        } else {
            latitudeD = latitudeD * -1.0;
        }

        if (eastWestd == 'E') {
            longituded = longituded * 1.0;
        } else {
            longituded = longituded * -1.0;
        }
            
        //Calculations of coordinates to radians creating a new variable to hold them

        double latitudeHRadians = Math.toRadians(latitudeH);
        double longitudeHRadians = Math.toRadians(longitudeH);
        double latitudeDRadians = Math.toRadians(latitudeD);
        double longitudedRadians = Math.toRadians(longituded);

        //Calculations finding V
        
        double deltaLatitude = latitudeHRadians - latitudeDRadians;
        double a = deltaLatitude / 2.0;
        double sinA = Math.sin(a);
        double sinASqrd = Math.pow(sinA, 2);

        double deltaLongitude = longitudeHRadians - longitudedRadians;
        double b = deltaLongitude / 2.0;
        double sinB = Math.sin(b);
        double sinBSqrd = Math.pow(sinB, 2);

        double cosLatH = Math.cos(latitudeHRadians);
        double cosLatD = Math.cos(latitudeDRadians);
        double latProduct = cosLatH * cosLatD;

        double v = sinASqrd + sinBSqrd * latProduct;

        //Calculations finding distance
        
        double preDistance = 2.0 * earthRadius * Math.atan2(Math.sqrt(v), Math.sqrt(1.0 - v));
        double distance = Math.round(preDistance * 100.0) / 100.0;

        //Prompt for invalid inputs resulting in the end of the program
        
        if (validH > 0.0 && validNSEWH > 0.0) {
            System.out.println("Sorry, the home coordinates you entered are not valid.");
            System.out.println("Sorry, the home N/S hemisphere you entered is not valid.");

        }

        if (validH > 0.0 && validNSEWH == 0.0){
            System.out.println("Sorry, the home coordinates you entered are not valid.");

        }
       
        if (validNSEWH > 0.0 && validH == 0.0){
            System.out.println("Sorry, the home N/S hemisphere you entered is not valid.");
        }

        if (validD > 0.0 && validNSEWD > 0.0) {
            System.out.println("Sorry, the destination coordinates you entered are not valid.");
            System.out.println("Sorry, the destination N/S hemisphere you entered is not valid.");
        }

        if (validD > 0.0 && validNSEWD == 0.0){
            System.out.println("Sorry, the destination coordinates you entered are not valid.");
        }
        
        if (validNSEWD > 0.0 && validD == 0.0){
            System.out.println("Sorry, the destination N/S hemisphere you entered is not valid.");
        }

        if (validH > 0.0 || validNSEWH > 0.0 || validD > 0.0 || validNSEWD > 0.0) {
            System.out.println("Because of invalid coordinates, the program will terminate.");
            System.exit(1);
        }
        
        //Printing distance

        System.out.println("The distance from home to destination is " + distance + " miles.");
        
        //Asking to see map and printing link to map

        System.out.print("Would you like to see a map of your trip? ");
        String answer = StdIn.readString();
        System.out.println("You said " + answer + "!");
        System.out.println("");
        
        //The end of the program resulting in the corresponding link and map being printed for the coordinates given
        
        if(answer.equalsIgnoreCase("Yes")){ 
            System.out.println("Right-click this link or copy it to your borwser:");
            String url = ("https://www.mapquestapi.com/staticmap/v4/getmap?key=t8xhSEnIxadDAwSL0fSuJer5JAWqGPZ5&size=400,400&type=map&bestfit=" + latitudeH + "," + longitudeH + "," + latitudeD + "," + longituded + "&pois=A," + latitudeH + "," + longitudeH + "%7CB," + latitudeD + "," + longituded + "&polyline=color:0xff0000%7Cwidth:3%7C" + latitudeH + "," + longitudeH + "," + latitudeD + "," + longituded);
            System.out.println(url);
            StdDraw.picture(0.5, 0.5, url);
        } else{
            System.out.println("Thank you for using my program!");
        }
    } //End of Main
}