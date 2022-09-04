package leecode;

import java.util.ArrayList;
import java.util.List;

class Solution967{
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1;i < 10;i++){
            FlushBacking(i,i,n-1,k,numbers);
        }
        int[] res = new int[numbers.size()];
        for (int i = 0;i < res.length;i++){
            res[i] = numbers.get(i);
        }
        return res;
    }
    public void FlushBacking(int num,int disit,int n,int k,List<Integer> nums){
        if (n == 0){
            nums.add(num);
            return;
        }
        if (disit+k <= 9){
            int any = disit+k;
            FlushBacking(num*10+any,any,n-1,k,nums);
        }
        if (k!=0&&(disit-k)>=0){
            int any = disit-k;
            FlushBacking(num*10+any,any,n-1,k,nums);
        }
    }
}
public class Numbers_With_Same_Consecutive_Differences {
    public static void main(String[] args) {
        int[] res = new Solution967().numsSameConsecDiff(2,8);
        for (int re : res) {
            System.out.println(re);
        }
    }
}
