package leecode;

/**
 * 给定root二叉树的 ，将树展平为“链表”：
 *
 * “链表”应该使用相同的TreeNode类，其中right子指针指向列表中的下一个节点，并且left子指针始终为null.
 * “链表”的顺序应该与二叉树的前序遍历相同。
 */

class Solution114 {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode prev = root.left;

                while (prev.right != null)
                    prev = prev.right;

                prev.right = root.right;
                root.right = root.left;
                root.left = null;
            }

            root = root.right;
        }
    }
}





public class FlattenBinaryTree {
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

    }
}
