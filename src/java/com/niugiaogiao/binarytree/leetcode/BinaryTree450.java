package com.niugiaogiao.binarytree.leetcode;

/**
 * 删除二叉搜索树中的节点
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-25 12:02
 */
public class BinaryTree450 {

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

    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode targetNode = searchNode(root, key);
        if (targetNode == null) {
            return root;
        }
        TreeNode parentNode = searchParent(root, key);
        if (targetNode.left == null && targetNode.right == null) {
            if (parentNode != null && parentNode.left != null && parentNode.left.val == key) {
                parentNode.left = null;
            } else if (parentNode != null && parentNode.right != null && parentNode.right.val == key) {
                parentNode.right = null;
            } else {
                root = null;
            }
            return root;
        }
        if (targetNode.left != null && targetNode.right != null) {
            TreeNode lastNode = targetNode.right;
            while (lastNode.left != null) {
                lastNode = lastNode.left;
            }
            int t = lastNode.val;
            deleteNode(root, t);
            targetNode.val = t;
            return root;
        }

        if (targetNode.left != null) {
            if (parentNode != null && parentNode.left == targetNode) {
                parentNode.left = targetNode.left;
            } else if (parentNode != null && parentNode.right == targetNode) {
                parentNode.right = targetNode.left;
            } else {
                root = targetNode.left;
            }
        } else {
            if (parentNode != null && parentNode.left != null && parentNode.left == targetNode) {
                parentNode.left = targetNode.right;
            } else if (parentNode != null && parentNode.right != null && parentNode.right == targetNode) {
                parentNode.right = targetNode.right;
            } else {
                root = targetNode.right;
            }
        }

        return root;
    }


    public static TreeNode searchParent(TreeNode node, int key) {
        if (node == null) return null;
        if (node.left != null && node.left.val == key || node.right != null && node.right.val == key) {
            return node;
        }
        return searchParent((node.val < key) ? node.right : node.left, key);
    }

    public static TreeNode searchNode(TreeNode node, int key) {
        if (node == null) return null;
        if (node.val == key) return node;
        return searchNode((node.val < key) ? node.right : node.left, key);
    }

    /**
     * 二叉搜索树另一个删除实现
     */
    public static TreeNode delNode1(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val < key) {
            root.right = delNode1(root.right, key);
        } else if (root.val > key) {
            root.left = delNode1(root.left, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode node = root.right;
                while (node.left != null) {
                    node = node.left;
                }
                node.left = root.left;
                root = root.right;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        t1 = deleteNode(t1, 3);
        t1 = deleteNode(t1, 4);
        t1 = deleteNode(t1, 2);
        t1 = deleteNode(t1, 5);
        t1 = deleteNode(t1, 7);
        t1 = deleteNode(t1, 6);
        System.err.println("");
    }

}
