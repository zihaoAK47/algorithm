package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 050. 向下的路径节点之和
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）
 * <p>
 * 链接：https://leetcode.cn/problems/6eUYwP
 *
 * @author zihao
 */

public class BinaryTree050 {

    public int pathSumDFS(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = run(root, targetSum);
        count += pathSumDFS(root.left, targetSum);
        count += pathSumDFS(root.right, targetSum);
        return count;
    }

    public int run(TreeNode node, long targetSum) {
        int res = 0;
        if (node == null) {
            return res;
        }
        if (node.val == targetSum) {
            res++;
        }
        res += run(node.left, targetSum - node.val);
        res += run(node.right, targetSum - node.val);
        return res;
    }

    Map<Long, Integer> dict = new HashMap<>();

    /**
     * 另外一种实现方式
     */
    public int pathSumDFS1(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        dict.put(0L, 1);
        return run1(root, targetSum, 0);
    }

    public int run1(TreeNode node, int targetSum, long addSum) {
        if (node == null) return 0;
        long sum = node.val + addSum;
        int count = dict.getOrDefault(sum - targetSum, 0);
        dict.put(sum, dict.getOrDefault(sum, 0) + 1);
        count += run1(node.left, targetSum, sum);
        count += run1(node.right, targetSum, sum);
        dict.put(sum,dict.getOrDefault(sum,0) - 1);
        return count;
    }
}
