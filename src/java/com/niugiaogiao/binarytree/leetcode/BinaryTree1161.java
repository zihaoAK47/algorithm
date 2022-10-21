package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public int maxLevelSumBFS(TreeNode root) {
        return 0;
    }


    static List<Integer> res = new ArrayList<>();
    static int maxDeep = 0;
    static int maxVal = Integer.MIN_VALUE;
    public static int maxLevelSum1(TreeNode root) {
        run(root, 1);
        for (int i = 0 ; i < res.size() ; ++i) {
            if (res.get(i) > maxVal) {
                maxDeep = i + 1;
                maxVal = res.get(i);
            }
        }

        return maxDeep ;
    }

    public static void run(TreeNode node, int deep) {
        if (node == null) return;
        if (res.size() == deep - 1) {
            res.add(deep - 1, node.val);
        } else {
            res.set(deep - 1,res.get(deep - 1) + node.val);
        }
        run(node.left, deep + 1);
        run(node.right, deep + 1);
    }

    public static void main(String[] args) {
        TreeNode t1 =  new TreeNode(1);
        TreeNode t2 =  new TreeNode(7);
        TreeNode t3 =  new TreeNode(0);
        TreeNode t4 =  new TreeNode(7);
        TreeNode t5 =  new TreeNode(-8);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        System.err.println(maxLevelSum1(t1));
    }
}
