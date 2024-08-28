import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * this class simulates a Percolation system(渗流系统)
 */
public class Percolation {
    private final WeightedQuickUnionUF backwashPercolation;
    private final WeightedQuickUnionUF normalPercolation;
    private final boolean[] status;
    private final int size;
    private int numOpenSites;

    /**
     * creates n-by-n grid, with all sites initially blocked
     * time complexity: O(N^2)
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        this.numOpenSites = 0;

        this.backwashPercolation = new WeightedQuickUnionUF(n * n + 2); // build two virtual points
        this.normalPercolation = new WeightedQuickUnionUF(n * n + 1);   // deal with the backwash problem
        this.status = new boolean[n * n];
    }

    /**
     * opens the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }

        int index = getIndex(row, col);
        this.status[index] = true;       // open the grid
        this.numOpenSites++;

        if (row == 1) {                  // connect to the source
            this.backwashPercolation.union(index, this.size * this.size);
            this.normalPercolation.union(index, this.size * this.size);
        }
        if (row == this.size) {
            this.backwashPercolation.union(index, this.size * this.size + 1);
        }

        if (inRange(row - 1, col) && isOpen(row - 1, col)) {
            int upIndex = index - this.size;
            this.backwashPercolation.union(upIndex, index);
            this.normalPercolation.union(upIndex, index);
        }

        if (inRange(row + 1, col) && isOpen(row + 1, col)) {
            int downIndex = index + this.size;
            this.backwashPercolation.union(downIndex, index);
            this.normalPercolation.union(downIndex, index);
        }

        if (inRange(row, col - 1) && isOpen(row, col - 1)) {
            int leftIndex = index - 1;
            this.backwashPercolation.union(leftIndex, index);
            this.normalPercolation.union(leftIndex, index);
        }

        if (inRange(row, col + 1) && isOpen(row, col + 1)) {
            int rightIndex = index + 1;
            this.backwashPercolation.union(rightIndex, index);
            this.normalPercolation.union(rightIndex, index);
        }
    }

    /**
     * is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return this.status[getIndex(row, col)];
    }

    /**
     * is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return this.normalPercolation.find(getIndex(row, col)) ==
                this.normalPercolation.find(this.size * this.size);
    }

    /**
     * returns the number of open sites
     */
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        // check whether the two virtual point are connected
        return this.backwashPercolation.find(this.size * this.size) ==
                this.backwashPercolation.find(this.size * this.size + 1);
    }

    /**
     * check if the index is in the range
     */
    private boolean inRange(int row, int col) {
        return row >= 1 && row <= this.size && col >= 1 && col <= this.size;
    }

    /**
     * given the row and col
     * return the index
     */
    private int getIndex(int row, int col) {
        return (row - 1) * this.size + col - 1;
    }

    /**
     * test client (optional)
     */
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(2, 2);
        p.open(1, 2);
        System.out.println(p.isFull(2, 2));
        System.out.println(p.isFull(1, 1));
        System.out.println(p.isFull(3, 3));
        System.out.println(p.isFull(2, 1));
    }
}
