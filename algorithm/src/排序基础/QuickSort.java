package 排序基础;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static Random random = new Random();

    public static void sort(int [] arr){
        System.out.println("快速排序");
        //quickSort(arr,0,arr.length - 1);
//        quickSortTweWays(arr,0,arr.length - 1);
        quickSortThreeWays(arr,0,arr.length - 1);
    }

    private static void quickSortThreeWays(int[] arr, int left, int right) {
        if (right - left <= 15) {
            InsertionSort.sort(arr,left,right);
            return;
        }
        swap(arr,left,random.nextInt(right- left) + left);
        int data = arr[left];
        int leftIndex = left; //于小data的数组右边界，初始为0
        int rightIndex = right+1; //大于data的数组左边界，初始为0
        int i = left+1;//当前操作元素
        while(true){
            if (i <= right && arr[i] < data) {
                swap(arr,leftIndex+1,i);
                leftIndex++;
                i++;
            } else if (i <= right && arr[i] == data) {
                i++;
            } else {
                swap(arr,i,rightIndex - 1);
                rightIndex--;
            }
            if (i >= rightIndex) {
                break;
            }
        }
        swap(arr,left,leftIndex);
        quickSortThreeWays(arr,left,leftIndex - 1);
        quickSortThreeWays(arr,rightIndex,right);
    }

    private static void quickSortTweWays(int [] arr , int left, int right){

        if (right - left <= 15) {
            InsertionSort.sort(arr,left,right);
            return;
        }
        //随机选一个元素，防止退化成O(n ^ 2)的时间复杂度
        swap(arr,random.nextInt((right - left)) + left, left);

        int data = arr[left];
        int i = left + 1, j = right;
        while(true){
            while(i <= right && arr[i] < data){
                i++;
            }
            while(j >= left+1 && arr[j] > data){
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr,left,j);
        quickSortTweWays(arr, left, j - 1);
        quickSortTweWays(arr, j+1, right);
    }


    private static void quickSort(int[] arr, int begin, int end) {
        //优化一：如果需要排序的元素比较小，则采用插入排序
        if (end - begin <= 15) {
            InsertionSort.sort(arr,begin,end);
            return;
        }
        int p = partition(arr, begin , end);
        quickSort(arr, begin, p - 1);
        quickSort(arr, p + 1, end);
    }

    private static int partition(int[] arr, int begin, int end) {
        //随机选一个元素，防止退化成O(n ^ 2)的时间复杂度
        swap(arr,random.nextInt((end - begin)) + begin, begin);

        int data = arr[begin];
        int j = begin;
        for (int i = begin + 1; i <= end; i++) {
            if (arr[i] < data) {
                j++;
                swap(arr, j, i);
            }
        }
        //将选中元素放入数组中最终位置
        swap(arr,begin,j);
        return j;
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String [] args){
        int[] arr = {456,45,657,768,679,2,1,3,4576,67,89,234,234,12,44,576,7658,9,90,324};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
