//  Laurie K
//  LKing@holycross.edu
//  8 July 2015
//
//  Updates:
//    12 Sept 2021 - K. Walsh - display both images, change output file name.
//
//  Converts an existing image to grayscale using a weighted averaging method.
//  The old and new images are both displayed on the screen, and the new image
//  is saved to a file (with the word "Gray-" prefixed to the original filename).
//
//  Algorithm is adapted from programs 3.1.3 (pg. 328) and 3.1.4 (pg. 331) of
//  the Sedgewick and Wayne textbook.
//
//  java Gray inFileName 
//  Example: java Gray Feast.jpg 

import java.awt.Color;

public class Gray {

  public static void main(String[] args) {

    // Error check: ensure user provided an extra argument.
    if (args.length != 1) {
      System.err.println("Please provide one extra argument, like this:");
      System.err.println("java Gray inFileName");
      System.err.println("For example:");
      System.err.println("      java Gray Feast.jpg");
      System.exit(1);
    }

    // Open the original input image chosen by the user.
    String inputFileName = args[0];
    Picture originalImage = new Picture(inputFileName);
    originalImage.show();

    // Get the width and height of the original image.
    int w = originalImage.width();
    int h = originalImage.height();

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
    String outputFileName = "Gray-" + inputFileName;

    grayImage.save(outputFileName);
    System.out.println(inputFileName + " has been converted to grayscale as " + outputFileName);

    grayImage.show();

  } // end of main()
} // end of Gray class