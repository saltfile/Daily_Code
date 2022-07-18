package leecode;
class Solution629{
    private final static int scd = 1000000007;
    public int kInversePairs(int n, int k) {

        int[][] dp=new int[n+1][k+1];

        for(int i=1;i<=n;i++)
            for(int j=0;j<=k && j<=i*(i-1)/2;j++) {
                if(i==1 && j==0) {dp[i][j]=1;break;}
                else if(j==0) dp[i][j]=1;
                else dp[i][j]=((dp[i][j-1]+dp[i-1][j])%scd+scd-((j-i)>=0?dp[i-1][j-i]:0))%scd;
    }
        return dp[n][k];
}
}
public class KInverseArray {
}
