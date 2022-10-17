package com.niugiaogiao.binarytree.leetcode;

import java.util.*;

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

    // 实现方式2 - 更改递增 递减判断算法
    public static boolean isEvenOddTree1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 默认奇数
        boolean flag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                if ((item.val % 2 == 0 && flag && pre > item.val) || (item.val % 2 != 0 && !flag && pre < item.val)) {
                    if (item.left != null) queue.add(item.left);
                    if (item.right != null) queue.add(item.right);
                    pre = item.val;
                } else {
                    return false;
                }
            }
            flag = !flag;
        }

        return true;
    }

    // 实现方式3 - DFS
    public static boolean isEvenOddTreeDFS(TreeNode root) {
        return dfs(root, false, 1);
    }

    static List<Integer> temp = new LinkedList<>();
    public static boolean dfs(TreeNode node, boolean flag, int level) {
        if (node == null) return true;
        // 奇偶性质检测
        if (!((node.val % 2 == 0 && flag) || (node.val % 2 != 0 && !flag))) {
            return false;
        }
        if (temp.size() < level) {
            temp.add(level - 1, node.val);
        } else {
            // 递增递减检测
            Integer pre = temp.get(level - 1);
            if (!((flag && pre > node.val) || (!flag && pre < node.val))) {
                return false;
            }
            temp.set(level - 1, node.val);
        }
        return dfs(node.left, !flag, level + 1) && dfs(node.right, !flag, level + 1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(10);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(7);
        TreeNode t6 = new TreeNode(9);
        TreeNode t7 = new TreeNode(12);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(6);
        TreeNode t10 = new TreeNode(2);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t4.left = t7;
        t4.right = t8;
        t3.left = t5;
        t3.right = t6;
        t5.left = t9;
        t6.right = t10;

//        TreeNode t1 = new TreeNode(1);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t5 = new TreeNode(5);
//        t1.left = t2;
//        t1.right = t3;
//        t2.left = t4;
//        t3.right = t5;
        System.err.println(isEvenOddTreeDFS(t1));
    }
}