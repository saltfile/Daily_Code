package leecode;

import java.util.*;

/**
 * nums给定一个长度为 的整数数组n和一个整数target，找出其中三个整数nums的和最接近target。
 * 返回三个整数之和。
 * 您可以假设每个输入都只有一个解决方案。
 */
class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        if(nums.length < 3||nums.length >  1000)return 0;
        Arrays.sort(nums);
        int res = 0;
        int sum = 0;
        for(int i = 0;i  < nums.length-2;i++)
        {
            int ptr2 = nums.length - 1;
            int j = i+1;
            while(j <ptr2)
            {
                sum = nums[ptr2] + nums[i] + nums[j];
                int tans = Math.abs(target-sum);
                if(tans < diff)
                {
                    res = sum;
                    diff = tans;
                }
                if(sum == target)
                {
                  return target;
                }
                else if (sum > target)
                {
                    ptr2--;
                }
                else
                {
                    j++;
                }
            }
        }

        return res;
    }
}
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums  = {1,2,4,8,16,32,64,128};
        System.out.println(new Solution16().threeSumClosest(nums,82));
    }
}
