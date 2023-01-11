package aohiothor;


import java.util.HashMap;
import java.util.HashSet;

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
                if (map.get(c) <= 0){
                    map.remove(c);
                }else {
                    int idx = map.get(c);
                    map.put(c,idx-1);
                }
            }

        }

        if (map.size()==0)return true;
        else return false;


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




    }
}
