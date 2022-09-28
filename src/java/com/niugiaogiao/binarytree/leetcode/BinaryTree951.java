package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 951. 翻转等价二叉树
 * <p>
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * <p>
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转 等价于二叉树 Y。
 * <p>
 * 这些树由根节点root1 和 root2给出。如果两个二叉树是否是翻转 等价的函数，则返回 true ，否则返回 false 。
 * <p>
 * https://leetcode.cn/problems/flip-equivalent-binary-trees/
 *
 * @author zihao
 */
public class BinaryTree951 {

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

    public boolean flipEquiv1(TreeNode root1, TreeNode root2) {
        if (root1 == root2) return true;
        if (root1 == null || root2 == null || root1.val != root2.val) return false;
        return flipEquiv1(root1.left, root2.left) && flipEquiv1(root1.right, root2.right)
                || flipEquiv1(root1.left, root2.right) && flipEquiv1(root1.right, root2.left);
    }

    public static boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        dfs2(root1, r1);
        dfs2(root2, r2);
        return r1.equals(r2);
    }

    public static void dfs2(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        int L = node.left != null ? node.left.val : -1;
        int R = node.right != null ? node.right.val : -1;
        if (L < R) {
            dfs2(node.left, list);
            dfs2(node.right, list);
        } else {
            dfs2(node.right, list);
            dfs2(node.left, list);
        }
        list.add(null);
    }

    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(1);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t5 = new TreeNode(5);
//        TreeNode t6 = new TreeNode(6);
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t8 = new TreeNode(8);
//        t1.left = t2;
//        t1.right = t3;
//        t2.left = t4;
//        t2.right = t5;
//        t5.left = t7;
//        t5.right = t8;
//        t3.left = t6;
//
//        TreeNode r1 = new TreeNode(1);
//        TreeNode r2 = new TreeNode(3);
//        TreeNode r3 = new TreeNode(2);
//        TreeNode r4 = new TreeNode(6);
//        TreeNode r5 = new TreeNode(4);
//        TreeNode r6 = new TreeNode(5);
//        TreeNode r7 = new TreeNode(8);
//        TreeNode r8 = new TreeNode(7);
//        r1.left = r2;
//        r1.right = r3;
//        r2.right = r4;
//        r3.left = r5;
//        r3.right = r6;
//        r6.left = r7;
//        r6.right = r8;

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        r1.left = r2;
        r2.left = r3;
        System.err.println(flipEquiv2(t1, r1));
    }

}
