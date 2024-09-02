import java.util.Random;

public class InsertionSort {

    public InsertionSort() {}

    /**
     * implement the insertion sort(移动法插入排序)
     * @param a the array input
     */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable t = a[i];       // 插入的是a[i]
            int j = i;                     // 当前确定可以插入的位置
            for (; j > 0; j--) {
                if (lessThan(t, a[j - 1])) {
                    a[j] = a[j - 1];     // 每次都将item向后挪一位
                }
                else {
                    break;
                }
            }
            a[j] = t;
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
