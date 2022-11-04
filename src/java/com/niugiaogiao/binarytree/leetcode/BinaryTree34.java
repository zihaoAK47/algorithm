package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点
 * <p>
 * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/description/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-04 22:36
 */
public class BinaryTree34 {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        run(root, target, 0);
        return res;
    }

    public void run(TreeNode node, int target, int sum) {
        if (node == null) {
            return;
        }
        temp.add(node.val);
        if (node.left == null && node.right == null && node.val + sum == target) {
            List<Integer> tRes = new ArrayList<>(temp);
            res.add(tRes);
        }
        run(node.left, target, node.val + sum);
        run(node.right, target, node.val + sum);
        temp.remove(temp.size() - 1);
    }
}
