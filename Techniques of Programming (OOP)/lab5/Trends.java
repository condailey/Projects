//**************************************************************************
// Trends.java
// Author:
// Date:
// Class: CSCI 131
// Purpose: Compute a few basic statistics for a list of integers, using a
// completely-full array to hold the values. Data is taken from a file of
// climate data.
//**************************************************************************

public class Trends {
    public static void main(String[] args) {
        // Load data from a file containing historical climate data for
        // Worcester, MA. The data consists of a single number, the montly
        // average temperature, for each month starting in January 1900.
        StdOut.println("Loading data from worcester-temperature.txt ...");
        In infile = new In("worcester-temperature.txt");
        double[] data = infile.readAllDoubles();

        StdOut.print("Do you want to see all the data? [y or n] ");
        if (StdIn.readString().equalsIgnoreCase("y")) {
            // Print all temperature data, with spaces, and a newline between every 12 numbers.
            StdOut.println("There are " + data.length + " values in the array.");
            for (int i = 0; i < data.length; i++) {
                if (i % 12 == 0)
                    StdOut.println();
                StdOut.print(data[i] + " ");
                
            }
            StdOut.println( );
        }

       

        
        StdOut.println("The earliest year of data is from 1900.");
        int numYears = data.length / 12;
        int endYear = (numYears - 1) + 1900; 
        StdOut.println("The most recent year of data is from " + endYear + ".");
        StdOut.println("There are " + numYears + " years worth of data in all.");
        
        // Find the average of values in the array
       double sum = 0;
       for (int i = 0; i < data.length; i++) {
           sum += data[i];
       }
       double average = (double)sum/(double)data.length;

       // Find the average of the first decade in the array
       double sum1 = 0;
       for (int i = 0; i < 120; i++) {
           sum1 += data[i];
       }
       double average1 = (double)sum1/(double)120;

       // Find the average of the last decade in the array
       double sum2 = 0;
       for (int i = 0; i < 120; i++) {
           sum2 += data[i + data.length - 120];
       }
       double average2 = (double)sum2/(double)120;

        // Print out some statistics...

        // Print out average of all the data.
        StdOut.println("Average temperature overall: " + average);
      
        StdOut.println("");                // Leave one blank line

        // Print out the average for just the first 120 months (1900 to 1909)
        StdOut.println("Average temperature for 1900-1909: " + average1);
        
        StdOut.println("");                // Leave one blank line

        // Print out the average for just the last 120 months (???? to ????)
        StdOut.println("Average temperature for last decade: " + average2);
        
        StdOut.println("");                // Leave one blank line

        // Let the user pick a month and year, and print out the data for just
        // that one month.
        StdOut.print("Pick a month and year, e.g. \"6 1999\": ");
        int month = StdIn.readInt();
        int year = StdIn.readInt();
        if (month > 12 || month < 0 || year > endYear || year < 1900) {
            StdOut.println("Sorry, I don't have data for "+month+"/"+year+".");
        } else {
            int specYear = year - 1900;
            int yearNew = specYear * 12;
            int specMonth = month - 1;
            int newData = specMonth + yearNew;
            StdOut.println("Average temperature for " + month + "/" + year + ":" +  " " + data[newData]);
                }
        StdOut.println( );                // Leave one blank line

        // Declare and allocate an array of doubles to hold yearly averages.
        double[] yearlyAvg = new double[numYears];

        // Each value in the yearlyAvg array will represent the average over an
        // entire year worth of data. There will be one value for 1900, one
        // value for 1901, one value for 1902, etc., up to and including
        // endYear. For each year, calculate the average for the 12 months of
        // that year, and store this result in the appropriate place in the
        // yearlyAvg array.
        // TODO: Add code here.
        for(int n = 0; n < numYears; n++){
            double sum4 = 0;
            for (int i = 0; i < 12; i++) {
                sum4 += data[12 * n + i];
            }
                double average4 = (double)sum4/(double)12;
                yearlyAvg[n] = average4;
        }

        // Print all of the yearly averages.
        for (int yr = 1900; yr <= endYear; yr++) {
            StdOut.print("Average yearly temperature for " + yr + ": ");
            StdOut.println(yearlyAvg[yr-1900]);
        }

        // Examine each year from 1900 to endYear, inclusive. If that year was
        // particularly hot (more than 2 degrees above the overall average) or
        // particularly cold (more than 2 deegrees below the overall average),
        // then print a message saying so. Also identify and print out the
        // record-setting years (a year that is hotter or colder than all all
        // previous years).
        // TODO: Add code here.

        int yearW = 0;
        int yearC = 0;
        double max = yearlyAvg[0];
        double min = yearlyAvg[0];
        int maxYr = 0;
        int minYr = 0;

        for(int i = 0; i < numYears; i++){
            if(yearlyAvg[i] < min){
                minYr = i + 1900;
                StdOut.println(minYr + " was a record setting cold year.");
                min = yearlyAvg[i];
            }else if(yearlyAvg[i] < average - 2){
                yearC = i + 1900;
                StdOut.println(yearC + " was a cold year.");
            }else if(yearlyAvg[i] > max){
                maxYr = i + 1900;
                StdOut.println(maxYr + " was a record setting hot year.");
                max = yearlyAvg[i];
            }else if (yearlyAvg[i] > average + 2){
                yearW = i + 1900;
                StdOut.println(yearW + " was a hot year.");
            }
        }
    }
}
