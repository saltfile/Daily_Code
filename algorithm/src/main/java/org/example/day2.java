package org.example;

/**
 * DP 数组的简单推到应用
 *
 *
 * 例：
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i]
 *
 * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
 *
 * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
 *
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stone-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 其实主要的思路就是通过用动态规划的方式去推测双方将石子都选最大的状态表，相当于是两个智商在线的人都要最好的结果
 *
 */

class DP_node{
    public int dx = 0;
    public int dy = 0;
    public DP_node(int dx,int dy){
        this.dx = dx;
        this.dy = dy;
    }
}





class Stone_Game{
    /**
     *
     * @param piles 石子数组
     * @return 先手和后手的值
     */
    public int Soulytau(int[] piles){
        //构建DP数组
        DP_node[][] dp = new DP_node[piles.length][piles.length];

        //初始化DP数组
        for (int i = 0; i < piles.length; i++)
            for (int j = 0; j < piles.length; j++)
                dp[i][j] = new DP_node(0,0);
        for (int i = 0; i < piles.length; i++)
            dp[i][i].dx = piles[i];


        for (int i = piles.length-2;i >= 0;i--){
            for (int j = i+1; j < piles.length; j++) {
                int left = piles[i] + dp[i+1][j].dy;
                int right = piles[j] + dp[i][j-1].dy;


                if (left>right){
                    dp[i][j].dx = left;
                    dp[i][j].dy = dp[i+1][j].dx;
                }else {
                    dp[i][j].dx = right;
                    dp[i][j].dy = dp[i][j-1].dx;
                }
            }
        }

        return dp[0][piles.length-1].dx-dp[0][piles.length-1].dy;
    }
}






















public class day2 {
    public static void main(String[] args) {
        int[] pils = {2,8,3,5};
        System.out.println(new Stone_Game().Soulytau(pils));
    }
}
