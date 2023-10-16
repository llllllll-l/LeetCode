package Sorting;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {32,4,65,73,743,3,867,5,};

        int[] sortedArr = insertionSort(arr);

        for (int i : sortedArr) {
            System.out.print(i + " ");
        }
    }

    // O(N^2)
    private static int[] insertionSort(int[] arr) {
        int k;
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            for (k = i; k > 0 && tmp < arr[k-1]; k--) {
                arr[k] = arr[k-1];
            }
            arr[k] = tmp;
        }

        return arr;
    }
}
