package aohiothor;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

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


/**
 * 1005. K 次取反后最大化的数组和
 *
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *
 *     选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 *
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 */

class LargestSumAfterKNegations{
    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            //从前向后遍历，遇到负数将其变为正数，同时K--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        if (k % 2 == 1) nums[len - 1] = -nums[len - 1];
        return Arrays.stream(nums).sum();

    }
}

/**
 * 134. 加油站
 *
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 */
class CanCompleteCircuit{
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int talSum = 0;
        int res = 0;//从哪里开始加油
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            talSum += gas[i] - cost[i];
            //油量不够
            if (curSum < 0){
                res = i+1;
                curSum = 0;
            }
        }
        if (talSum < 0)return -1;
        return res;





    }

}

/**
 * 135. 分发糖果
 *
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 *     每个孩子至少分配到 1 个糖果。
 *     相邻两个孩子评分更高的孩子会获得更多的糖果。
 *
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *  提示：使用两边贪心算法
 */


class Candy{
    public int candy(int[] ratings) {
        int[] candys = new int[ratings.length];
        candys[0] = 1;
        //首先从前往后分析
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1])candys[i]+=candys[i-1]+1;
            else candys[i] = 1;
        }
        //在从后向前分析
        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i+1])candys[i]=Math.max(candys[i],candys[i+1]+1);
        }
        int res = 0;
        for (int a:candys)res+=a;
        return res;
    }
}

/**
 * 860. 柠檬水找零
 *
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 *
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 *
 * 注意，一开始你手头没有任何零钱。
 *
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 */
class LemonadeChange{
    public boolean lemonadeChange(int[] bills) {
        int five = 0; //5美元
        int ten = 0; //10美元

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5)five+=1;

            if (bills[i] == 10){
                five--;
                ten++;
            }


            if (bills[i] == 20){
                if (ten > 0){
                    ten--;
                    five--;
                }else {
                    five-=3;
                }
            }
            if (ten < 0||five < 0)return false;
        }

        return true;
    }
}

/**
 * 406. 根据身高重建队列
 *
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 */

class ReconstructQueue{


    public int[][] reconstructQueue(int[][] people) {
        //先排好序
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        LinkedList<int[]> res = new LinkedList<>();

        for (int[] person : people) {
            res.add(person[1],person);
        }


        return res.toArray(new int[people.length][]);
    }



}


public class greedy_part {
    public static void main(String[] args) {
        int[] sum = new int[]{1,2,3,4,5};
        System.out.println(new MaxProfit().maxProfit(sum));
    }
}
