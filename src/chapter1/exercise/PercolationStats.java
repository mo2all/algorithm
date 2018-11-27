package chapter1.exercise;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @Description 测试Percolation的p*值
 * @Author Skye
 * @Date 2018/11/26 19:34
 * @Version 1.0
 **/
public class PercolationStats {
    private final double[]resultOfP;
    /**
     * perform trials independent experiments on an n-by-n grid
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n <=0 && trials <=0)
            throw new IllegalArgumentException();


        resultOfP = new double[trials];

        for (int i = 0; i < trials; i++) {
            int steps=0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    steps++;
                }
            }
//            int numberOfOpenSites= percolation.numberOfOpenSites();
            resultOfP[i] = ((double) (steps))/((double) (n*n));
        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(resultOfP);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(resultOfP);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean()-((1.96*stddev())/Math.sqrt(resultOfP.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+((1.96*stddev())/Math.sqrt(resultOfP.length));
    }

    // test client (described below)
    public static void main(String[] args) {
        double currentTime = System.currentTimeMillis();
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats percolationStats = new PercolationStats(n, trials);
        double mean = percolationStats.mean();
        double stddev = percolationStats.stddev();
        double confidenceLo = percolationStats.confidenceLo();
        double confidenceHi = percolationStats.confidenceHi();
        System.out.println("mean = "+ mean+"\nstddev = " + stddev+
                "\n95% confidence interval=[ " +
                confidenceLo+", "+confidenceHi+"]");
    }
}
