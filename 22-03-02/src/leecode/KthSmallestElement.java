package leecode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class Solution378 {
    public int kthSmallest(int[][] matrix, int k) {
        int [] ans = new int[matrix.length*matrix[0].length];
        int flag = 0;
        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[0].length;j++){
                ans[flag] = matrix[i][j];
                flag++;
            }
        }
        Arrays.sort(ans);
        return ans[k-1];
    }
}



public class KthSmallestElement {
    public static void main(String[] args) {
        int[][] ints ={{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(new Solution378().kthSmallest(ints,8));
     }
}
