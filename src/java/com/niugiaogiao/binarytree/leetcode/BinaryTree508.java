package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BinaryTree508 {

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

    List<Integer> res = new LinkedList<>();
    Map<Integer, Integer> dict = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {

        return null;
    }

    public void run(TreeNode node) {
        if (node == null || node.left == null && node.right == null) return;

        run(node.left);
        run(node.right);



    }
}
