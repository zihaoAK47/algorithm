package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 *
 * @author zihao
 */
public class BinaryTree55 {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int deep = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            deep++;
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                if (item.left != null) queue.add(item.left);
                if (item.right != null) queue.add(item.right);
            }
        }
        return deep;
    }

    List<Integer> list = new ArrayList<>();
    public int maxDepthDFS(TreeNode root) {
        run(root,1);
        return list.size();
    }

    public void run(TreeNode node, int deep) {
        if (node == null) return;
        if (list.size() < deep) {
            list.set(deep - 1,node.val);
        }
        run(node.left,deep + 1);
        run(node.right,deep + 1);
    }
}
