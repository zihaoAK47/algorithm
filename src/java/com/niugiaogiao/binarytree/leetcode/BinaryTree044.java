package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值
 *
 * https://leetcode.cn/problems/hPov7L/submissions/
 *
 * @author zihao
 */
public class BinaryTree044 {

    public List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                if (item.val > max)
                    max = item.val;
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }

            res.add(max);
        }

        return res;
    }

    List<Integer> res = new ArrayList<>();
    public List<Integer> largestValuesDFS(TreeNode root) {
        if (root == null)
            return res;

        run(root, 1);
        return res;
    }

    public void run(TreeNode node, int deep) {
        if (node == null)
            return;

        if (res.size() < deep) {
            res.add(deep - 1, node.val);
        } else {
            res.set(deep - 1, Math.max(node.val, res.get(deep - 1)));
        }

        run(node.left, deep + 1);
        run(node.right, deep + 1);
    }
}
