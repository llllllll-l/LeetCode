package Sorting;

public class QuickSort {
    private static final int CUTOFF = 10;

    public static void main(String[] args) {
        int[] arr = {49, 4, 56, 57, 34, 76, 64, 54, 73, 21, 94, 10, 25, 19, 25, 70, 9, 49, 79, 31, 12, 53, 25, 79, 22, 86, 25, 62, 51, 57, 87, 54, 83, 50, 89, 72, 0, 39, 30, 82, 1, 99, 16, 49, 2, 45, 28, 18, 24, 50};
        int[] sortedArr = quickSort(arr);

        for (int i : sortedArr) {
            System.out.print(i+" ");
        }
    }

    private static int[] quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
        return arr;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left + CUTOFF <= right) {
            int pivot = median3(arr, left, right);

            int l = left, r = right;
            for(;;) { // infinite looop
                while (arr[++l] < pivot) {}
                while (arr[--r] > pivot) {}
                if (l < r) {
                    swap(arr, l, r);
                } else {
                    break;
                }
            }

            swap(arr, l, r-1);

            quickSort(arr, left, l-1);
            quickSort(arr, r+1, right);
        } else {
            System.out.println("Insertion");
            insertionSort(arr, left, right);
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            int key = arr[i]; 
            int j = i -1;
            
            while (j >= left && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    private static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    private static int median3(int[] arr, int left, int right) {
        int center = (left + right) / 2;
        if (arr[center] < arr[left]) {
            swap(arr, left, center);
        }
        if (arr[right] < arr[left]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[center]) {
            swap(arr, center, right);
        }

        swap(arr, center, right-1);
        return arr[right-1];
    }
}
