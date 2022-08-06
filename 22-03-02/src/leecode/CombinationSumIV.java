package leecode;
class Solution377 {
    public int combinationSum4(int[] nums, int target) {
        Integer[] memo = new Integer[target + 1];
        return BackTracking(nums,target,memo);
    }
    public int BackTracking(int[] nums, int target, Integer[] memo){

        if(target < 0) return 0;
        if(memo[target] != null) return memo[target];
        if(target == 0) return 1;

        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            ans += BackTracking(nums, target - nums[i], memo);
        }

        memo[target] = ans;
        return memo[target];
    }
}
public class CombinationSumIV {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Solution377().combinationSum4(nums,4));
     }
}
