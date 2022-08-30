package com.niugiaogiao.tree.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BinaryTree106 {

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

    Map<Integer, Integer> dict;
    int[] tInorder;
    int[] tPostOrder;
    int index = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        tInorder = inorder;
        tPostOrder = postorder;
        index = len - 1;
        for (int i = 0; i < inorder.length; i++) {
            dict.put(inorder[i], i);
        }

        return run(0, inorder.length - 1);
    }

    public TreeNode run(int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(tPostOrder[index]);
        int inorderPos = dict.get(root.val);
        index--;
        root.right = run(inorderPos + 1, end);
        root.left = run(start, inorderPos - 1);
        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        deque.add(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 1 - 1; i >= 0; i--) {
            TreeNode node = deque.peek();
            int val = postorder[i];
            if (inorder[inorderIndex] != node.val) {
                node.right = new TreeNode(val);
                deque.push(node.right);
            } else {
                while (!deque.isEmpty() && inorder[inorderIndex] == deque.peek().val) {
                    inorderIndex--;
                    node = deque.poll();
                }
                node.left = new TreeNode(val);
                deque.push(node.left);
            }
        }

        return root;
    }

}
