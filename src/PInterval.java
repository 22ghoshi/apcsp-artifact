import org.apache.commons.math3.distribution.NormalDistribution;

public class PInterval {
    private int n;
    private double phat;
    private double cLevel;
    private double[] interval = new double[2];
    private static NormalDistribution dist = new NormalDistribution();

    public PInterval() {

    }

    public PInterval(int sampleSize, double sampleProp, double confLevel) {
        this.n = sampleSize;
        this.phat = sampleProp;
        this.cLevel = confLevel;
    }

    public double[] getInterval() {
        if(interval[0] == 0 && interval[1] == 0) {
            double MOE = zstar(cLevel) * (Math.sqrt((phat * (1 - phat)) / n));
            interval[0] = phat - MOE;
            interval[1] = phat + MOE;
        }
        return interval;
    }

    public double zstar(double confLevel) {
        return dist.inverseCumulativeProbability(0.5 + (confLevel / 2));
    }

    public double getConfLevel() {
        return this.cLevel;
    }
}

