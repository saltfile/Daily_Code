package aohiothor;


import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;

/**
 * 我早该写熟悉这些算法的
 */











public class sort_part {
    //展示数组
    public static void ShowArr(int[] arr){
        System.out.print("[");
        for (int a: arr) {
            System.out.print(a);
            System.out.print("   ");
        }
        System.out.println("]");
    }


    //冒泡排序  从小到大O(n^2)
    public static int[] BolBolSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {

                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    //选择排序   从小到大O(n^2)
    public static int[] SelectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]){
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    //插入排序 从小到大O(n^2)

    public static int[] InsertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i-1;
            while ((j >= 0)&&(temp < arr[j])){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
        return arr;
    }


    public static int[] ShellSort(int[] arr){

        for (int i = arr.length/2; i >=1; i/=2) {
            for (int j = i; j < arr.length; j++) {
                int temp = arr[j];
                int idx = j-i;
                while (idx >= 0 &&temp < arr[idx]){
                    arr[idx+i] = arr[idx];
                    idx-=i;
                }
                arr[idx+i] = temp;
            }
        }
        return arr;
    }



    public static void QuickSort(int[] arr,int left,int right){
        if(left >= 0&&right < arr.length&&right-left>=1) {

            int i = left;
            int j = right;
            int key = arr[left];

            while (i != j) {
                while (arr[j] >= key && i < j)
                    j--;
                while (arr[i] <= key && i < j)
                    i++;
                //交换
                if (i < j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }
            arr[left] = arr[i];
            arr[i] = key;
            QuickSort(arr, left, i - 1);
            QuickSort(arr, i + 1, right);
        } else return;
    }



    public static void main(String[] args) {
        int[] arr = {5,2,6,7,8,9,5,4,1,2,6,8,3,5,2};
        //1.冒泡排序
        BolBolSort(arr);
        ShowArr(arr);

        int[] arr2 = {5,2,6,7,8,9,5,4,1,2,6,8,3,5,2};
        //2.选择排序
        SelectSort(arr2);
        ShowArr(arr2);

        int[] arr3 = {5,2,6,7,8,9,5,4,1,2,6,8,3,5,2};
        //3.插入排序
        InsertSort(arr3);
        ShowArr(arr3);

        int[] arr4 = {5,2,6,7,8,9,5,4,1,2,6,8,3,5,2};
        //4.希尔排序
        ShellSort(arr4);
        ShowArr(arr4);

        int[] arr5 = {5,2,6,7,8,9,5,4,1,2,6,8,3,5,2};
        //5.快速排序
        QuickSort(arr5,0,arr5.length-1);
        ShowArr(arr5);
    }
}
