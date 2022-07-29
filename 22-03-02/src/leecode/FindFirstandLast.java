package leecode;

import java.util.Arrays;

/**
 * nums给定一个按非降序排序的整数数组，找到给定target值的开始和结束位置。
 *
 * 如果target在数组中找不到，则返回[-1, -1]。
 *
 * 您必须编写具有 O(log n)运行时复杂性的算法。
 * 例：
 * 输入： nums = [5,7,7,8,8,10]，目标 = 8
 * 输出： [3,4]
 */
class Solution34{
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if(nums.length <= 0)return res;

        int left = 0;
        int right = nums.length-1;
        int mid = 0;


        //二分查找
        while (left <= right)
        {
            mid = (left + right) / 2;
            if (nums[mid] < target)
            {
                left = mid + 1;
            }
            else if (nums[mid] > target)
            {
                right = mid - 1;
            }
            else
            {
                break;
            }
        }
        if (nums[mid] != target)return res;
        //赋值
        int n = nums.length, i = mid, j = mid;

        // For first index
        while(i>0 && nums[i-1] == target) i--;
        // For last index
        while(j < n-1 && nums[j+1] == target) j++;
        res[0] = i;
        res[1] = j;


        return res;
    }
}




public class FindFirstandLast {
    public static void main(String[] args) {
        int[] nums = {5,7,8,8,10};
        int[] res = new Solution34().searchRange(nums,6);
        for(int o:res){
            System.out.println(o);
        }
    }






}
