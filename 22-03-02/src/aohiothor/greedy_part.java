package aohiothor;


import java.util.Arrays;

/**
 * 455. 分发饼干
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，
 * 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 */
class FindContentChildren{
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int res = 0;
        for (int i = 0; i < s.length&&res < g.length; i++) {
            if (s[i]>=g[start]){
                start++;
                res++;
            }
        }
        return res;
    }
}

/**
 * 376. 摆动序列
 *
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 *
 *     例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 *     相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 *
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 */

class WiggleMaxLength{
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <=1)return nums.length;

        int diff = 0;
        int prevdiff = 0;
        int res = 1;

        for (int i = 0; i < nums.length-1; i++) {
            diff = nums[i+1]-nums[i];
            //判断是否是前一个正后一个负或者相反的情况
            if (diff >0&&prevdiff <= 0){
                res++;
                prevdiff = diff;
            }

            if (diff < 0&&prevdiff >=0){
                res++;
                prevdiff = diff;
            }

        }
        return res;
    }
}

public class greedy_part {
    public static void main(String[] args) {
        int[] sum = new int[]{1,7,4,9,2,5};
        System.out.println(new WiggleMaxLength().wiggleMaxLength(sum));
    }
}
