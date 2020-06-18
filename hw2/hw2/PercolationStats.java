package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int experiments;
    private double[] storage;
    private Percolation temp;
    private int number;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.number = N;
        this.experiments = T;
        if (number <= 0 || experiments <= 0) {
            throw new java.lang.IllegalArgumentException("wrong");
        }
        storage = new double[experiments];
        for (int i = 0; i < experiments; i++) {
            temp = pf.make(number);
            int counter = 0;
            while (!temp.percolates()) {
                int randCol = StdRandom.uniform(N);
                int randRow = StdRandom.uniform(N);
                if (!temp.isOpen(randRow, randCol)) {
                    temp.open(randRow, randCol);
                    counter++;

                }


            }
            double fraction = (double) counter / (N * N);
            storage[i] = fraction;

        }



    }


    public double mean() {
        return StdStats.mean(storage);
    }
    public double stddev() {


        return StdStats.stddev(storage);

    }
    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - ((1.96 * stddev() / Math.sqrt(experiments)));
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + ((1.96 * stddev() / Math.sqrt(experiments)));

    }
}

