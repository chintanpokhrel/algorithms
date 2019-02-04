import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private int openSites;
    private final int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        uf = new WeightedQuickUnionUF(n * n + 2);
        
        grid = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }

        /* Union top and bottom row to virtual top and bottom */
        for(int i = 1; i < n + 1; ++i) {
            uf.union(i,  0);
            uf.union(n * n + 1, n * (n - 1) + i);
        }
        
        this.n = n;
        openSites = 0;
    }

    public void open(int row, int col) {
        checkRange(row, col);

        row = row - 1;
        col = col - 1;
        
        if(grid[row][col]) {
            return;
        }
        
        grid[row][col] = true;
        connectUp(row, col);
        connectDown(row, col);
        connectLeft(row, col);
        connectRight(row, col);

        openSites++;
    }

    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        checkRange(row, col);
        int cur = xytoi(row - 1, col - 1);
        return grid[row - 1][col - 1] && uf.connected(0, cur);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if(n == 1) {
            return grid[0][0];
        }
        
        return uf.connected(0,  n * n + 1);
    }

    private void connectUp(int i, int j) {
        if(i > 0 && grid[i - 1][j]) {
            uf.union(xytoi(i, j), xytoi(i - 1, j));
        }
    }

    private void connectDown(int i, int j) {
        if (i + 1 < n && grid[i + 1][j]) {
            uf.union(xytoi(i, j), xytoi(i + 1, j));
        }
    }

    private void connectLeft(int i, int j) {
        if (j > 0 && grid[i][j - 1]) {
            uf.union(xytoi(i, j), xytoi(i, j - 1));
        }
    }

    private void connectRight(int i, int j) {
        if (j + 1 < n && grid[i][j + 1]) {
            uf.union(xytoi(i, j),  xytoi(i, j + 1));
        }
    }

    private void checkRange(int row, int col) {
        if (row > n || col > n || row <  1 || col < 1) {
            throw new IllegalArgumentException();
        }
    }

    private int xytoi(int x, int y) {
        return n * x + y + 1; /* + 1 to discount for the top site */
    }
    
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        //p.open(1,  1);
        System.out.println(p.isFull(1,  1));
        System.out.println(p.percolates());
    }
}


