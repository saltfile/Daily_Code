import leecode.TreeNode;

import java.util.ArrayList;

public class TreeAgain {
    //中左右
    private static void qian(TreeNode node,ArrayList arr){
        if (node == null)return;

        arr.add(node.val);
        qian(node.left,arr);
        qian(node.right,arr);
    }

    private static void  zhong(TreeNode node,ArrayList arr){
        if (node == null)return;;
        zhong(node.left,arr);
        arr.add(node.val);
        zhong(node.right,arr);
    }

    private static void hou(TreeNode node,ArrayList arr){
        if (node ==  null)return;
        hou(node.left,arr);
        hou(node.right,arr);
        arr.add(node.val);
    }




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
        ArrayList arr = new ArrayList();
        hou(node1,arr);
        System.out.println(arr);
        String  s = "ff";
    }
}
