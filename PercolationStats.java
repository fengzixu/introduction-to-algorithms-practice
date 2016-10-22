import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by xuran on 2016/10/12.
 */
public class PercolationStats {
    private int times;
    private double[] thresholdArray;
    private double sampleMean;
    private double standev;

    public PercolationStats(int n, int trials) {
        times = trials;
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        thresholdArray = new double[trials];
        for (int i = 0; i < trials; i++) {
            int count = 0;
            Percolation obj = new Percolation(n);
            while (!obj.percolates()) {
                int randomx = StdRandom.uniform(1, n + 1);
                int randomy = StdRandom.uniform(1, n + 1);
                if (obj.isOpen(randomx, randomy)) {
                    continue;
                }

                obj.open(randomx, randomy);
                count++;
            }


            thresholdArray[i] = (double) count / ((double) n * (double) n);
        }
    }

    public double mean() {
        sampleMean = StdStats.mean(thresholdArray);
        return sampleMean;
    }

    public double stddev() {
        standev = StdStats.stddev(thresholdArray);
        return standev;
    }

    public double confidenceLo() {
        return sampleMean - (1.96 * standev) / Math.sqrt(times);
    }

    public double confidenceHi() {
        return sampleMean + (1.96 * standev) / Math.sqrt(times);
    }

    public static void main(String[] args) {
        int n;
        int trials;
        while (true) {
            n = StdIn.readInt();
            trials = StdIn.readInt();
            PercolationStats obj = new PercolationStats(n, trials);
            StdOut.printf("%-24s = " + obj.mean() + "\n", "mean");
            StdOut.printf("%-24s = " + obj.stddev() + "\n", "stddev");
            StdOut.printf("%-24s = ", "95% confidence interval");
            StdOut.printf(obj.confidenceLo() + ", " + obj.confidenceHi() + "\n");
        }
    }

}
