package aohiothor;

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




















public class dynamic_programming {
}
