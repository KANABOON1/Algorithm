import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * implement the in-place merge sort
 */
public class MergeSort {
    public MergeSort() {}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     * @param order choose which algorithm to use
     */
    public static void sort(Comparable[] a, String order) {
        if (order.equals("top-down")) {
            // 以参数的形式传入一个辅助数组，避免每次Merge都创建一个新的数组
            Comparable[] aux = new Comparable[a.length];
            downSort(a, aux, 0, a.length - 1);
        }
        else if (order.equals("bottom-up")) {
            Comparable[] aux = new Comparable[a.length];
            upSort(a, aux, 0, a.length - 1);
        }
        else {
            throw new IllegalArgumentException("The order is invalid.");
        }
    }

    /**
     * use the bottom-up merge sort, 循序渐进的思想
     * @param a the array to be sorted
     */
    private static void upSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        for (int sz = 1; sz < a.length; sz *= 2) {        // assume that the size=1 array has already been sorted
            for (int j = 0; j < a.length; j += 2 * sz) {
                merge(a, aux, j, Math.min(j + sz - 1, a.length - 1), Math.min(j + 2 * sz -1, a.length - 1));
            }
        }
    }

    /**
     * sort the array of [lo, hi], 化整为零的思想
     * time complexity: [1/2 * NlogN, NlogN]
     * @param a the array input
     * @param lo the lo index(included)
     * @param hi the hi index(included)
     */
    private static void downSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) {   // base case
            return;
        }
        downSort(a, aux, lo, (lo + hi) / 2);
        downSort(a, aux, (lo + hi) / 2 + 1, hi);
        // add a condition to shorten the running time
        if (a[(lo + hi) / 2].compareTo(a[(lo + hi) / 2 + 1]) <= 0) {
            return;
        }
        merge(a, aux, lo, (lo + hi) / 2, hi);
    }

    /**
     * merge the two arrays between[lo, mid] [mid + 1, hi]
     * time complexity: [1/2 * N, N]
     * @param a the input array
     * @param lo the lowest index
     * @param mid the middle index
     * @param hi the highest index
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i < hi + 1; i++) {
            aux[i] = a[i];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k < hi + 1; k++) {
            if (i == mid + 1) {      // write the boundary condition first
                a[k] = aux[j++];
            }
            else if (j == hi + 1) {
                a[k] = aux[i++];
            }
            else if (aux[i].compareTo(aux[j]) <= 0) {
                a[k] = aux[i++];
            }
            else if (aux[i].compareTo(aux[j]) > 0) {
                a[k] = aux[j++];
            }
        }
    }

    /**
     * Check if array is sorted - useful for debugging.
     * @param a the input array
     * @return boolean
     */
    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * generate a random array of size N
     */
    private static Integer[] generateRandomArray(int N, int min, int max) {
        Random random = new Random();
        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    public static void main(String[] args) {
        Integer[] test = generateRandomArray(99, 1, 100);
//        sort(test, "top-down");
        sort(test, "bottom-up");
        assert isSorted(test);
        System.out.println("successful sort!");
    }
}
