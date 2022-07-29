package leecode;

import sun.reflect.generics.tree.Tree;

class Solution236 {
    private TreeNode res = new TreeNode();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.backtracking(root,p,q);
        return this.res;
    }
    private boolean backtracking(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        int left = this.backtracking(root.left, p, q) ? 1 : 0;
        int right = this.backtracking(root.right,p,q) ? 1 : 0;
        int mid = (root == p || root == q) ? 1 : 0;
        if (mid + left + right >= 2) {
            this.res = root;
        }
        return (mid + left + right > 0);
    }
}




//3,5,1,6,2,0,8,null,null,7,4
public class LowestBinaryTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9= new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        System.out.println(new Solution236().lowestCommonAncestor(node1,node2,node4).val);


    }
}
