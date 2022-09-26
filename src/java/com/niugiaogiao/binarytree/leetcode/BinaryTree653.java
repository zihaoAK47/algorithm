package com.niugiaogiao.binarytree.leetcode;

import java.util.*;

/**
 * 两数之和五 - 输入二叉搜索树
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true
 * <p>
 * https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/
 *
 * @author zihao
 */

public class BinaryTree653 {

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

    /* 实现1 */
    Map<Integer, TreeNode> dict = new HashMap<>();

    public boolean findTarget1(TreeNode root, int k) {
        saveNode1(root);
        for (Map.Entry<Integer, TreeNode> item : dict.entrySet()) {
            if (dict.containsKey(k - item.getKey()) && dict.get(k - item.getKey()) != dict.get(item.getKey())) {
                return true;
            }
        }

        return false;
    }

    public void saveNode1(TreeNode node) {
        if (node == null) return;
        saveNode1(node.left);
        saveNode1(node.right);
        dict.put(node.val, node);
    }

    /* 实现2 */
    Set<Integer> setDict = new HashSet<>();

    public boolean findTarget2(TreeNode root, int k) {
        return isExists(root, k);
    }

    public boolean isExists(TreeNode node, int k) {
        if (node == null) return false;
        if (setDict.contains(k - node.val)) return true;
        setDict.add(k);
        return isExists(node.left, k) || isExists(node.right, k);
    }

    /* 实现3 */
    public boolean findTarget3(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Set<Integer> dict = new HashSet<>();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (dict.contains(k - poll.val)) {
                return true;
            }
            dict.add(poll.val);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }

        return false;
    }

    /* 实现4 */
    public boolean findTarget4(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        saveToArray(root, res);
        int i = 0;
        int j = res.size() - 1;
        while (i < j) {
            int t = res.get(i) + res.get(j);
            if (t == k) {
                return true;
            } else if (t < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public void saveToArray(TreeNode node, List<Integer> res) {
        if (node == null) return;
        saveToArray(node.left, res);
        res.add(node.val);
        saveToArray(node.right, res);
    }

}
