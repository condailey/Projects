//  K. Walsh
//  kwalsh@holycross.edu
//  12 Sept 2021
//
//  Converts an existing image to sepia tone using a weighted averaging method,
//  and gives the borders a darkened appearance.
//
//  The old and new images are both displayed on the screen, and the new image
//  is saved to a file (with the word "Sepia-" prefixed to the original filename).
//
//  java Sepia inFileName 
//  Example: java Sepia Feast.jpg 

import java.awt.Color;

public class Sepia {

  public static void main(String[] args) {

    // Error check: ensure user provided an extra argument.
    if (args.length != 1) {
      System.err.println("Please provide one extra argument, like this:");
      System.err.println("java Sepia inFileName");
      System.err.println("For example:");
      System.err.println("      java Sepia Feast.jpg");
      System.exit(1);
    }

    // Open the original input image chosen by the user.
    String inputFileName = args[0];
    Picture originalImage = new Picture(inputFileName);
    originalImage.show();

    // Get the width and height of the original image.
    int w = originalImage.width();
    int h = originalImage.height();

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
    String outputFileName = "Sepia-" + inputFileName;

    sepiaImage.save(outputFileName);
    System.out.println(inputFileName + " has been converted to sepia as " + outputFileName);

    sepiaImage.show();

  } // end of main()
} // end of Sepia class