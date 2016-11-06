import edu.princeton.cs.algs4.StdOut;

/**
 * Created by xuran on 2016/11/1.
 */
public class InsertSort {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            int pos = 0;
            Comparable min = a[i];
            for (int j = i - 1; j >= 0 && less(min, a[j]); j--) {
                a[j + 1] = a[j];
                pos = j;
            }

            a[pos] = min;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }

        StdOut.println();
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"4", "3", "2", "1"};
        sort(a);
        show(a);
    }

}
