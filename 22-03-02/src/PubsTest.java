import java.util.ArrayList;
import java.util.Arrays;

public class PubsTest {
    public static void main(String[] args) {

        int[] arr = {1,45,3,7,5,2,5,8,8,2,8,123,67,90};
            quickSort(arr,0, arr.length-1);
//        MaoPao(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"    ");
        }
    }

    //冒泡排序
    public static void MaoPao(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }



    }


    public static void QK(int[] arr,int left,int right){

      if (left >= 0 && right < arr.length && right - left >= 1){
          int l = left;
          int r = right;
          int key = arr[left];

          while (l != r){
              while (arr[r] >= key && l < r){
                  r--;
              }
              while (arr[l] <= key && l < r){
                  l++;
              }
              if (l < r){
                  int temp = arr[l];
                  arr[l] = arr[r];
                  arr[r] = arr[l];
              }
          }
          arr[left] = arr[l];
          arr[l] = key;
          QK(arr,left,l - 1);
          QK(arr,l + 1,right);
      }

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


    public static void quickSort(int[] nums, int left, int right){
        if(left >= 0 && right < nums.length && right - left >= 1){
            int i = left;
            int j = right;
            int key = nums[left];

            while(i != j){
                while(key <= nums[j] && i < j){
                    j--;
                }
                while(key >= nums[i] && i < j){
                    i++;
                }

                if(i < j){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
            nums[left] = nums[i];
            nums[i] = key;

            quickSort(nums,left,i - 1);
            quickSort(nums,i + 1,right);
        }
    }



}