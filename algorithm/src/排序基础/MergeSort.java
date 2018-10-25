package 排序基础;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int [] arr){
        System.out.println("归并排序");

        mergeSort(arr,0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        //优化一：如果需要排序的元素比较小，则采用插入排序
        if (right - left <= 15) {
            InsertionSort.sort(arr,left,right);
            return;
        }
        int mid = left / 2 + right / 2;
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+ 1,right);
        //优化二：如果需要归并的两个数组已经有序，则跳过归并步骤
        if (arr[mid] > arr[mid + 1]) {
            merge(arr,left,mid,right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int [] tempArr = new int [right - left + 1];
        for (int i = left; i <= right; i++) {
            tempArr[i - left] = arr[i];
        }
        int arrIndex = left;
        int leftIndex = 0;
        int midIndex = mid - left;
        int rightIndex = midIndex + 1;
        for (int i = 0; i < tempArr.length; i++) {
            if (leftIndex > midIndex) {
                //左边数组到达边界
                arr[arrIndex] = tempArr[rightIndex];
                rightIndex++;
            } else if (rightIndex > tempArr.length-1) {
                //右边数组到达边界
                arr[arrIndex] = tempArr[leftIndex];
                leftIndex++;
            }else if (tempArr[leftIndex] < tempArr[rightIndex]) {
                arr[arrIndex] = tempArr[leftIndex];
                leftIndex++;
            } else {
                arr[arrIndex] = tempArr[rightIndex];
                rightIndex++;
            }
            arrIndex++;
        }
    }

    public static void mergeSortBU(int [] arr){
        for (int size = 1; size <= arr.length; size += size) {
            //i+size<arr.length:第二个数组的左边界要小于总数组长度，保证有两个数组归并
            for (int i = 0; i+size < arr.length; i = i + 2 * size) {
                merge(arr,i , i+size - 1, Math.min(i+ 2 * size - 1,arr.length - 1));
            }
        }
    }

    public static void main(String [] args){
        int[] arr = {18, 13, 9, 18, 2, 8, 16, 15, 12, 16};
        mergeSortBU(arr);
        System.out.println(Arrays.toString(arr));
    }
}











