package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

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

    public static boolean hasPathSumDFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null || root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSumDFS(root.left, targetSum - root.val) || hasPathSumDFS(root.right, targetSum - root.val);
    }

    public static boolean hasPathSumBFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();
        queue.add(root);
        queueVal.add(root.val);
        while (!queue.isEmpty()) {
            TreeNode itemNode = queue.poll();
            Integer itemVal = queueVal.poll();
            if (itemNode.left == null && itemNode.right == null) {
                if (itemNode.val == targetSum) {
                    return true;
                }
            }
            if (itemNode.left != null) {
                queue.add(itemNode.left);
                queueVal.add(targetSum + itemNode.left.val);
            }
            if (itemNode.right != null) {
                queue.add(itemNode.right);
                queueVal.add(targetSum + itemNode.right.val);
            }
        }

        return false;
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
    }
}
