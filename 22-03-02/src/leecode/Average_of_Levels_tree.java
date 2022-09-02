package leecode;

import java.util.ArrayList;
import java.util.List;

class Solution637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List <Integer> count = new ArrayList< >();
        List <Double> sum = new ArrayList < > ();
        FlashBack(root, 0, sum, count);
        List<Double> res = new ArrayList<>();
        for (int i = 0;i < sum.size();i++){
            res.add(sum.get(i)/count.get(i));
        }
        return res;

    }
    public void FlashBack(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)return;

        if (i < sum.size()){
            //已有的层
            sum.set(i,sum.get(i)+t.val);
            count.set(i,count.get(i)+1);
        }else {
            //新的一层
            sum.add((double) t.val);
            count.add(1);
        }
        FlashBack(t.left,++i,sum,count);
        FlashBack(t.right,++i,sum,count);
    }
}
public class Average_of_Levels_tree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        System.out.println(new Solution637().averageOfLevels(node1));


    }
}
