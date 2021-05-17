import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        
        char intervalType = 'a';
        while (!(intervalType == 'p') && !(intervalType == 't')) {
            System.out.println("What type of 1-sample confidence would you like to make? \nEnter 'p' for proportions or 't' for means.");
            intervalType = s.next().charAt(0);
            if (!(intervalType == 'p') && !(intervalType == 't')) {
                System.out.println("Only enter either 'p' or 't'.");
            }
        }
        if (intervalType == 'p') {
            PInterval pInterval = getPData();
            System.out.println("We are " + (pInterval.getConfLevel() * 100) + "% confident that the true proportion lies in the interval " + Arrays.toString(pInterval.getInterval()) + ".");
        }
        else if (intervalType == 't') {
            int dataType = 0;
            while (!(dataType == 1) && !(dataType == 2)) {
                System.out.println("Would you like to enter a collection of sample data values or just the sample mean/standard deviation? Enter 1 for data values or 2 for sample mean/SD.");
                dataType = s.nextInt();
                if (!(dataType == 1) && !(dataType == 2)) {
                    System.out.println("Only enter either 1 or 2.");
                }
            }
            TInterval tInterval = getTData(dataType);
            System.out.println("We are " + (tInterval.getConfLevel() * 100) + "% confident that the true mean lies in the interval " + Arrays.toString(tInterval.getInterval()) + ".");
        }
        s.close();
    }

    public static PInterval getPData() {
        System.out.println("Enter your sample size (a whole number).");
        int sampleSize = s.nextInt();
        System.out.println("Enter your sample proportion (should be a decimal between 0 and 1).");
        double sampleProp = s.nextDouble();
        System.out.println("Enter your confidence level (should be a decimal between 0 and 1).");
        double cLevel = s.nextDouble();
        PInterval pInterval = new PInterval(sampleSize, sampleProp, cLevel);
        return pInterval;
    }

    public static TInterval getTData(int dataType) {
        if (dataType == 1) {
            System.out.println("How many data values do you have, or what is your sample size? (a whole number).");
            int sampleSize = s.nextInt();
            double[] userData = new double[sampleSize];
            double dataValue, sum = 0;
            for (int i = 0; i < sampleSize; i++) {
                System.out.println("Please enter data value " + (i + 1) + ".");
                dataValue = s.nextDouble();
                userData[i] = dataValue;
                sum += dataValue;
            }
            double sampleMean = sum / (double)sampleSize;
            double sumDiffs = 0;
            for (double value : userData) {
                sumDiffs += Math.pow((value - sampleMean), 2);
            }
            double sampleSD = Math.sqrt(sumDiffs / (double)(sampleSize - 1));
            System.out.println("Your sample mean is " + sampleMean + " and your sample standard deviation is " + sampleSD + " (using n - 1).");
            System.out.println("Enter your confidence level (a decimal between 0 and 1).");
            double cLevel = s.nextDouble();
            TInterval tInterval = new TInterval(sampleSize, sampleMean, sampleSD, cLevel);
            return tInterval;
        }
        else {
            System.out.println("Enter your sample size (a whole number).");
            int sampleSize = s.nextInt();
            System.out.println("Enter your sample mean.");
            double sampleMean = s.nextDouble();
            System.out.println("Enter your sample standard deviation.");
            double sampleSD = s.nextDouble();
            System.out.println("Enter your confidence level (a decimal between 0 and 1).");
            double cLevel = s.nextDouble();
            TInterval tInterval = new TInterval(sampleSize, sampleMean, sampleSD, cLevel);
            return tInterval;
        }
    }
}
