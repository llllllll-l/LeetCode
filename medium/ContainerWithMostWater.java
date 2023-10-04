package medium;
// 11
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxArea(height));
    }

    private static int maxArea(int[] height) {
        int low = 0, high = height.length-1, maxArea = 0;


        while (low < high) {
            int curent = Math.min(height[low], height[high]) * (high - low);
            maxArea = Math.max(maxArea, curent);

            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }

        return maxArea;
    }
}
