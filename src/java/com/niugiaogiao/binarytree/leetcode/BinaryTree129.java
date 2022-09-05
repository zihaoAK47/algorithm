package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-05 22:30
 */
public class BinaryTree129 {

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

    public int sumNumbers(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        int flag = 10;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size; i++) {
                TreeNode item = queue.poll();
                if (item.left == null && item.right == null) {
                    res += item.val;
                    continue;
                }
                if (item.left != null) {
                    item.left.val += item.val * flag;
                    queue.add(item.left);
                }
                if (item.right != null) {
                    item.right.val += item.val * flag;
                    queue.add(item.right);
                }
            }
        }

        return res;
    }

    int res = 0;
    public int sumNumbers1(TreeNode root) {
        run(root);
        return res;
    }

    public void run(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res += node.val;
            return;
        }
        if (node.left != null) {
            node.left.val += node.val * 10;
            run(node.left);
        }
        if (node.right != null) {
            node.right.val += node.val * 10;
            run(node.right);
        }
    }
}
