package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * LCP 44. 开幕式焰火
 * 「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。
 * <p>
 * https://leetcode.cn/problems/KnLfVT/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-10 23:22
 */
public class BinaryTree44 {

    Set<Integer> dict = new HashSet<>();

    public int numColor(TreeNode root) {
        if (root == null) {
            return 0;
        }
        run(root);
        return dict.size();
    }

    public void run(TreeNode node) {
        if (node == null) return;
        if (!dict.contains(node.val)) {
            dict.add(node.val);
        }
        run(node.left);
        run(node.right);
    }


    public int numColor2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode itemNode = null;

        Map<Integer, Integer> dict = new HashMap<>();
        while (!stack.isEmpty()) {
            itemNode = stack.pop();
            dict.put(itemNode.val, 1);
            if (itemNode.right != null) {
                stack.push(itemNode.right);
            }
            if (itemNode.left != null) {
                stack.push(itemNode.left);
            }
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> item : dict.entrySet()) {
            count += item.getValue();
        }

        return count;
    }
}
