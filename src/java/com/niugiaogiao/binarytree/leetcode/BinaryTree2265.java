package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 2265. 统计值等于子树平均值的节点数
 * https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree/
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-06 21:43
 */
public class BinaryTree2265 {

    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Info info = new Info(0, 0);
        getSum(root, info);
        if (info.sum / info.count == root.val) {
            count += 1;
        }
        count += averageOfSubtree(root.left);
        count += averageOfSubtree(root.right);
        return count;
    }

    static class Info {
        int sum;
        int count;

        public Info(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    public void getSum(TreeNode node, Info info) {
        if (node == null) {
            return;
        }
        info.count++;
        info.sum += node.val;
        getSum(node.left, info);
        getSum(node.right, info);
    }


    int res=0;
    int count=0;
    public int averageOfSubtree1(TreeNode root) {
        countDFS(root);
        return res;
    }

    public int countDFS(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int current = count++;
        int val = countDFS(node.left) + countDFS(node.right) + node.val;
        if (node.val == val / (count - current)) {
            res++;
        }
        return val;
    }
}
