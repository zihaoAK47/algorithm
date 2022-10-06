package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree623 {

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

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newNode = new TreeNode(val);
            newNode.left = root;
            return newNode;
        }
        int deep = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty() && deep != depth - 1) {
            int size = queue.size();
            deep++;
            for (int i = 0 ; i < size ; ++i) {
                TreeNode poll = queue.poll();
                if (deep == depth - 1) {
                    TreeNode tLeft = poll.left;
                    TreeNode tRight = poll.right;
                    poll.left = new TreeNode(val);
                    poll.right = new TreeNode(val);
                    poll.left.left = tLeft;
                    poll.right.right = tRight;
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return root;
    }
}
