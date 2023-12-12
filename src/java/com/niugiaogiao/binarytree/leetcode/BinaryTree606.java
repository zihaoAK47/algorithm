package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 根据二叉树创建字符串
 * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
 * <p>
 * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * <p>
 * 链接：https://leetcode.cn/problems/construct-string-from-binary-tree
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-14 23:09
 */
public class BinaryTree606 {

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

    static StringBuilder stringBuilder = new StringBuilder();

    public static String tree2str(TreeNode root) {
        run(root);
        return stringBuilder.toString();
    }

    public static void run(TreeNode node) {
        stringBuilder.append(node.val);
        if (node.left != null && node.right != null) {
            stringBuilder.append("(");
            run(node.left);
            stringBuilder.append(")");
            stringBuilder.append("(");
            run(node.right);
            stringBuilder.append(")");
            return;
        }

        if (node.left != null) {
            stringBuilder.append("(");
            run(node.left);
            stringBuilder.append(")");
            return;
        }

        if (node.right != null) {
            stringBuilder.append("(");
            stringBuilder.append(")");
            stringBuilder.append("(");
            run(node.right);
            stringBuilder.append(")");
        }
    }

    /**
     * 实现方式二
     */
    StringBuffer sb = new StringBuffer();

    public String tree2strDFS(TreeNode root) {
        dfs(root);
        return sb.substring(1, sb.length() - 1);
    }

    public void dfs(TreeNode node) {
        sb.append("(");
        sb.append(node.val);
        if (node.left != null) {
            dfs(node.left);
        } else if (node.right != null) {
            sb.append("()");
        }
        if (node.right != null) {
            dfs(node.right);
        }
        sb.append(")");
    }

    public static String tree2strBFS(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode t = stack.peek();
            if (set.contains(t)) {
                sb.append(")");
                stack.pop();
            } else {
                sb.append("(");
                sb.append(t.val);
                if (t.right != null) {
                    stack.push(t.right);
                }
                if (t.left != null) {
                    stack.push(t.left);
                } else if (t.right != null) {
                    sb.append("()");
                }
                set.add(t);
            }
        }
        return sb.substring(1, sb.length() - 1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        System.err.println(tree2strBFS(t1));
    }

}
