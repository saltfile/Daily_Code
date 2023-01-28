package aohiothor;


import java.util.*;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};


//算法二叉树部分
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


/**
 * 102. 二叉树的层序遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 */


class LevelOrder{  public int maxDepth(TreeNode root) {
        return getlevel(root);
    }

    public int getlevel(TreeNode root){
        if (root == null)return 0;

        int num1 = getlevel(root.left);
        int num2 = getlevel(root.right);
        int res = 1+Math.max(num1,num2);
        return res;

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res =  new ArrayList<>();
        int level = 0;
        Order(root,res,level);
        return res;

    }
    public void Order(TreeNode root,List<List<Integer>> res,int level){
        if (root == null)return;
        if (res.size() == level) res.add(new ArrayList<>());

        res.get(level).add(root.val);

        Order(root.left,res,level + 1);
        Order(root.right,res,level + 1);

    }


}


/**
 * 226. 翻转二叉树
 *
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 */

class InvertTree{
    public TreeNode invertTree(TreeNode root) {
        revcse(root);
        return root;
    }

    public void revcse(TreeNode root){
        if (root == null)return;
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        revcse(root.left);
        revcse(root.right);


    }



}

/**
 * 101. 对称二叉树
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 *
 *这题比较迷：
 * 确定终止条件
 * 左节点为空，右节点不为空，不对称，return false；
 * 左不为空，右为空，不对称 return false
 * 左右都为空，对称，返回true
 * 左右都不为空，比较节点数值，不相同就return false
 */


class IsSymmetric{
    public boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }

    public boolean check(TreeNode left,TreeNode right){
        if (left == null&&right != null){
            return false;
        }
        if (left != null && right == null){
            return false;
        }
        if (left == null&&right == null){
            return true;
        }
//        if (left.val == right.val){
//            return true;
//        }
        if (left.val!=right.val){
            return false;
        }

        boolean out = check(left.left,right.right);
        boolean in = check(left.right,right.left);
        return out&&in;

    }


}

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明:叶子节点是指没有子节点的节点。
 *
 *
 *
 *
 * 559. N 叉树的最大深度
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 */








class MaxDepth{
    public int maxDepth(TreeNode root) {
        return getlevel(root);
    }

    public int getlevel(TreeNode root){
        if (root == null)return 0;

        int num1 = getlevel(root.left);
        int num2 = getlevel(root.right);
        int res = 1+Math.max(num1,num2);
        return res;

    }


    public int maxDepth(Node root) {
        return getlevel(root);
    }

    public int getlevel(Node root){
        if (root == null)return 0;
        int res = 0;
        for (int i = 0; i < root.children.size(); i++) {
            res = Math.max(res,getlevel(root.children.get(i)));
        }
        return ++res;
    }

}

/**
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 *
 * 注意！！！！！
 * 左子树为空，右子树不为空，说明最小深度是 1 + 右子树的深度。
 * 右子树为空，左子树不为空，最小深度是 1 + 左子树的深度。
 */

class MinDepth{
    public int minDepth(TreeNode root) {
        return getlevel(root);
    }

    public int getlevel(TreeNode root){
        if (root==null)return 0;
        int num1 = getlevel(root.left);
        int num2 = getlevel(root.right);
        if (root.left !=null&&root.right == null){
            return 1+num1;
        }
        if (root.right !=null && root.left == null){
            return 1+num2;
        }
        int res = 1+Math.min(num1,num2);
        return res;
    }


}

/**
 * 257. 二叉树的所有路径
 *
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 */

class BinaryTreePaths{


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        if (root == null)return res;

        getpath(root,path,res);
        return res;

    }

    public void getpath(TreeNode root,List<Integer>path,List<String> res){
        path.add(root.val);
        //说明后面没路
        if (root.left == null&&root.right == null){
            String res_part = "";
            for (int i = 0; i < path.size()-1; i++) {
                res_part+=path.get(i);
                res_part+= "->";
            }
            res_part+=path.get(path.size()-1);
            res.add(res_part);
        }
        if (root.left!=null){
            getpath(root.left,path,res);
            path.remove(path.size()-1);
        }
        if (root.right!=null){
            getpath(root.right,path,res);
            path.remove(path.size()-1);
        }
    }
}


