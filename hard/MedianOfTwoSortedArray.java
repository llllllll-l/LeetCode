package hard;
import java.util.*;

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        int[] nums1 = {1,2}, nums2 = {3,4};
        //int[] nums1 = {1,3}, nums2 = {2};

        findMedianSortedArrays(nums1, nums2);

    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] con = connectArrays(nums1, nums2);

        if (con.length%2 == 0) {
            double median = (double)(con[con.length/2] + con[con.length/2 -1])/2;
            return median;
        } else {
            System.out.println(con[con.length/2]);
            return con[con.length/2];
        }
    }

    private static int[] connectArrays(int[] nums1, int[] nums2) {
        int[] both = new int[nums1.length + nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            both[i] = nums1[i];
        }

        for (int i = 0; i < nums2.length; i++) {
            both[i + nums1.length] = nums2[i];
        }

        Arrays.sort(both);

        return both;
    }
}
