package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~2h个节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-complete-tree-nodes
 */
public class BinaryTree222 {

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

    public int countNodesBFS(TreeNode root) {
        int nodeCount = 0;
        if (root == null) {
            return nodeCount;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode item = queue.poll();
            nodeCount++;
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }

        return nodeCount;
    }

    public int countNodesDFS(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodesDFS(root.left) + countNodesDFS(root.right);
    }

    public static int countNodesBFS1(TreeNode root) {
        if (root == null) return 0;

        int h = 0;
        TreeNode tempNode = root;
        while (tempNode.left != null) {
            h++;
            tempNode = tempNode.left;
        }
        int start = 1 << h;
        int end = (1 << (h + 1)) - 1;
        while (start < end) {
            int mid = ((end - start + 1) >> 1) + start;
            if (isExists(root, h, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

//        {
//            while (start <= end) {
//                int mid = (start + end) / 2;
//                if (isExists(root, h, mid)) {
//                    start = mid + 1;
//                } else {
//                    end = mid - 1;
//                }
//            }
//
//            return start - 1;
//        }

        return start;
    }

    public static boolean isExists(TreeNode root, int leaver, int k) {
        TreeNode tempRoot = root;
        int bits = 1 << (leaver - 1);
        while (tempRoot != null && bits > 0) {
            if ((bits & k) == 0) {
                tempRoot = tempRoot.left;
            } else {
                tempRoot = tempRoot.right;
            }
            bits >>= 1;
        }
        return tempRoot != null;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        countNodesBFS1(t1);
    }
}
