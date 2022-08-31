package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-07-13 16:23
 */
public class BinaryTree100 {

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

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return false;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(p);
        stack2.push(q);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode pop1 = stack1.pop();
            TreeNode pop2 = stack2.pop();
            if ((pop1 == null && pop2 != null) || (pop1 != null && pop2 == null)) {
                return false;
            } else if (pop1 == null || pop2 == null) {
                continue;
            } else if (pop1.val != pop2.val) {
                return false;
            }

            stack1.push(pop1.right);
            stack1.push(pop1.left);
            stack2.push(pop2.right);
            stack2.push(pop2.left);
        }

        return stack1.isEmpty() && stack2.isEmpty();
    }

    public boolean isSameTree3(TreeNode p, TreeNode q) {
        Queue<TreeNode> tmpQueue = new LinkedList<>();
        tmpQueue.offer(p);
        tmpQueue.offer(q);
        while (!tmpQueue.isEmpty()) {
            p = tmpQueue.poll();
            q = tmpQueue.poll();
            if (p == null && q == null) {
                continue;
            }
            if ((p == null || q == null) || p.val != q.val) {
                return false;
            }
            tmpQueue.offer(p.left);
            tmpQueue.offer(q.left);

            tmpQueue.offer(p.right);
            tmpQueue.offer(q.right);
        }
        return true;
    }

    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode p1 = new TreeNode();
        TreeNode p2 = new TreeNode();
        p1.val = 1;
        p2.val = 2;
        p1.left = p2;

        TreeNode q1 = new TreeNode();
        TreeNode q2 = new TreeNode();
        q1.val = 1;
        q2.val = 2;
        q1.left = q2;

        System.err.println(isSameTree1(p1, q1));
    }
}
