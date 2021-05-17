import org.apache.commons.math3.distribution.TDistribution;

public class TInterval {
    private int n;
    private double xbar;
    private double sigma;
    private double cLevel;
    private double[] interval = new double[2];
    private TDistribution dist;

    public TInterval() {

    }

    public TInterval(int sampleSize, double sampleMean, double sampleSD, double confLevel) {
        this.n = sampleSize;
        this.xbar = sampleMean;
        this.sigma = sampleSD;
        this.cLevel = confLevel;
        this.dist = new TDistribution(sampleSize - 1);
    }

    public double[] getInterval() {
        if(interval[0] == 0 && interval[1] == 0) {
            double MOE = tstar(cLevel) * (sigma / Math.sqrt(n));
            interval[0] = xbar - MOE;
            interval[1] = xbar + MOE;
        }
        return interval;
    }

    public double tstar(double confLevel) {
        return dist.inverseCumulativeProbability(0.5 + (confLevel / 2));
    }

    public double getConfLevel() {
        return this.cLevel;
    }
}
