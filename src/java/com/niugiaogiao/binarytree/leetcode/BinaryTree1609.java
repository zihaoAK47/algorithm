package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 奇偶树
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 * <p>
 * 链接：https://leetcode.cn/problems/even-odd-tree
 *
 * @author zihao
 */
public class BinaryTree1609 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode item = queue.poll();
                temp.add(item.val);
                if ((item.val % 2 == 0 && flag) || (item.val % 2 != 0 && !flag)) {
                    if (item.left != null) {
                        queue.add(item.left);
                    }
                    if (item.right != null) {
                        queue.add(item.right);
                    }
                } else {
                    return false;
                }
            }

            if (!checkNum(temp, flag)) return false;

            flag = !flag;
        }

        return true;
    }

    public boolean checkNum(List<Integer> nums, boolean flag) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        for (int i = 0; i < nums.size(); ++i) {
            while (!s1.isEmpty() && nums.get(i) <= nums.get(s1.peek())) {
                s1.pop();
            }
            while (!s2.isEmpty() && nums.get(i) >= nums.get(s2.peek())) {
                s2.pop();
            }
            s1.push(i);
            s2.push(i);
        }
        if (flag && s2.size() == nums.size()) {
            return true;
        } else if (!flag && s1.size() == nums.size()) {
            return true;
        }

        return false;
    }
}
