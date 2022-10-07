package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 在二叉树中增加一行
 * <p>
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 * 注意，根节点 root 位于深度 1
 * 加法规则如下:
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 * <p>
 * 链接：https://leetcode.cn/problems/add-one-row-to-tree
 */
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

    public TreeNode addOneRowBFS(TreeNode root, int val, int depth) {
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
            for (int i = 0; i < size; ++i) {
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

    public TreeNode addOneRowDFS(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newNode = new TreeNode(val);
            newNode.left = root;
            return newNode;
        }
        run(root, val, depth, 1);
        return root;
    }

    public void run(TreeNode node, int val, int depth, int nowDeep) {
        if (node == null) return;

        if (depth - 1 == nowDeep) {
            TreeNode tLeft = node.left;
            TreeNode tRight = node.right;
            node.left = new TreeNode(val);
            node.right = new TreeNode(val);
            node.left.left = tLeft;
            node.right.right = tRight;
            return;
        }
        run(node.left, val, depth, nowDeep + 1);
        run(node.right, val, depth, nowDeep + 1);
    }
}