/**
 * 222. 完全二叉树的节点个数
 *
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
 */
class CountNodes{
    public int countNodes(TreeNode root) {
        return getNodes(root);
    }
    public int getNodes(TreeNode root){
        if (root == null)return 0;
        int les = getNodes(root.left);
        int res = getNodes(root.right);
        int sum = les+res+1;
        return sum;
    }

}


/**
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 参数：当前传入节点。
 * 返回值：以当前传入节点为根节点的树的高度。
 *
 * 那么如何标记左右子树是否差值大于1呢？
 *
 * 如果当前传入节点为根节点的二叉树已经不是二叉平衡树了，还返回高度的话就没有意义了。
 *
 * 所以如果已经不是二叉平衡树了，可以返回-1 来标记已经不符合平衡树的规则了。
 */

class IsBalanced{
    public boolean isBalanced(TreeNode root) {
        return getlevel(root)!=-1;
    }
    public int getlevel(TreeNode root){
        if (root == null)return 0;

        int left = getlevel(root.left);
        if (left == -1)return -1;
        int right = getlevel(root.right);
        if (right == -1) return -1;
        int res = Math.abs(left-right) > 1 ? -1 : 1+Math.max(left,right);
        return res;
    }
}


/**
 * 404. 左叶子之和
 *
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 */
class SumOfLeftLeaves{
    public int sumOfLeftLeaves(TreeNode root) {


        return getLeftSum(root);
    }
    public int getLeftSum(TreeNode root){
        if (root == null)return 0;
        //获取左边叶子的值

        int left = getLeftSum(root.left);
        int right = getLeftSum(root.right);

        int mid = 0;
        //注意这里是叶子节点
        if (root.left != null&&root.left.left == null&&root.left.right == null){
            mid = root.left.val;
        }
        return left+mid+right;
    }
}

/**
 * 513. 找树左下角的值
 *
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 */
class FindBottomLeftValue{
    private int level = -1;
    private int res;
    public int findBottomLeftValue(TreeNode root) {
        //担心就一个root节点
        res = root.val;
        getDeep(root,0);
        return res;
    }

    public void getDeep(TreeNode root,int deep){
        //确定边界
        if (root == null)return;
        //如果是叶子结点如果比当前的深度深那么就替换原来的深度
        if (root.left == null&&root.right == null){
            if (deep > level){
                level = deep;
                res = root.val;
            }
        }
        //先左后右
        getDeep(root.left,deep+1);
        getDeep(root.right,deep+1);
    }



}

/**
 * 112. 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 */

class HasPathSum{

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)return false;
        return getSum(root,targetSum,0+root.val);
    }
    //个人比较喜欢回溯
    public boolean getSum(TreeNode root,int targer,int sum){
        //子节点且找到了
        if (root.left == null&&root.right == null&&sum == targer)return true;
        //子节点但没找到
        if (root.left == null&&root.right == null)return false;
        if (root.left!=null){
            sum += root.left.val;
            //执行成功就返回去
            if (getSum(root.left,targer,sum))return true;
            //妹执行成功就回溯
            sum-=root.left.val;
        }
        if (root.right != null){
            sum += root.right.val;
            if (getSum(root.right, targer, sum))return true;
            sum-=root.right.val;
        }
        return false;

    }

}


/**
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树。
 *
 *
 *
 * 这个看解析，确实不太会
 * 来看一下一共分几步：
 *
 *     第一步：如果数组大小为零的话，说明是空节点了。
 *
 *     第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
 *
 *     第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点
 *
 *     第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）
 *
 *     第五步：切割后序数组，切成后序左数组和后序右数组
 *
 *     第六步：递归处理左区间和右区间
 */

