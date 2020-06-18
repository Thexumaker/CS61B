package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][]grid;
    private int rowLength;
    private int openCount;
    private int virtualTop;
    private int virtualBot;
    private WeightedQuickUnionUF temp;
    private WeightedQuickUnionUF temp2;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N can't be less than or  equal to 0");
        }
        grid = new boolean[N][N];
        rowLength = N;
        virtualBot = N * N;
        virtualTop = (N * N) + 1;
        openCount = 0;
        temp = new WeightedQuickUnionUF((N * N) + 2);
        temp2 = new WeightedQuickUnionUF((N * N) + 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }


    }
    public void open(int row, int col) {
        if (row < 0 || row >= rowLength) {
            throw new java.lang.IndexOutOfBoundsException("row has to be between 0 and n-1");
        }
        if (col < 0 || col >= rowLength) {
            throw new java.lang.IndexOutOfBoundsException("col has to be between 0 and n-1");
        }
        if (!grid[row][col]) {
            grid[row][col] = true;
            openCount += 1;
        }
        int num = xyTo1D(row, col);
        if (num < rowLength) {
            temp.union(virtualTop, num);
            temp2.union(virtualTop, num);
        }
        if (num >= (rowLength * (rowLength - 1)) && num < rowLength * rowLength) {
            temp.union(virtualBot, num);

        }



        //openCount +=1;
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            temp.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            temp2.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row + 1 < rowLength && isOpen(row + 1, col)) {
            temp.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            temp2.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            temp.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            temp2.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col + 1 < rowLength && isOpen(row, col + 1)) {
            temp.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            temp2.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }



    }
    private int xyTo1D(int r, int c) {
        int result = (rowLength * r) + c;
        return result;
    }
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= rowLength) {
            throw new java.lang.IndexOutOfBoundsException("row has to be between 0 and n-1");
        }
        if (col < 0 || col >= rowLength) {
            throw new java.lang.IndexOutOfBoundsException("col has to be between 0 and n-1");
        }
        // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= rowLength) {
            throw new java.lang.IndexOutOfBoundsException("row has to be between 0 and n-1");
        }
        if (col < 0 || col >= rowLength) {
            throw new java.lang.IndexOutOfBoundsException("col has to be between 0 and n-1");
        }
        if (isOpen(row, col)) {
            if (temp2.connected(xyTo1D(row, col), virtualTop)) {
                return true;
            }

        }
        return false;

    }


    // is the site (row, col) full?
    public int numberOfOpenSites() {
        return openCount;

    }
    public boolean percolates() {
        if (temp.connected(virtualBot, virtualTop)) {
            return true;
        }
        return false;


    }

    public static void main(String[] args) {

    }   // use for unit testing (not required, but keep this here for the autograder)

}
