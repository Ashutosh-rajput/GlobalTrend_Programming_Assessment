public class area {
    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int l = 0;
        int r = arr.length - 1;
        int max = 0;

        while (l < r) {
            int width = r - l;
            int currentl = Math.min(arr[l], arr[r]);
            int area = width * currentl;
            max = Math.max(max, area);

            if (arr[l] < arr[r]) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println("The maximum area " + max);
    }
}
