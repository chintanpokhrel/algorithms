import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final double[] thresholds;
	private int n;
	private static final double CONFIDENCE_CONST = 1.96;
	
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		
		this.n = n;
		thresholds = new double[trials];
		
		for(int i=0; i<trials; ++i) {
			Percolation p = new Percolation(n);
			thresholds[i] = (perform(p)*1.0)/n;
		}
	}
	
	private int perform(Percolation p) {
	    while (!p.percolates()) {
	        int random = StdRandom.uniform(1, n+1);
            int row = random;
            random = StdRandom.uniform(1, n+1);
            int col = random;
            if(!p.isOpen(row, col)) {
                p.open(row, col);
            }
	    }
		return p.numberOfOpenSites();
	}
	
	public double mean() {
		return StdStats.mean(thresholds);
	}
	
	public double stddev() {
	    if (thresholds.length == 1) {
	        return Double.NaN;
	    }
	    
		return StdStats.stddev(thresholds);
	}
	
	public double confidenceLo() {
	    if (thresholds.length == 1) {
            return Double.NaN;
        }
	    
		return mean() - (CONFIDENCE_CONST * stddev() / java.lang.Math.sqrt(thresholds.length));
	}
	
	public double confidenceHi() {
	    if (thresholds.length == 1) {
            return Double.NaN;
        }
		return mean() + (CONFIDENCE_CONST * stddev() / java.lang.Math.sqrt(thresholds.length));
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, trials);
		StdOut.printf("mean                    = %f\n", ps.mean());
		StdOut.printf("stddev                  = %f\n", ps.stddev());
		StdOut.printf("95% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
	}
}


