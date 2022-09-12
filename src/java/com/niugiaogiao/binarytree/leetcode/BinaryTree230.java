package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * title:   二叉搜索树中第K小的元素
 * body:    给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * @author zihao
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 */
public class BinaryTree230 {

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

    public int kthSmallestBFS(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode itemNode = stack.pop();
                if (--k == 0) {
                    return itemNode.val;
                }
                root = itemNode.right;
            }
        }
        return root.val;
    }

    int res = 0;
    int globalK = 0;
    public int kthSmallestDFS(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        globalK = k;
        run(root);
        return res;
    }

    public void run(TreeNode node) {
        if (node == null) {
            return;
        }
        run(node.left);
        if (--globalK == 0) {
            res = node.val;
            return;
        }
        run(node.right);
    }

    static Map<TreeNode, Integer> nodeDict = new HashMap<>();
    public static int kthSmallestTest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        saveDict(root);
        TreeNode cur = root;
        while (cur != null) {
            int nodeCount = nodeDict.getOrDefault(cur.left, 0);
            if (nodeCount == k - 1) {
                break;
            } else if (nodeCount > k - 1) {
                cur = cur.left;
            } else {
                cur = cur.right;
                k -= nodeCount + 1;
            }
        }

        return cur.val;
    }

    public static int saveDict(TreeNode node) {
        if (node == null) {
            return 0;
        }
        nodeDict.put(node, 1 + saveDict(node.left) + saveDict(node.right));
        return nodeDict.get(node);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t6;

        kthSmallestTest(t1, 6);
        System.err.println("");
    }
}
