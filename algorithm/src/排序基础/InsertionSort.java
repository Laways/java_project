package 排序基础;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int [] arr){
        System.out.println("插入排序");
        for (int i = 1; i < arr.length; i++) {
            //当前需要插入的节点
            int temp = arr[i];
            int j;
            //寻找到应该插入节点位置之前所有节点后移一位
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j-1];
            }
            //找到插入节点位置后赋值，节省元素交换时间开销
            arr[j] = temp;
        }
    }

    public static void sort(int [] arr, int l, int r){
        for (int i = l + 1; i <= r; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > l && arr[j - 1] > temp ; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }
    public static void main(String [] args){
        int[] arr = {58,68,47,398,75,68,145,7};
        sort(arr,2,7);
        System.out.println(Arrays.toString(arr));
    }
}
