package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像
 * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
 *
 * @author zihao
 */

public class BinaryTree27 {

    static TreeNode res;

    public static TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        res = new TreeNode(root.val);
        run(root, res);
        return res;
    }

    public static void run(TreeNode node, TreeNode t) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            t.right = new TreeNode(node.left.val);
        }
        if (node.right != null) {
            t.left = new TreeNode(node.right.val);
        }
        run(node.left, t.right);
        run(node.right, t.left);
    }

    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree2(root.left);
        TreeNode right = mirrorTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);
        Queue<TreeNode> q2 = new LinkedList<>();
        TreeNode res = new TreeNode(root.val);
        q2.add(res);

        while (!q1.isEmpty()) {
            TreeNode q1Node = q1.poll();
            TreeNode q2Node = q1.poll();
            if (q1Node.left != null) {
                q2Node.right = new TreeNode(q1Node.left.val);
                q1.add(q1Node.left);
                q2.add(q2Node.right);
            }
            if (q1Node.right != null) {
                q2Node.left = new TreeNode(q1Node.right.val);
                q1.add(q1Node.right);
                q2.add(q2Node.left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
    }
}
