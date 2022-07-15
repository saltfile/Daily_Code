package leecode;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class Solution199 {
    private List <Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        this.res.clear();
        if (root==null)
            return this.res;

        Right(root,0);
        return this.res;
    }
    public void Right(TreeNode root,int level){
        if (root==null)
            return;

        if(level == this.res.size())
           this.res.add(root.val);

        //遍历层数
        Right(root.right, level + 1);
        Right(root.left, level + 1);

    }

}



public class BinaryTreeRightSide {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node5;


        List<Integer> list = new Solution199().rightSideView(node1);
        for (Integer i : list){
            System.out.println(i);
        }





    }
}
