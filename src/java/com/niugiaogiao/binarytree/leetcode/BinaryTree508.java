package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 出现次数最多的子树元素和
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * <p>
 * 一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）
 * <p>
 * 链接：https://leetcode.cn/problems/most-frequent-subtree-sum
 *
 * @author zihao
 */
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

    static Map<Integer, Integer> dict = new HashMap<>();

    public static int[] findFrequentTreeSum(TreeNode root) {
        run(root);
        int maxCount = 0;
        List<Integer> res = new LinkedList<>();
        for (Map.Entry<Integer, Integer> item : dict.entrySet()) {
            if (maxCount < item.getValue()) {
                res.clear();
                maxCount = item.getValue();
            }
            res.add(item.getKey());
        }

        int[] returnRes = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            returnRes[i] = res.get(i);
        }

        return returnRes;
    }

    public static int run(TreeNode node) {
        if (node == null) return 0;
        int res = node.val + run(node.left) + run(node.right);
        dict.put(res, dict.getOrDefault(res, 0) + 1);
        return res;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(-5);
        TreeNode t4 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        findFrequentTreeSum(t1);
    }
}
