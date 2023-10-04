package medium;
// 162

public class TwoSumInputArray {

    public static void main(String[] args) {
        int [] numbers = {2,7,11,15}; 
        int target = 9;

        int[] ans = twoSum(numbers, target);

        System.out.print("[");
        for (int i : ans) {
            System.out.print(i+",");
        }
        System.out.print("]");
    }

    private static int[] twoSum(int[] numbers, int target) {
        int fPoint = 0, bPoint = numbers.length -1;

        while (fPoint < bPoint) {
            int complement = numbers[fPoint] + numbers[bPoint];

            if (complement == target) {
                return new int[] {numbers[fPoint], numbers[bPoint]}; 
            } else if (complement < target) {
                bPoint--;
            } else {
                fPoint++;
            }
        }

        return new int[] {-1,-1};
    }
}
