package leecode;

/**
 * 摆动序列是连续数字之间的差异在正负之间严格交替的序列。第一个差异（如果存在）可以是正面的也可以是负面的。具有一个元素的序列和具有两个不相等元素的序列是平凡的摆动序列。
 *
 * 例如，[1, 7, 4, 9, 2, 5]是一个摆动序列，因为差异(6, -3, 5, -7, 3)在正负之间交替。
 * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5]不是摆动序列。第一个不是因为它的前两个差是正的，第二个不是因为它的最后一个差是零。
 * 子序列是通过从原始序列中删除一些元素（可能为零）而获得的，而其余元素则保持其原始顺序。
 *
 * 给定一个整数数组nums，返回的最长摆动子序列的nums长度。
 */


class Solution376 {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length < 2)return nums.length;
        int up = 1,down = 1;
        for(int i = 1;i < nums.length;i++){
            if (nums[i-1] > nums[i])
            {
                down = up+1;
            }else if(nums[i-1] < nums[i])
            {
                up = down+1;
            }
        }
        return Math.max(up,down);
    }
}
public class Wiggle_Subsequence {
    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5,6,7,8,9};
        System.out.println(new Solution376().wiggleMaxLength(ints));
    }
}
