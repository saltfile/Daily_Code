package leecode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 问题描述:
 *  两个排序数组的中位数
 *  给定两个排序数组nums1和nums2大小m，n分别返回两个排序数组的中位数。
 *  举例:
 *  输入： nums1 = [1,3], nums2 = [2]
 * 输出： 2.00000
 * 解释：合并数组 = [1,2,3]，中位数为 2。
 *
 * 输入： nums1 = [1,2], nums2 = [3,4]
 * 输出： 2.50000
 * 解释：合并数组 = [1,2,3,4]，中位数为 (2 + 3) / 2 = 2.5。
 */
public class Array_Median {
    public static void main(String[] args) {
        int[] arr1 = {1,4};
        int[] arr2 = {2,3};
        int[] arr3 = {2};
        int[] arr4 = {1,3};
        System.out.println(new Array_Median().findMedianSortedArrays(arr3,arr4));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> mergedList = new ArrayList();

        for(int i = 0; i < nums1.length; i++){
            mergedList.add(nums1[i]);
        }

        for(int j = 0; j < nums2.length; j++){
            mergedList.add(nums2[j]);
        }

        Collections.sort(mergedList);

        int lens = mergedList.size()%2;
        if (lens == 0){
            int l = mergedList.size()/2;
            System.out.println(l);
            return (double) (mergedList.get(l)+mergedList.get(l-1))/2;
        }else {
            int l = mergedList.size()/2;
            return (double) mergedList.get(l);
        }
    }




}
