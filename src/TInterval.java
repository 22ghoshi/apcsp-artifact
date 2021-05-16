import org.apache.commons.math3.distribution.TDistribution;

public class TInterval {
    private int n;
    private double xbar;
    private double sigma;
    private double cLevel;
    private double[] interval = new double[2];

    public TInterval() {

    }

    public TInterval(int sampleSize, double sampleMean, double sampleSD, double confLevel) {
        this.n = sampleSize;
        this.xbar = sampleMean;
        this.sigma = sampleSD;
        this.cLevel = confLevel;
    }

    public double[] getInterval() {
        if(interval[0] == 0 && interval[1] == 0) {
            double MOE = tstar(cLevel, n) * (sigma / Math.sqrt(n));
            interval[0] = xbar - MOE;
            interval[1] = xbar + MOE;
        }
        return interval;
    }

    public double tstar(double confLevel, double sampleSize) {
        TDistribution dist = new TDistribution(sampleSize - 1);
        return dist.inverseCumulativeProbability(0.5 + (confLevel / 2));
    }
}