class BuildTree{
    Map<Integer, Integer> map;  // 方便根据数值查找位置
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { // 用map保存中序序列的数值对应位置
            map.put(inorder[i], i);
        }
        return getTree(inorder,0,inorder.length,postorder,0,postorder.length);
    }

    public TreeNode getTree(int[] inorder,int IStart,int IEnd,int[] postorder,int PStart,int PEnd){
        //第一步：如果数组大小为零的话，说明是空节点了。
        if (IStart >= IEnd||PStart>=PEnd)return null;
        //第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
        int rootIndex = map.get(postorder[PEnd - 1]);  // 找到后序遍历的最后一个元素在中序遍历中的位置
        TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造结点
        int lenOfLeft = rootIndex - IStart;  // 保存中序左子树个数，用来确定后序数列的个数
        root.left = getTree(inorder, IStart, rootIndex,
                postorder,PStart, PStart + lenOfLeft);
        root.right = getTree(inorder, rootIndex + 1, IEnd,
                postorder, PStart + lenOfLeft, PEnd - 1);
        return root;
    }
}


/**
 * 654. 最大二叉树
 *
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 *
 *     创建一个根节点，其值为 nums 中的最大值。
 *     递归地在最大值 左边 的 子数组前缀上 构建左子树。
 *     递归地在最大值 右边 的 子数组后缀上 构建右子树。
 *
 * 返回 nums 构建的 最大二叉树 。
 */


class ConstructMaximumBinaryTree{
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums,0,nums.length);
    }
    public TreeNode build(int[] nums,int start,int end){
        if (end - start < 1)return null;
        if (end - start == 1) {// 只有一个元素
            return new TreeNode(nums[start]);
        }
        //找到最大的下标
        int maxidx = start;
        for (int i = start+1; i < end; i++) {
            if (nums[i] > nums[maxidx])maxidx = i;
        }
        //创建新节点
        TreeNode root = new TreeNode(nums[maxidx]);

        root.left = build(nums,start,maxidx);

        root.right = build(nums,maxidx+1,end);

        return root;
    }

}

/**
 * 617. 合并二叉树
 *
 * 给你两棵二叉树： root1 和 root2 。
 *
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 *
 * 返回合并后的二叉树。
 *
 * 注意: 合并过程必须从两个树的根节点开始。
 */

class MergeTrees{
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return merge(root1,root2);
    }

    public TreeNode merge(TreeNode root1,TreeNode root2){
        if (root1 == null)return root2;
        if (root2 == null) return root1;
        root1.val+=root2.val;
        root1.left = merge(root1.left,root2.left);
        root1.right = merge(root1.right,root2.right);
        return root1;
    }
}

/**
 * 700. 二叉搜索树中的搜索
 *
 * 给定二叉搜索树（BST）的根节点 root 和一个整数值val。
 *
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
 */
class SearchBST{
    public TreeNode searchBST(TreeNode root, int val) {
        return select(root,val);
    }

    public TreeNode select(TreeNode root,int val){
        if (root == null)return null;
        TreeNode res = null;
        if (root.val > val){
           res = select(root.left,val);
        }else if (root.val < val){
            res = select(root.right,val);
        }else {
            return root;
        }
        return res;
    }
}

/**
 * 98. 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 *     节点的左子树只包含 小于 当前节点的数。
 *     节点的右子树只包含 大于 当前节点的数。
 *     所有左子树和右子树自身必须也是二叉搜索树。
 */

class IsValidBST{
    List<Integer> check = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        check.clear();

        Middle(root);
        int slow = 0;
        for (int i = 1; i < check.size(); i++) {
            if (check.get(i-1) >= check.get(i))return false;
        }
        return true;
    }
    public void Middle(TreeNode root){
        if (root == null)return;

        Middle(root.left);
        check.add(root.val);
        Middle(root.right);
    }
}

