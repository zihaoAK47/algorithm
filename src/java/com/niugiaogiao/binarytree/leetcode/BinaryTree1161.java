package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最大层内元素和
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 * <p>
 * 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
 * <p>
 * 链接：https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree
 *
 * @author zihao
 */
public class BinaryTree1161 {

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = Integer.MAX_VALUE;
        int maxDeep = 0;
        int deep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int tempMax = 0;
            deep++;
            for (int i = 0; i < size; ++i) {
                TreeNode itemPoll = queue.poll();
                tempMax += itemPoll.val;
                if (itemPoll.left != null) queue.add(itemPoll.left);
                if (itemPoll.right != null) queue.add(itemPoll.right);
            }
            if (tempMax > max) {
                max = tempMax;
                maxDeep = deep;
            }
        }

        return maxDeep;
    }
}
