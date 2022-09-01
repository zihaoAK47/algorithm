package com.niugiaogiao.binarytree.leetcode;

/**
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。
 * 判断该树中是否存在 根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和targetSum
 * 如果存在，返回 true ；否则，返回 false
 * <p>
 * 叶子节点 是指没有子节点的节点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTree112 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Info {
        // 左树总值
        int allLeftVal;
        // 右树总值
        int allRightVal;
        // 当前节点是否是根节点
        boolean isRoot;
        // 当前节点是否满足条件
        boolean isEquals;

        Info(int allLeftVal, int allRightVal, boolean isRoot, boolean isEquals) {
            this.allLeftVal = allLeftVal;
            this.allRightVal = allRightVal;
            this.isRoot = isRoot;
            this.isEquals = isEquals;
        }

        Info() {

        }
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        return run(root, targetSum, root).isEquals;
    }

    public static Info run(TreeNode node, int targetSum, TreeNode root) {
        if (node == null) {
            return new Info(0,0,false,false);
        }
        Info run1 = run(node.left, targetSum, root);
        Info run2 = run(node.right, targetSum, root);
        boolean isRoot = node == root;
        boolean isEquals = false;
        if (run1.allLeftVal + node.val == targetSum && isRoot) {
            isEquals = true;
        }
        if (run1.allRightVal + node.val == targetSum && isRoot) {
            isEquals = true;
        }
        if (run2.allLeftVal + node.val == targetSum && isRoot) {
            isEquals = true;
        }
        if (run2.allRightVal + node.val == targetSum && isRoot) {
            isEquals = true;
        }
        int leftVal = run1.allLeftVal == 0 ? run2.allLeftVal + node.val : run1.allLeftVal + node.val;
        int rightVal = run2.allRightVal == 0 ? run1.allRightVal + node.val : run2.allRightVal + node.val;
        return new Info(leftVal, rightVal, isRoot, isEquals);
    }

    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(5);
//        TreeNode t2 = new TreeNode(4);
//        TreeNode t3 = new TreeNode(8);
//        TreeNode t4 = new TreeNode(11);
//        TreeNode t5 = new TreeNode(13);
//        TreeNode t6 = new TreeNode(4);
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t8 = new TreeNode(2);
//        TreeNode t9 = new TreeNode(1);
//        t1.left = t2;
//        t1.right = t3;
//        t2.left = t4;
//        t4.left = t7;
//        t4.right = t8;
//        t3.left = t5;
//        t3.right = t6;
//        t6.right = t9;

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        t1.left = t2;

        System.err.println(hasPathSum(t1, 1));
    }

}
