package 排序基础;

import java.util.Arrays;

/**
 * 选择排序
 */
public class BubbleSort {

    public static void sort (int [] arr){
        System.out.println("冒泡排序");

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i] ) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //选择排序
    public static <T extends Comparable<? super T>> void sort2(T[] arr){
        System.out.println("开始排序");

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[i]) < 0 ) {
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    public static void main(String [] args){
        Integer[] arr = {58,68,47,398,75,68,145,7};
        sort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
