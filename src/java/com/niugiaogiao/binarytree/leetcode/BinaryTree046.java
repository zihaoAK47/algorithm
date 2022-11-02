package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
 * <p>
 * https://leetcode.cn/problems/WNC0Lk/description/
 *
 * @author zihao
 */
public class BinaryTree046 {

    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideViewDFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root, 1);
        return res;
    }

    public void dfs(TreeNode node, int deep) {
        if (node == null) {
            return;
        }

        if (res.size() < deep) {
            res.add(deep - 1, node.val);
        } else {
            res.set(deep - 1, node.val);
        }

        dfs(node.left, deep + 1);
        dfs(node.right, deep + 1);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int deep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                if (i + 1 == size) {
                    res.add(item.val);
                }

                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
        }

        return res;
    }
}