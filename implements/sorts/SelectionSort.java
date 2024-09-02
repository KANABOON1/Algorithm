import java.util.Random;

public class SelectionSort {
    public SelectionSort() {}

    /**
     * implement the selection sort
     * @param a the array input
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {    // 每一轮选择一个最小的元素并放在相应位置
            int min_index = i;
            for (int j = min_index + 1; j < a.length; j++) {
                if (lessThan(a[j], a[min_index])) {
                    min_index = j;
                }
            }
            swap(a, i, min_index);
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

    public static void main(String[] args) {
        Integer[] test = generateRandomArray(99, 1, 100);
        sort(test);
        if (isSorted(test)) {
            System.out.println("successful sort!");
        }
    }
}
