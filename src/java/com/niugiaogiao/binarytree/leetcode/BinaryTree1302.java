package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1302. 层数最深叶子节点的和
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和
 * https://leetcode.cn/problems/deepest-leaves-sum/
 *
 * @author zihao
 */
public class BinaryTree1302 {

    public int deepestLeavesSumBFS(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                sum += item.val;
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
        }
        return sum;
    }

    int sum = 0;
    int maxDeep = 0;
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        run(root, 1);
        return sum;
    }

    public void run(TreeNode node, int deep) {
        if (node == null) {
            return;
        }

        run(node.left, deep + 1);
        run(node.right, deep + 1);
        if (maxDeep < deep) {
            maxDeep = deep;
            sum = 0;
        }
        if (deep == maxDeep) {
            sum += node.val;
        }
    }
}
