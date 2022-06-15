package leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 06-15
 * 算法1：两数之和
 * 问题描述：
 * 输入： nums = [2,7,11,15], target = 9
 * 输出： [0,1]
 * 解释：因为 nums[0] + nums[1] == 9，所以返回 [0, 1]。
 */












public class TwoNum {
    public static void main(String[] args) {
       int[] nn = TwoNum(18);
       for(int i:nn){
           System.out.println(i+" ");
       }
    }
    public static int[] TwoNum(int target){
        int[] nums = {6,2,5,4,5};
        //首先将数组中的数字放入map之中，key是数字value是下标备用
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            //减去目标数字与数组中的数进行对应
            int complement = target - nums[i];
            //如果减数comlement在map中存在并且下标不和i一样即可生成答案
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }

        }
        return null;
    }
    }

