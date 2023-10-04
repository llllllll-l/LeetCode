package easy;
// 27
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;

        int[] arr = (removeElement(nums, val));

        for (int i : arr) {
            System.out.println(i);
        }
    }
    // temp
    private static int[] removeElement(int[] nums, int val) {
        int[] newArray = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                newArray[index] = nums[i];
                index++;
            }
        }
        return newArray;
    }
}
