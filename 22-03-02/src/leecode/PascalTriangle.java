package leecode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *给定一个整数，返回帕斯卡三角形numRows的前 numRows 。
 *
 * 在帕斯卡三角形中，每个数字都是其正上方的两个数字之和，如下所示：
 * 就像是杨辉三角
 */


class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> Item = new ArrayList<>();
        Item.add(1);
        res.add(new ArrayList<>(Item));
        for(int i = 2;i <= numRows;i++){
            List<Integer> ItemNew = new ArrayList<>();
            ItemNew.add(1);
            for (int j = 1;j < i-1;j++){
                ItemNew.add(Item.get(j-1)+Item.get(j));
            }
            ItemNew.add(1);
            res.add(new ArrayList<>(ItemNew));
            Item = ItemNew;
        }
        return res;
    }
}
public class PascalTriangle {
    public static void main(String[] args) {
        System.out.println(new Solution118().generate(6));
    }
}
