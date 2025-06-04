//  Author: Connor Dailey
//  Email: cddail25@g.holycross.edu
//  Date: 7 October 2021
//
// 
//  Prompts user to select an image. After the prompt the user can either
//  choose to view the picture, change it to grayscale, sepia, censor, sharpen, save the new
//  image, or simply quit the program.
// 
//  Example: java PhotoMatic before.jpg

import java.awt.Color;

public class PhotoMatic {
    public static void main(String[] args){

        System.out.println("Welcome to PhotoMatic!");

        // Error check: ensure user provided an extra argument.
        if (args.length != 1) {
            System.err.println("Please provide one extra argument, like this:");
            System.err.println("java PhotoMatic inFileName");
            System.err.println("For example:");
            System.err.println("      java PhotoMatic Feast.jpg");
            System.exit(1);
        }
        // Open the original input image chosen by the user.
        String inputFileName = args[0];
        Picture originalImage = new Picture(inputFileName);
        
    
        // Get the width and height of the original image.
        int w = originalImage.width();
        int h = originalImage.height();

        //list for possible program options
        System.out.println(inputFileName + " size is " + w + " x " + h + " pixels.");
        StdOut.println("Possible options are: ");
        StdOut.println("    show             -- display the image");
        StdOut.println("    grayscale        -- convert the image to grayscale");
        StdOut.println("    sepia            -- convert the image to sepia tone colors");
        StdOut.println("    censor           -- convert the image to a censored version");
        StdOut.println("    sharpen          -- convert the image to a sharpened version");
        StdOut.println("    save NAME        -- save the image using NAME as the filename");
        StdOut.println("    quit             -- quit the program");

        //question asking the user what command they wish to select
        int n = 1;
        
        //loop for continuous questioning
        for(int i = 0; i < n; n++){
        
            StdOut.print("What would you like to do? ");
            String command = StdIn.readString();
        
                //conditionals nested with operations if an option is selected
                if(command.equalsIgnoreCase("show")) {
                    originalImage.show();
                    StdOut.println("ok.");
                }else if (command.equalsIgnoreCase("grayscale")){
                    // Create a new image that is the same size as the original.
                    Picture grayImage = new Picture(w, h);

                    // Loop over all rows y, and for each row loop over all columns x
                    for (int y = 0; y < h; y++) {
                        for (int x = 0; x < w; x++) {

                            // Get the color of the pixel in the original image.
                            Color hue;
                            hue = originalImage.get(x, y);
                            int r = hue.getRed();
                            int g = hue.getGreen();
                            int b = hue.getBlue();

                            // Compute a rounded weighted average, and use that to create a suitable gray color.
                            int z = (int) Math.round(0.299*r + 0.587*g + 0.114*b);
                            Color gray = new Color (z, z, z);

                            // Paint the corresponding pixel in the new image using the computed gray color.
                            grayImage.set(x, y, gray);
                        } 
                    } // end of loop for all the pixels in the image

                    // Save the new image into a new file.
                    StdOut.println("Converting image to grayscale.");

                    //set the current Image
                    originalImage = grayImage;
                }else if(command.equalsIgnoreCase("sepia")){
                    // Compute half the radius, i.e. half the diagonal size of image
                    double radius = 0.5 * Math.sqrt(w*w + h*h);

                    // Create a new image that is the same size as the original.
                    Picture sepiaImage = new Picture(w, h);

                    // Loop over all rows y, and for each row loop over all columns x
                    for (int y = 0; y < h; y++) {
                        for (int x = 0; x < w; x++) {

                            // Get the color of the pixel in the original image.
                            Color hue;
                            hue = originalImage.get(x, y);
                            int r = hue.getRed();
                            int g = hue.getGreen();
                            int b = hue.getBlue();

                            // Compute darken factor depending on distance from center.
                            double distanceFromCenter = Math.sqrt((x-w/2)*(x-w/2) + (y-h/2)*(y-h/2));
                            double darkenFactor = 1.0 - Math.pow(distanceFromCenter/radius, 3);

                            // Compute a rounded weighted average, and use that to create a suitable sepia color.
                            int tr = (int)Math.round(darkenFactor*(0.393*r + 0.769*g + 0.189*b));
                            int tg = (int)Math.round(darkenFactor*(0.349*r + 0.686*g + 0.168*b));
                            int tb = (int)Math.round(darkenFactor*(0.272*r + 0.534*g + 0.131*b));
                            if (tr > 255) tr = 255;
                            if (tg > 255) tg = 255;
                            if (tb > 255) tb = 255;
                            Color sepia = new Color(tr, tg, tb);

                            // Paint the corresponding pixel in the new image using the computed sepia color.
                            sepiaImage.set(x, y, sepia);
                        } 
                    } // end of loop for all the pixels in the image

                    // Save the new image into a new file.

                    StdOut.println("Converting image to sepia.");

                    //set the current Image
                    originalImage = sepiaImage;
                //save the edited image
                } else if(command.equalsIgnoreCase("censor")){
                    
                    //color of censor bar
                    //new censored image
                    Color c = new Color(0, 0, 0);
                    Picture cImage = new Picture(w, h);

                    //inputs for coordinates
                    int left = StdIn.readInt();
                    int top = StdIn.readInt();
                    int right = StdIn.readInt();
                    int bottom = StdIn.readInt();

                    //loop for defining the censored spot
                    for (int y = 0; y < h; y++) {
                        for (int x = 0; x < w; x++) {
                            // Get the color of the pixel in the original image.
                            Color hue;
                            hue = originalImage.get(x, y);
                            int r = hue.getRed();
                            int g = hue.getGreen();
                            int b = hue.getBlue();


                            //Compute the new color or old color
                            if(x > left && x < right && y > top && y < bottom){
                                cImage.set(x, y, c);
                            }else{
                                cImage.set(x, y, hue);
                            }
                        }      
                    }
                    StdOut.println("Censoring image.");
                    originalImage = cImage;
                 //sharpening command      
                }else if(command.equalsIgnoreCase("sharpen")){
                    //creating new image
                    Picture sImage = new Picture(w, h);
                    
                    //loop for defining the quordinates
                    for(int y = 0; y < h ; y++){
                        for(int x = 0; x < w; x++){
                            Color hue;
                            hue = originalImage.get(x, y);
                            int r = hue.getRed();
                            int g = hue.getGreen();
                            int b = hue.getBlue();

                            //if the pixel is on the edge
                            if(x == 0 || x == w - 1 || y == 0 || y == h - 1){
                                sImage.set(x, y, hue);
                            }
                            //converting the image to sharpened version
                            else{
                                Color hueL;
                                hueL = originalImage.get(x - 1,y);
                                int rL = hueL.getRed();
                                int gL = hueL.getGreen();
                                int bL = hueL.getBlue();

                                Color hueT;
                                hueT = originalImage.get(x,y + 1);
                                int rT = hueT.getRed();
                                int gT = hueT.getGreen();
                                int bT = hueT.getBlue();

                                Color hueR;
                                hueR = originalImage.get(x + 1,y);
                                int rR = hueR.getRed();
                                int gR = hueR.getGreen();
                                int bR = hueR.getBlue();

                                Color hueB;
                                hueB = originalImage.get(x,y - 1);
                                int rB = hueB.getRed();
                                int gB = hueB.getGreen();
                                int bB = hueB.getBlue();
                                int newR = (r * 5) - rL - rT - rR - rB;
                                int newB = (b * 5) - bL - bT - bR - bB;
                                int newG = (g * 5) - gL - gT - gR - gB;

                                //if the values are greater than 255 or below zero
                            if(newR > 255){
                                newR = 255;   
                            }if(newB > 255){
                                newB = 255;
                            }if(newG > 255){
                                newG = 255;
                            }if(newR < 0){
                                newR = 0;
                            }if(newB < 0){
                                newB = 0;
                            }if(newG < 0){
                                newG = 0;
                            }
                            Color sharp = new Color(newR, newG, newB);
                            sImage.set(x, y, sharp);
                        }     
                    }
                }
                    //update the new image
                    originalImage = sImage;
                    //output for program
                    StdOut.println("producing sharpened image");
                    
                //save the edited image 
                }else if(command.equalsIgnoreCase("save")){
                    String newFile = StdIn.readString();
                    Picture saveImage = originalImage;

                    saveImage.save(newFile);
                    StdOut.println("Saving image as " + newFile);
                //conditional for quiting the program
                 }else if(command.equalsIgnoreCase("quit")){
                    StdOut.println("Good bye!");
                    System.exit(0);
                //error checking
                 }else if(!command.equalsIgnoreCase("show") || !command.equalsIgnoreCase("grayscale") || !command.equalsIgnoreCase("sepia") || !command.equalsIgnoreCase("save") || !command.equalsIgnoreCase("quit")) {
                    StdOut.println("Sorry, I don't know " + command + ", try a different option.");
                }     
            }  
        }
    }       