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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        tInorder = inorder;
        tPostOrder = postorder;
        dict = new HashMap<>(inorder.length);
        for (int i = len - 1; i >= 0; i--) {
            dict.put(inorder[i], i);
        }

        return run(len - 1, 0, len - 1, 0);
    }

    public TreeNode run(int iStart, int iEnd, int pStart, int pEnd) {
        if (pStart < pEnd) {
            return null;
        }

        int inorderPos = dict.get(tPostOrder[pStart]);
        int leftNodeSize = iEnd + inorderPos;
        TreeNode node = new TreeNode(tPostOrder[pEnd]);

        return null;
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
