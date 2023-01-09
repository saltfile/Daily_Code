package aohiothor;


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












public class hash_part {
    public static void main(String[] args) {
//        System.out.println(new IsAnagram().isAnagram("nagaram","anagram"));


        int[] n1 = {1,2,2,1};
        int[] n2 = {2,2,3};
        int[] res = new Intersection().intersection(n2,n1);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+"     ");
        }





    }
}
