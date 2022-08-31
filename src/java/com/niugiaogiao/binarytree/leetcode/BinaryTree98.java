package com.niugiaogiao.binarytree.leetcode;

import java.util.Stack;


/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTree98 {

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
        public Integer leftMax;
        public Integer rightMin;
        public boolean isBST;

        public Info(Integer leftMax, Integer rightMin, boolean isBST) {
            this.leftMax = leftMax;
            this.rightMin = rightMin;
            this.isBST = isBST;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return run(root).isBST;
    }

    public static Info run(TreeNode root) {
        if (root == null) {
            return new Info(null, null, true);
        }
        Info result1 = run(root.left);
        Info result2 = run(root.right);
        boolean isBST = false;
        int leftMax = root.val;
        int rightMin = root.val;
        leftMax = result1.leftMax != null ? Math.max(leftMax, result1.leftMax) : leftMax;
        leftMax = result2.leftMax != null ? Math.max(leftMax, result2.leftMax) : leftMax;
        rightMin = result1.rightMin != null ? Math.min(rightMin, result1.rightMin) : rightMin;
        rightMin = result2.rightMin != null ? Math.min(rightMin, result2.rightMin) : rightMin;

        if ((result1.isBST && result2.isBST) && (result1.leftMax == null || result1.leftMax < root.val) && (result2.rightMin == null || result2.rightMin > root.val)) {
            isBST = true;
        }
        return new Info(leftMax, rightMin, isBST);
    }

    /**
     * 实现方式 2
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 实现方式 2
     *
     * @param node
     * @param lower
     * @param upper
     * @return
     */
    public boolean isValidBST2(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST2(node.left, lower, node.val) && isValidBST2(node.right, node.val, upper);
    }

    /**
     * 实现方式3 采用中序的方式
     */
    public static boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        double inorder = -Double.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode nodePool = stack.pop();
                if (nodePool.val <= inorder) {
                    return false;
                }
                inorder = nodePool.val;
                root = nodePool.right;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(5);
        TreeNode r2 = new TreeNode(4);
        TreeNode r3 = new TreeNode(6);
        TreeNode r4 = new TreeNode(3);
        TreeNode r5 = new TreeNode(7);
        r1.left = r2;
        r1.right = r3;
        r3.left = r4;
        r3.right = r5;

        System.err.println(isValidBST3(r1));
    }

}
