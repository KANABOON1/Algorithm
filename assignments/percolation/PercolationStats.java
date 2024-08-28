import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * experiment on the percolate system, using Monte Carlo method
 */
public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    /**
     * perform independent trials on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        double[] percolationThreshold = new double[trials];

        for (int i = 0; i < trials; i++) {
            // one time trial
            Percolation percolation = new Percolation(n);
            while (true) {
                int randomRow = StdRandom.uniformInt(1, n + 1);
                int randomCol = StdRandom.uniformInt(1, n + 1);
                percolation.open(randomRow, randomCol);
                if (percolation.percolates()) {
                    percolationThreshold[i] =  (double) percolation.numberOfOpenSites() / (n * n);
                    break;
                }
            }
        }

        this.mean = StdStats.mean(percolationThreshold);
        this.stddev = StdStats.stddev(percolationThreshold);
        this.confidenceLo = this.mean - (1.96 * this.stddev) / Math.sqrt(trials);
        this.confidenceHi = this.mean + (1.96 * this.stddev) / Math.sqrt(trials);
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return this.mean;
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return this.stddev;
    }

    /**
     * low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return this.confidenceLo;
    }
    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return this.confidenceHi;

    }

    /**
     * test client
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationExp = new PercolationStats(n, t);

        double sampleMean = percolationExp.mean();
        double sampleStddev = percolationExp.stddev();
        double sampleConfidenceLo = percolationExp.confidenceLo();
        double sampleConfidenceHi = percolationExp.confidenceHi();

        System.out.println("mean                    = " + sampleMean);
        System.out.println("stddev                  = " + sampleStddev);
        System.out.println("95% confidence interval = " + '[' + sampleConfidenceLo + ", " + sampleConfidenceHi + ']');
    }
}
