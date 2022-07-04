package leecode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.locks.Condition;

class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>res = new LinkedList<>();
        HashSet<List<Integer>> set = new HashSet();
        if(nums.length < 3||nums.length > 3000)return res;
        Arrays.sort(nums);
        int ptr1 = 0;

        for(int i = 0;i  < nums.length-2;i++)
        {
            int ptr2 = nums.length - 1;
            int j = i+1;
            while(j <ptr2) {
                if(nums[ptr2] + nums[i] + nums[j] ==0)
                {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[ptr2]);
                    Collections.sort(temp);
                    set.add(temp);
                    j++;
                    ptr2--;
                }
                else if (nums[ptr2] + nums[i] + nums[j] > 0)
                {
                    ptr2--;
                }
                else
                {
                    j++;
                }
            }
        }
        for(List<Integer> o : set)res.add(o);
        return res;
    }
}

public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        List<List<Integer>> list =  new Solution15().threeSum(arr);
        System.out.println(list);
        for(List<Integer> i :list)
        {
            for(Integer o : i)
                System.out.println(o+",");
            System.out.println();
        }
    }
}
