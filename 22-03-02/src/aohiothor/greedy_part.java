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

/**
 * 53. 最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 */
class MaxSubArray{
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            if (sum>res){
                res = sum;
            }
            if (sum <= 0) sum = 0;
        }
        return res;
    }
}

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 * 贪心算法这里其实就是光看赚到的钱就可以了
 */
class MaxProfit{
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res+=Math.max(prices[i]-prices[i-1],0);
        }
        return res;
    }
}

/**
 * 55. 跳跃游戏
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *  就是说这一步能否覆盖到下面的的步骤
 */

class CanJump{
    public boolean canJump(int[] nums) {
        int idx = 0;
        if (nums.length == 1)return true;
        for (int i = 0; i <= idx; i++) {
            idx = Math.max(i+nums[i],idx);
            if (idx >= nums.length-1)return true;
        }
        return false;

    }
}

/**
 * 45. 跳跃游戏 II
 *
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 *     0 <= j <= nums[i]
 *     i + j < n
 *
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *
 */
class Jump{
    public int jump(int[] nums) {
        int cur = 0;
        int next = 0;
        int res = 0;

        for (int i = 0; i < nums.length-1; i++) {
            next = Math.max(i+nums[i],next );
            if (i == cur){
                cur = next;
                res+=1;
            }
        }
        return res;
    }
}


public class greedy_part {
    public static void main(String[] args) {
        int[] sum = new int[]{1,2,3,4,5};
        System.out.println(new MaxProfit().maxProfit(sum));
    }
}
