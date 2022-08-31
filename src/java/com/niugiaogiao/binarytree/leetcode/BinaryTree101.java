package com.niugiaogiao.binarytree.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree101 {

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

    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> left = new LinkedList<>();
        Deque<TreeNode> right = new LinkedList<>();
        left.addFirst(root);
        right.addFirst(root);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode leftNode = left.poll();
            TreeNode rightNode = right.poll();
            if (leftNode == null && rightNode != null || leftNode != null && rightNode == null) {
                return false;
            }
            // 左轴不对称
            if (leftNode.left == null && rightNode.right != null || leftNode.left != null && rightNode.right == null) {
                return false;
            }
            // 右轴不对称
            if (leftNode.right == null && rightNode.left != null || leftNode.right != null && rightNode.left == null) {
                return false;
            }
            if (leftNode.left != null && rightNode.right != null) {
                if (leftNode.left.val != rightNode.right.val) {
                    return false;
                }

                left.add(leftNode.left);
                right.add(rightNode.right);
            }
            if (leftNode.right != null && rightNode.left != null) {
                if (leftNode.right.val != rightNode.left.val) {
                    return false;
                }
                left.add(leftNode.right);
                right.add(rightNode.left);
            }
        }

        return left.isEmpty() && right.isEmpty();
    }

    public static boolean isSymmetric1(TreeNode root) {
        return run1(root, root);
    }

    public static boolean run1(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }

        boolean res1 = false;
        if (leftNode.left != null && rightNode.right != null) {
            if (leftNode.left.val != rightNode.right.val) {
                res1 = false;
            } else {
                res1 = run1(leftNode.left, rightNode.right);
            }
        } else if (leftNode.left == null && rightNode.right == null) {
            res1 = true;
        }

        boolean res2 = false;
        if (leftNode.right != null && rightNode.left != null) {
            if (leftNode.right.val != rightNode.left.val) {
                res2 = false;
            } else {
                res2 = run1(leftNode.right, rightNode.left);
            }
        } else if (leftNode.right == null && rightNode.left == null) {
            res2 = true;
        }

        return res1 && res2;
    }

    public static boolean isSymmetric2(TreeNode root) {
        return run2(root, root);
    }

    public static boolean run2(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }
        return leftNode.val == rightNode.val && run2(leftNode.left, rightNode.right) && run2(leftNode.right, rightNode.left);
    }

    public static boolean isSymmetric3(TreeNode root) {
        return run3(root, root);
    }

    public static boolean run3(TreeNode r1, TreeNode r2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(r1);
        queue.add(r2);
        while (!queue.isEmpty()) {
            TreeNode poll1 = queue.poll();
            TreeNode poll2 = queue.poll();
            if (poll1 == null && poll2 == null) {
                continue;
            }
            if ((poll1 == null || poll2 == null) || (poll1.val != poll2.val)) {
                return false;
            }
            queue.offer(poll1.left);
            queue.offer(poll2.right);

            queue.offer(poll1.right);
            queue.offer(poll2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;

        System.err.println(isSymmetric1(t1));
    }
}
