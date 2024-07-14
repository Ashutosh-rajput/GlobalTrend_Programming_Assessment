import java.util.Arrays;

public class KthLargestElement {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        Arrays.sort(nums);
        int kthLargest = nums[nums.length - k];
        System.out.println("The " + k + "th largest element is: " + kthLargest);
    }
}
