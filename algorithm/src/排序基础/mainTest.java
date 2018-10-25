package 排序基础;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class mainTest {
    public static String FUNCTION_NAME = "sort";

    public static void main(String [] args){
        int count = 1000000;
        Random random = new Random();
        int [] arr1 = initArr(count,random);
        int [] arr2 = cory(arr1);
        int [] arr3 = cory(arr1);
        int [] arr4 = cory(arr1);
        int [] arr5 = cory(arr1);
        int [] arr6 = cory(arr1);
//        testSort(arr1, BubbleSort.class);
//        testSort(arr2,InsertionSort.class);
//        testSort(arr3,SelectionSort.class);
        testSort(arr4,MergeSort.class);
        testSort(arr6,QuickSort.class);

    }

    private static int[] cory(int[] arr) {
        int [] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i]  = arr[i];
        }
        return arr1;
    }

    private static void testSort(int[] arr,Class clazz){
        long startTime = System.nanoTime();
        try{
            Method sortMethod = clazz.getMethod(FUNCTION_NAME, int[].class);
            sortMethod.invoke(null,arr);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("没有找到指定方法");
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println(clazz.getName() + " 用时:"+time);
        for (int i = 0; i < arr.length - 1 ; i++) {
            if (arr[i] > arr[i+1]) {
                System.out.println("排序失败");
            }
        }
//        System.out.println(Arrays.toString(arr));


    }

    private static int[] initArr(int count, Random random) {
        int [] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt(count * 2);
        }
//        System.out.println(Arrays.toString(arr));

        return arr;
    }
}
