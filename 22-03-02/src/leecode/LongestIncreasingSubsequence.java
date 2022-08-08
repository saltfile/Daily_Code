package leecode;

import java.util.ArrayList;
import java.util.List;

class Solution300 {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> lis = new ArrayList<>();

        for(int num:nums){

            int size = lis.size();

            if(size==0 ||size>0 && num>lis.get(size-1)){
                lis.add(num);
            }else{
                int insertIndex = MaxMubuer(lis,num);
                lis.set(insertIndex,num);
            }
        }

        return lis.size();
    }
    int MaxMubuer(List<Integer> list, int target){
        int start = 0;
        int end = list.size()-1;

        while(start<end){
            int mid = (start+end)/2;
            if(list.get(mid)<target){
                start=mid+1;
            }else{
                end=mid;
            }
        }
        return start;
    }
}
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(new Solution300().lengthOfLIS(arr));
    }
}
