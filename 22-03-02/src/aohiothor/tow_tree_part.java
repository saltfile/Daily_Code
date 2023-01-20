package aohiothor;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


class Ergodic{
    //前序遍历 中  左  右

    public void Preorder(TreeNode treeNode){
        if (treeNode==null)return;
        System.out.println(treeNode.val);
        Preorder(treeNode.left);
        Preorder(treeNode.right);

    }
    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    //中序遍历   左 中 右
    public void Middle(TreeNode root){
        if (root == null)return;
        Middle(root.left);
        System.out.println(root.val);
        Middle(root.right);
    }

    public void Middle(TreeNode root,List<Integer> result){
        if (root == null)return;
        Middle(root.left,result);
        result.add(root.val);
        Middle(root.right,result);
    }

    //后序遍历    左 右 中
    public void Postorder(TreeNode root){
        if (root == null)return;
        Postorder(root.left);
        Postorder(root.right);
        System.out.println(root.val);
    }

    public void Postorder(TreeNode root,List<Integer> result){
        if (root == null)return;
        Postorder(root.left,result);
        Postorder(root.right,result);
        result.add(root.val);
    }





}















public class tow_tree_part {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(8);
        root.left = n1;
        root.right = n2;

        n1.left = n3;
        n1.right = n4;

        n2.left = n5;
        n2.right = n6;

        //前序遍历
//        new Ergodic().Preorder(root);
//        List<Integer> arr =new ArrayList<>();
//        new Ergodic().preorder(root,arr);
//        System.out.println(arr);


        //中序遍历
        new Ergodic().Middle(root);
        List<Integer> arr =new ArrayList<>();
        new Ergodic().Middle(root,arr);
        System.out.println(arr);

        //后序遍历
//        new Ergodic().Postorder(root);
//        List<Integer> arr =new ArrayList<>();
//        new Ergodic().Postorder(root,arr);
//        System.out.println(arr);
    }
}
