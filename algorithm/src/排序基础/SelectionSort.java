package 排序基础;

import java.util.Arrays;

public class SelectionSort {

    public static void sort(int [] arr){
        System.out.println("选择排序");

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length ; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }

        }
    }

    public static void main(String [] args){
        int[] arr = {58,68,47,398,75,68,145,7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
