package aohiothor;

import javax.swing.plaf.IconUIResource;

/**
 * 509. 斐波那契数
 *
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 */
class Fib{
    public int fib(int n) {
        if (n <=1)return n;

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }
        return dp[1];
    }
    public int recursion(int n){
        if (n < 2)return n;
        return recursion(n-1)+recursion(n-2);
    }


}


/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 斐波那契从第二个开始往后 1 1 2
 */



class CimbStairs{
    public int climbStairs(int n) {
        if (n <= 1)return n;
        int[] dp = new int[3];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            int sum = dp[1]+dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];

    }
}

/**
 * 746. 使用最小花费爬楼梯
 *
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 */


class MinCostClimbingStairs{
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+ cost[i-2]);
        }
        return dp[cost.length];

    }
}
















public class dynamic_programming {
}
