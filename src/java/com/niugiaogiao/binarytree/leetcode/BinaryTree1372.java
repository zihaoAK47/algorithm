package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-11-18 22:39
 */
public class BinaryTree1372 {

    static int max = 0;

    public static int longestZigZag1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        run(root, 0, 1);
        run(root, 0, 0);
        longestZigZag1(root.left);
        longestZigZag1(root.right);
        return max;
    }

    public static void run(TreeNode node, int t, int up) {
        if (node == null) {
            return;
        }
        // left
        if (node.left != null && up == 0) {
            run(node.left, t + 1, 1);
        }

        if (node.right != null && up == 1) {
            run(node.right, t + 1, 0);
        }
        max = Math.max(max, t);
    }

    int maxAns;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxAns = 0;
        dfs(root, false, 0);
        dfs(root, true, 0);
        return maxAns;
    }

    public void dfs(TreeNode o, boolean dir, int len) {
        maxAns = Math.max(maxAns, len);
        if (!dir) {
            if (o.left != null) {
                dfs(o.left, true, len + 1);
            }
            if (o.right != null) {
                dfs(o.right, false, 1);
            }
        } else {
            if (o.right != null) {
                dfs(o.right, false, len + 1);
            }
            if (o.left != null) {
                dfs(o.left, true, 1);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(3);
        t1.left = t2;
        t2.right = t3;
        t3.left = t4;
        t4.right = t5;
        System.err.println(max);
    }
}
