package com.niugiaogiao.linked.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同
 */
public class LinkedList114 {

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

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> tempSaveNode = new ArrayList<>();
        saveNode(tempSaveNode, root);
        for (int i = 0; i < tempSaveNode.size() - 1; i++) {
            tempSaveNode.get(i).left = null;
            tempSaveNode.get(i).right = tempSaveNode.get(i + 1);
        }
        tempSaveNode.get(tempSaveNode.size() - 1).left = null;
    }

    private static void saveNode(List<TreeNode> save, TreeNode root) {
        if (root == null) {
            return;
        }
        save.add(root);
        saveNode(save, root.left);
        saveNode(save, root.right);
    }

    /**
     * 实现方式 二
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param root
     */
    public static void faltten1(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode preNode = null;
        TreeNode nowNode = null;
        while (!stack.isEmpty()) {
            nowNode = stack.pop();
            if (preNode != null) {
                preNode.left = null;
                preNode.right = nowNode;
            }
            if (nowNode.right != null) {
                stack.push(nowNode.right);
            }
            if (nowNode.left != null) {
                stack.push(nowNode.left);
            }

            preNode = nowNode;
        }
    }

    /**
     * 实现方式三
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param root
     */
    public static void faltten2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode pre = null;
        TreeNode next = null;
        TreeNode current = root;
        while (current != null) {
            pre = null;
            next = null;
            if (current.left != null) {
                pre = current.left;
                next = current.left;
            }
            // 找到左右节点
            while (next != null && next.right != null) {
                next = next.right;
            }
            if (next != null) {
                next.right = current.right;
                current.right = pre;
            }
            current.left = null;
            current = current.right;
        }
    }

    public void flatten4(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    private static TreeNode last = null;

    public static void faltten3(TreeNode root) {
        if (root == null) {
            return;
        }
        faltten3(root.right);
        faltten3(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode();
        TreeNode r2 = new TreeNode();
        r1.val = 1;
        r2.val = 2;
        r1.left = r2;
        flatten(r1);
    }

}
