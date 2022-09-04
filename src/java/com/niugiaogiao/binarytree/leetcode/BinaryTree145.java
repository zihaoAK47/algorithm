package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-04 22:50
 */
public class BinaryTree145 {

      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

    List<Integer> res = new LinkedList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return res;
        run(root);
        return res;
    }

    public void run(TreeNode node) {
        if (node == null) return;
        run(node.left);
        run(node.right);
        res.add(node.val);
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        if (root == null) return res;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode item = s1.pop();
            s2.push(item);
            if (item.left != null) {
                s1.push(item.left);
            }
            if (item.right != null) {
                s1.push(item.right);
            }
        }

        while (!s2.isEmpty())
            res.add(s2.pop().val);

        return res;
    }

    public List<Integer> postorderTraversal3(TreeNode root) {
        if (root == null) return res;
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(root);
        TreeNode c = null;
        while (!s1.isEmpty()) {
            c = s1.peek();
            if (c.left != null && c.left != root && c.right != root) {
                s1.push(c.left);
            } else if (c.right != null && c.right != root) {
                s1.push(c.right);
            } else {
                res.add(s1.pop().val);
                root = c;
            }
        }

        return res;
    }
}
