import java.util.Random;

public class BubbleSort {
    public BubbleSort() {}

    /**
     * implement the bubble sort, which is a stable and in-place sort
     * time complexity: O(N^2)
     * space complexity: O(1)
     * @param a the array input
     */
    public static void sort(Comparable[] a) {
        // In the most complicated case: N, (N-1), ..., 1
        // it should take (N - 1) turns to sort the array
        // note: 部分有序的复杂度低于完全倒序
        for (int i = 0; i < a.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (lessThan(a[j + 1], a[j])) {
                    swap(a, j, j + 1);
                    isSorted = false;               // if swap occurs, then the array is not sorted
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * whether a is less than b
     * @param a comparable
     * @param b comparable
     * @return boolean
     */
    private static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * swap the a[i] and a[j]
     * @param a the array input
     * @param i index i
     * @param j index j
     */
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
        sort(test);
        if (isSorted(test)) {
            System.out.println("successful sort!");
        }
    }
}
