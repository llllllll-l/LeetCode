// 34
public class FindFirstAndLastPosition {
    public static void main(String[] args) {
        int[] nums = {1,4}; 
        int target = 4;

        searchRange(nums, target);

        for (int i : nums) {
            System.out.print(i);
        }
    }

    private static int[] searchRange(int[] nums, int target) {
        int[] targetPos = new int[2]; 

        if (nums.length == 0) {
            return new int[] {-1,-1};
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetPos[0] = i;
                break;
            } 
            if (i == nums.length-1) {
                return new int[] {-1,-1};
            }
        }

        for (int i = nums.length-1; i >= targetPos[0]; i--) {
            if (nums[i] == target) {
                targetPos[1] = i; 
                break;
            }
        }

        return targetPos;
    }
}
