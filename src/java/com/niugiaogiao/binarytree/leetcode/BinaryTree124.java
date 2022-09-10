package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-03 11:16
 */
public class BinaryTree124 {

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

    public static int maxPathSum(TreeNode root) {
        run(root);
        return max;
    }

    static int max = -1024;
    public static int run(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int a = Math.max(run(node.left), 0);
        int b = Math.max(run(node.right), 0);
        max = Math.max(max, node.val + a + b);
        return node.val + Math.max(a,b);
    }

    public static int maxPathSum1(TreeNode root) {
        int max = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode itemNode = queue.poll();
            max = Math.max(itemNode.val, max);
            int t = itemNode.val;
            if (itemNode.left != null) {
                t += itemNode.left.val;
                max = Math.max(itemNode.left.val, max);
                max = Math.max(itemNode.left.val + itemNode.val, max);
                queue.add(itemNode.left);
            }
            if (itemNode.right != null) {
                t += itemNode.right.val;
                max = Math.max(itemNode.right.val, max);
                max = Math.max(itemNode.right.val + itemNode.val, max);
                queue.add(itemNode.right);
            }
            max = Math.max(t, max);
        }

        return max;
    }


    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(-10);
//        TreeNode t2 = new TreeNode(9);
//        TreeNode t3 = new TreeNode(20);
//        TreeNode t4 = new TreeNode(15);
//        TreeNode t5 = new TreeNode(7);
//        t1.left = t2;
//        t1.right = t3;
//        t3.left = t4;
//        t3.right = t5;
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        t4.left = t5;
        System.err.println(maxPathSum1(t1));
    }


}
