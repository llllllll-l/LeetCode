package Sorting;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};

        int[] sortedArr = shellSort(arr);

        for (int i : sortedArr) {
            System.out.print(i+" ");
        }
    }

    private static int[] shellSort(int[] arr) {

        int k;

        for (int gap = arr.length/2; gap > 0; gap /=2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                for (k = i; k >= gap && tmp < arr[k -gap]; k -= gap) {
                    arr[k] = arr[k - gap];
                }
                arr[k] = tmp;
            }
        }

        return arr;
    }
}
