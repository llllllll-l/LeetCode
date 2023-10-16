package Sorting;

public class MergeSort {

    private static int iteartion = 0;

    public static void main(String[] args) {
        int[] arr = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
        
        int[] sortedArr = mergeSort(arr);

        System.out.println("Returned sorted array:");
        for (int i : sortedArr) {
            System.out.print(i + " ");
        }
    }

    private static int[] mergeSort(int[] arr) {
        int[] tempArr = new int[arr.length];

        int[] sortedArr = mergeSort(arr, tempArr, 0, arr.length -1);
        return sortedArr;

    }

    private static int[] mergeSort(int[] arr, int[] tempArr, int left, int right) {
        int[] sortedArr = tempArr;
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(arr, tempArr, left, center);
            mergeSort(arr, tempArr, center+1, right);
            sortedArr = merge(arr, tempArr, left, center+1, right);
        }
       
        return sortedArr;
    }

    private static int[] merge(int[] arr, int[] tempArr, int left, int right, int rightEnd) {
        int leftEnd = right-1;
        int tmpPos = left;
        int numElements = rightEnd-left +1;
        iteartion++;

        while (left <= leftEnd && right <= rightEnd) {
            if (arr[left] < arr[right]) {
                tempArr[tmpPos++] = arr[left++];
            } else {
                tempArr[tmpPos++] = arr[right++];
            }
        }

        while (left <= leftEnd) {
            tempArr[tmpPos++] = arr[left++];
        }

        while (right <= rightEnd) {
            tempArr[tmpPos++] = arr[right++];
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = tempArr[rightEnd];
        }

        System.out.println("Step: "+iteartion);
        for (int i : arr) {
            System.out.print(i+ " ");
        }
        System.out.println();
        return arr;
    }
}
