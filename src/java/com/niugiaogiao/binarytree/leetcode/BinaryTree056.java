package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和
 * https://leetcode.cn/problems/opLdQZ/description/
 * <p>
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-06 17:12
 */
public class BinaryTree056 {

    Set<Integer> dict = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        return run(root, k);
    }

    public boolean run(TreeNode node, int k) {
        if (node == null) {
            return false;
        }
        if (dict.contains(k - node.val)) {
            return true;
        }

        dict.add(node.val);
        return run(node.left, k) || run(node.right, k);
    }

    Set<Integer> dict1 = new HashSet<>();
    public boolean findTargetBFS(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode item = queue.poll();
            if (dict.contains(k - item.val)) {
                return true;
            }
            dict.add(item.val);
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }

        return false;
    }
}
