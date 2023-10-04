package easy;
// 896
public class MonotonicArray {
    public static void main(String[] args) {
        int[] numbers = {1,2,2,3}; 
        int[] numbers1 = {6,5,4,4};
        int[] numbers2 = {1,3,2};
        
        System.out.println(isMonotonic(numbers));
        System.out.println(isMonotonic(numbers1));
        System.out.println(isMonotonic(numbers2));
    }

    public static boolean isMonotonic(int[] nums) {
        boolean increasing = false;
        boolean decreasing = false;

        increasing = isMonotonicInc(nums);
        decreasing = isMonotonicDec(nums);


        if (increasing == true || decreasing == true) {
            return true;
        } else {
            return false;
        }        
    }


    private static boolean isMonotonicDec(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]>= arr[i+1]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isMonotonicInc(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] <= arr[i+1]) {
                System.out.println(arr[i] + " "+arr[i+1]);
                continue;
            } else {
                return false;
            }
        }
        return true;

    }
}
