/*************************************************************************
 *  Author: Connor Dailey
 *  Date: 9/17/2021
 *  
 * Q-and-A style insect taxonomy, with pictures. It prompts for information
 * about an insect, then displays a picture of the insect in a window.
 * 
 * Example: java Taxonomy
 *
 *************************************************************************/

public class Taxonomy { 
    public static void main(String[] args) { 
        int wings;  // number of pairs of wings
        char shape; // body shape
        String antennae; // size of antennae, either "long" or "short"
        String leatherWings; // whether it has hard leathery wings.
        String pinchers; // whether it has rear pinchers.
        String mouth; // function of the mouth parts

        StdOut.print("How many pairs of fully developed wings: 0, 1, or 2? ");
        wings = StdIn.readInt();
        switch (wings) {
            case 0:
                // Classify insects with no wings
                StdOut.print("What body shape?\n");
                StdOut.print("  T - twig-like slender body\n");
                StdOut.print("  G - grass-hopper like body and long back legs\n");
                StdOut.print("  S - soft body with a protective shield\n");
                StdOut.print("  V - very small, without antennae\n");
                StdOut.print("Response: ");
                shape = StdIn.readString().charAt(0);
                switch (shape) {
                    case 'T':
                    case 't':
                        StdOut.print("Order: Phasmatodea\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/extatosoma-tiaratum.jpg", 1.0, 1.0);
                        break;
                    case 'G':
                    case 'g':
                        StdOut.print("Order: Orthoptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/locust.jpg", 1.0, 1.0);
                        break;
                    case 'S':
                    case 's':
                        StdOut.print("Order: Hemiptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/squashbug.jpg", 1.0, 1.0);
                        break;
                    case 'V':
                    case 'v':
                        StdOut.print("Order: Protura\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/protura.jpg", 1.0, 1.0);
                        break;
                    default:
                        StdOut.print("Sorry, the insect can't be classified.\n");
                        System.exit(1);
                } // end of shape switch
                break;
            case 1:
                // Classify insects with one pair of wings
                StdOut.print("What body shape?\n");
                StdOut.print("  G - grass-hopper like body and plate over abdomen\n");
                StdOut.print("  N - no tail, no plate over abdomen\n");
                StdOut.print("  T - has a tail and no plate over abdomen\n");
                StdOut.print("Response: ");
                shape = StdIn.readString().charAt(0);
                switch (shape) {
                    case 'G':
                    case 'g':
                        StdOut.print("Order: Orthoptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/locust.jpg", 1.0, 1.0);
                        break;
                    case 'N':
                    case 'n':
                        StdOut.print("Order: Strepsiptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/strepsiptera.jpg", 1.0, 1.0);
                        break;
                    case 'T':
                    case 't':
                        StdOut.print("Are the wings long or short?");
                        antennae = StdIn.readString();
                        if (antennae.equals("long")){
                            StdOut.print("Order: Hemiptera\n");
                            StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/squashbug.jpg", 1.0, 1.0);
                        } else if(antennae.equals("short")) {
                            StdOut.print("Order: Ephemroptera\n");
                            StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/mayfly1.jpg", 1.0, 1.0); 
                        }else {
                            StdOut.print("Sorry, the insect can't be classified.\n");
                            System.exit(1);
                     
                }break;
                
                 
                // the antenna length. Shape 'T' or 't' can be different things
                // depending on whether it has long or short antenna.
                // Shape 'T' or 't' with "long" antenna is 
                //     Hemiptera (https://www.amentsoc.org/images/squashbug.jpg)
                // Shape 'T' or 't' with "short" antenna is 
                //     Ephemeroptera (https://www.amentsoc.org/images/mayfly1.jpg)
                 // end of shape switch
                }break;
            case 2:
                // Classify insects with two pair of wings
                StdOut.print("Does it have hard, leathery fore-wings: yes or no? ");
                leatherWings = StdIn.readString();
                if (leatherWings.equals("yes")) {
                    StdOut.print("Does it have rear pinchers: yes or no? ");
                    pinchers = StdIn.readString();
                    if (pinchers.equals("yes")) {
                        StdOut.print("Order: Dermaptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/dermaptera.jpg", 1.0, 1.0);
                    } else if (pinchers.equals("no")) {
                        StdOut.print("Order: Coleoptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/stag-beetles.jpg", 1.0, 1.0);
                    } else {
                        StdOut.print("Sorry, the insect can't be classified.\n");
                        System.exit(1);
                    } 
                } else if (leatherWings.equals("no")) {
                    StdOut.print("What are the mouth parts for: piercing, sucking, or biting? ");
                    mouth = StdIn.readString();
                    if (mouth.equals("piercing") || mouth.equals("sucking")) {
                        StdOut.print("Order: Hemiptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/squashbug.jpg", 1.0, 1.0);
                    } else if (mouth.equals("biting")) {
                        StdOut.print("Order: Neuroptera\n");
                        StdDraw.picture(0.5, 0.5, "https://www.amentsoc.org/images/neuroptera.jpg", 1.0, 1.0);
                    } else {
                        StdOut.print("Sorry, the insect can't be classified.\n");
                        System.exit(1);
                    }
                } 
            
                break;
    // end of wings switch
            }
        
        StdDraw.show(); // This opens and displays the picture window
        StdOut.print("Close the picture window to exit\n");    
        }
   }