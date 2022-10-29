package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * <p>
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 链接：https://leetcode.cn/problems/3Etpl5
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-29 20:14
 */
public class BinaryTree049 {

    static int result = 0;

    public static int sumNumbersDFS(TreeNode root) {
        run(root);
        return result;
    }

    public static void run(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            result += node.val;
            return;
        }
        if (node.left != null) {
            node.left.val += node.val * 10;
        }
        if (node.right != null) {
            node.right.val += node.val * 10;
        }
        run(node.left);
        run(node.right);
    }

    public int sumNumbersBFS(TreeNode root) {
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                if (item.left == null && item.right == null) {
                    result += item.val;
                    continue;
                }
                if (item.left != null) {
                    item.left.val += item.val * 10;
                    queue.add(item.left);
                }
                if (item.right != null) {
                    item.right.val += item.val * 10;
                    queue.add(item.right);
                }
            }
        }
        return result;
    }
}
