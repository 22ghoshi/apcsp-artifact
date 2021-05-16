import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("What type of 1-sample confidence would you like to make? \nEnter 'p' for proportions or 't' for means.");
        char intervalType = s.next().charAt(0);

        if(intervalType == 'p') {
            System.out.println("Enter your sample size (a whole number).");
            int sampleSize = s.nextInt();
            System.out.println("Enter your sample proportion (should be a decimal between 0 and 1).");
            double sampleProp = s.nextDouble();
            System.out.println("Enter your confidence level (should be a decimal between 0 and 1).");
            double cLevel = s.nextDouble();
            PInterval pInterval = new PInterval(sampleSize, sampleProp, cLevel);
            System.out.println("We are " + (cLevel * 100) + "% confident that the true proportion lies in the interval " + Arrays.toString(pInterval.getInterval()) + ".");
        }
        else if (intervalType == 't') {
            System.out.println("Enter your sample size (a whole number).");
            int sampleSize = s.nextInt();
            System.out.println("Enter your sample mean.");
            double sampleMean = s.nextDouble();
            System.out.println("Enter your sample standard deviation.");
            double sampleSD = s.nextDouble();
            System.out.println("Enter your confidence level (should be a decimal between 0 and 1).");
            double cLevel = s.nextDouble();
            TInterval tInterval = new TInterval(sampleSize, sampleMean, sampleSD, cLevel);
            System.out.println("We are " + (cLevel * 100) + "% confident that the true proportion lies in the interval " + Arrays.toString(tInterval.getInterval()) + ".");
        }
        s.close();
    }
}
