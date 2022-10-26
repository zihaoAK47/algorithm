package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个
 * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立
 * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值
 * 如果第二小的值不存在的话，输出 -1 。
 * <p>
 * 链接：https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree
 *
 * @author zihao
 */

public class BinaryTree671 {

    int res = -1;
    int first = 0;

    public int findSecondMinimumValue(TreeNode root) {
        first = root.val;
        run(root);
        return res;
    }

    public void run(TreeNode node) {
        if (node == null) return;
        ;
        if (res != -1 && node.val >= res) return;
        if (node.val > first) res = node.val;
        run(node.left);
        run(node.right);
    }
}
