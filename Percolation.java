/**
 * Created by xuran on 16/9/20.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] sites;
    private int n;
    private int numbers;
    private WeightedQuickUnionUF wquf;
    private WeightedQuickUnionUF wqufEx;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        numbers = n * n + 1;
        sites = new boolean[numbers];
        wquf = new WeightedQuickUnionUF(numbers + 1);
        wqufEx = new WeightedQuickUnionUF(numbers);
        for (int i = 1; i <= n * n; i++) {
            sites[i] = false;
        }

        virtualTop = 0;
        virtualBottom = numbers;
        this.n = n;
    }

    public boolean isOpen(int i, int j) {
        checkoundary(i, j);
        return sites[(i - 1) * n + j];
    }

    private void connectNeighbor(int si, int sj, int ei, int ej) {
        checkoundary(si, sj);
        checkoundary(ei, ej);

        if (!isOpen(si, sj) || !isOpen(ei, ej)) {
            return;
        }

        int positionS = (si - 1) * n + sj;
        int positionE = (ei - 1) * n + ej;
        if (!wquf.connected(positionS, positionE)) {
            wquf.union(positionS, positionE);

        }

        if (!wqufEx.connected(positionS, positionE)) {
            wqufEx.union(positionS, positionE);

        }
        return;
    }

    public boolean isFull(int i, int j) {
        checkoundary(i, j);
        if (wqufEx.find(virtualTop) == wqufEx.find((i - 1) * n + j)) {
            return true;
        }

        return false;
    }

    public boolean percolates() {
        if (wquf.find(virtualTop) == wquf.find(virtualBottom)) {
            return true;
        }

        return false;
    }

    public void open(int i, int j) {
        checkoundary(i, j);
        sites[(i - 1) * n + j] = true;
        if (i == 1) {
            wquf.union((i - 1) * n + j, virtualTop);
            wqufEx.union((i - 1) * n + j, virtualTop);
        }
        if (i == n) {
            wquf.union((i - 1) * n + j, virtualBottom);
        }

        if (i - 1 >= 1) {
            connectNeighbor(i - 1, j, i, j);
        }

        if (i + 1 <= n) {
            connectNeighbor(i + 1, j, i, j);
        }

        if (j - 1 >= 1) {
            connectNeighbor(i, j - 1, i, j);
        }

        if (j + 1 <= n) {
            connectNeighbor(i, j + 1, i, j);
        }
    }

    private void checkoundary(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new IndexOutOfBoundsException();
        }
    }

}