/**
 * 530. 二叉搜索树的最小绝对差
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 差值是一个正数，其数值等于两值之差的绝对值。
 */

class GetMinimumDifference{

    List<Integer> check = new ArrayList<>();


    public int getMinimumDifference(TreeNode root) {
        check.clear();
        mid(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < check.size(); i++) {
            int anum = check.get(i)-check.get(i-1);
            if (anum < min)min = anum;
        }
        return min;

    }

    public void mid(TreeNode root){
        if (root == null)return;
        mid(root.left);
        check.add(root.val);
        mid(root.right);
    }


}


/**
 * 501. 二叉搜索树中的众数
 *
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 *
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 *
 * 假定 BST 满足如下定义：
 *
 *     结点左子树中所含节点的值 小于等于 当前节点的值
 *     结点右子树中所含节点的值 大于等于 当前节点的值
 *     左子树和右子树都是二叉搜索树
 *
 *
 */
class FindMode{
    int count = 0;
    int maxcount = 0;
    TreeNode pre = null;
    List<Integer> result = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        result.clear();
        count = 0;
        maxcount = 0;
        pre = null;
        Mid(root);
        int[] res = new int[result.size()];

        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;

    }


    void Mid(TreeNode root){
        if (root == null)return;
        Mid(root.left);

        if (pre == null){
            count = 1;
        }else if (pre.val == root.val){
            count += 1;
        }else {
            count = 1;
        }
        pre = root;


        if (count == maxcount){
            result.add(root.val);
        }

        if (count > maxcount){
            maxcount = count;
            result.clear();
            result.add(root.val);
        }
        Mid(root.right);
        return;
    }

}

/**
 * 236. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */


class  LowestCommonAncestor{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return getFather(root, p, q);
    }


    public TreeNode getFather(TreeNode root,TreeNode p,TreeNode q){
        if (root == q || root == p || root == null) return root;

        TreeNode left = getFather(root.left,p,q);
        TreeNode right = getFather(root.right,p,q);

        if (left!=null&&right == null)return left;
        else if (left == null&&right !=null)return right;
        else if (left!=null&&right!=null)return root;
        return null;
    }



}

/**
 * 235. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 */

class LowestCommonAncestorBST{

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return getLowest(root, p, q);
    }

    public TreeNode getLowest(TreeNode root,TreeNode p,TreeNode q){
        if (root.val > p.val&&root.val > q.val){
            return getLowest(root.left, p, q);
        } else if (root.val < p.val&&root.val < q.val) {
            return getLowest(root.right, p, q);
        }else {
            return root;
        }
    }

}

/**
 * 701. 二叉搜索树中的插入操作
 *
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 */


class InsertIntoBST{

    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insert(root, val);
    }

    public TreeNode insert(TreeNode root,int val){
        if (root == null){
            root = new TreeNode(val);
            return root;
        }

        if (root.val > val){
            root.left = insertIntoBST(root.left,val);
        }
        if (root.val < val){
            root.right = insertIntoBST(root.right,val);
        }
        return root;
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
//        new Ergodic().Middle(root);
//        List<Integer> arr =new ArrayList<>();
//        new Ergodic().Middle(root,arr);
//        System.out.println(arr);

        //后序遍历
//        new Ergodic().Postorder(root);
//        List<Integer> arr =new ArrayList<>();
//        new Ergodic().Postorder(root,arr);
//        System.out.println(arr);

//        System.out.println(new LevelOrder().levelOrder(root));
//        new InvertTree().invertTree(root);
//        System.out.println(new LevelOrder().levelOrder(root));
//        System.out.println(new SumOfLeftLeaves().sumOfLeftLeaves(root));


        int[] a1 = {9,3,15,20,7};
        int[] a2 = {9,15,7,20,3};
        TreeNode r = new BuildTree().buildTree(a1,a2);

//        System.out.println(new MaxDepth().maxDepth(root));
    }
}
