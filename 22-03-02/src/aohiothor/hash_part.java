package aohiothor;


import java.util.*;

/**
 * 242. 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 *
 *
 *
 * 示例1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 */

class IsAnagram{
    public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int idx =s.charAt(i)-'a';
            arr[idx]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i)-'a';
            arr[idx]--;
        }

        for(int o : arr){
           if (o!=0)return false;
        }
        return true;
    }
}

/**
 * 349. 两个数组的交集
 *
 * 给定两个数组nums1和nums2 ，返回 它们的交集。输出结果中的每个元素一定是 唯一 的。
 * 我们可以 不考虑输出结果的顺序 。
 *
 */


class Intersection{
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> res = new HashSet<>();
        HashSet<Integer> check = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            check.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
           if (check.contains(nums2[i])){
               res.add(nums2[i]);
           }
        }

        int[] ints = res.stream().mapToInt(Integer::intValue).toArray();
        return ints;
    }
}


/**
 * 202. 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：
 *
 *     对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 *     然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 *     如果这个过程 结果为1，那么这个数就是快乐数。
 *
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 */

class IsHappy{
    public boolean isHappy(int n) {
        HashSet set = new HashSet();
        while (n != 1 && !set.contains(n)){
            set.add(n);
            n = getNum(n);
        }
        return n == 1;
    }

    public int getNum(int n){
        int sum = 0;
        while (n>0) {
            int temp = n % 10;
            sum += temp * temp;
            n = n / 10;
        }
        return sum;
    }


}

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *  第一种方式我直接双循环莽过
 *  第二种使用map过滤掉没必要的第二次循环
 */
class TwoSum{
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0&&nums == null)return res;


        for (int i = 0; i < nums.length; i++) {
            int r = target - nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] == r){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0&&nums == null)return res;

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int ids = target-nums[i];
            if (map.containsKey(ids)){
                res[0] = map.get(ids);
                res[1] = i;
                break;
            }
            map.put(nums[i],i);

        }
        return res;
    }
}


/**
 * 454. 四数相加 II
 *
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 */

class FourSumCount{
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer> map = new HashMap<>();
        //先统计num1+num2
        for (int n1:nums1){
            for (int n2:nums2) {
                map.put(n1+n2,map.getOrDefault(n1+n2,0)+1);
            }
        }
        int count = 0;

        for (int n3:nums3){
            for (int n4:nums4){
                int key = 0-(n3+n4);
                if (map.containsKey(key)){
                    count+= map.get(key);
                }
            }
        }
        return count;

    }
}


/**
 * 383. 赎金信
 *
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */


class CanConstruct{
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map = new HashMap<>();

        for(char c:ransomNote.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }


        for (char c:magazine.toCharArray()){
            if (map.containsKey(c)){
                if (map.get(c) > 0){
                    int idx = map.get(c);
                    map.put(c,idx-1);
                }
            }

        }

        for (Character key : map.keySet()){
            if (map.get(key)!=0){
                return false;
            }
        }
        return true;


    }
}


/**
 * 15. 三数之和
 *
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。// 本来想着用哈希但觉得太鸡儿复杂就选择三指针
 */

class ThreeSum{

    public List<List<Integer>> threeSum(int[] nums) {
        //先定义一个结果集,把nums给排序
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        //遍历这个数组，按照a+b+c=0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)return result;
            //防止元素已经用过重复现象
            if( i > 0&&nums[i] == nums[i-1])continue;

            int left = i+1;
            int right = nums.length-1;

            //保证边界
            while (right > left) {
                //双指针移动
                if (nums[i] + nums[left] + nums[right] > 0)
                    right--;


                else if (nums[i] + nums[left] + nums[right] < 0)
                    left++;


                else {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(nums[i]);
                    arr.add(nums[left]);
                    arr.add(nums[right]);
                    result.add(arr);

                    //后两位去重
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    //最后同时向中间移动位置
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

}


/**
 * 18. 四数之和
 *
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 *     0 <= a, b, c, d< n
 *     a、b、c 和 d 互不相同
 *     nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * 你可以按 任意顺序 返回答案 。
 *
 *
 * 跟上面一样双指针的puls用法
 */



class FourSum{
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //先定义一个结果集,把nums给排序
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        //首先是第一层循环 a+b+c+d = target

        for (int a = 0; a < nums.length; a++) {
            if (nums[a] > 0 && nums[a] > target) return result;

            if (a > 0 && nums[a] == nums[a - 1]) continue;//第一个数字去重

            for (int b = a + 1; b < nums.length; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;

                int left = b + 1;
                int right = nums.length - 1;


                //确定边界
                while (right > left) {
                    int sum = nums[a] + nums[b] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        List<Integer> arr = new ArrayList<>();
                        arr.add(nums[a]);
                        arr.add(nums[b]);
                        arr.add(nums[left]);
                        arr.add(nums[right]);
                        result.add(arr);
                        //最后后两位去重
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        right--;
                        left++;
                    }
                }
            }

        }
        return result;
    }
}





public class hash_part {
    public static void main(String[] args) {
//        System.out.println(new IsAnagram().isAnagram("nagaram","anagram"));


//        int[] n1 = {1,2,2,1};
//        int[] n2 = {2,2,3};
//        int[] res = new Intersection().intersection(n2,n1);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i]+"     ");
//        }
//        int[] n3 = {3,2,4};
//        System.out.println(new TwoSum().twoSum2(n3,6));
//        System.out.println(new CanConstruct().canConstruct("aa","sdfsdaaa"));

        int[] arr = new int[]{
                1,0,-1,0,-2,2

};


        System.out.println(new FourSum().fourSum(arr,0));
//        System.out.println(new ThreeSum().threeSum(arr));


    }
}
