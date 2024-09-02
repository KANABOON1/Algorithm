public class Solution {
    public static int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int max_index = i;
            for (int j = max_index + 1; j < nums.length; j++) {
                if (nums[j] > nums[max_index]) {
                    max_index = j;
                }
            }
            swap(nums, i, max_index);
        }

        return nums[k - 1];
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1};
        System.out.println(findKthLargest(test, 1));
    }
}
