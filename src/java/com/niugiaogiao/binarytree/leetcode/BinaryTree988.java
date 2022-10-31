package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从叶结点开始的最小字符串
 * <p>
 * 给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个 [0, 25] 范围内的值，分别代表字母 'a' 到 'z'
 * 返回 按字典序最小 的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 * 注：字符串中任何较短的前缀在 字典序上 都是 较小 的：
 * 例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点
 * 节点的叶节点是没有子节点的节点。
 * <p>
 * 链接：https://leetcode.cn/problems/smallest-string-starting-from-leaf
 *
 * @author zihao
 */
public class BinaryTree988 {

    static StringBuilder stringBuilder = new StringBuilder();

    public static String smallestFromLeaf(TreeNode root) {
        String run = run1(root);
        return run;
    }

    public static String run1(TreeNode node) {
        if (node == null) return "";
        String left = run1(node.left);
        String right = run1(node.right);
        String minVal = ((char) (node.val + 'a')) + "";
        if (!"".equals(left) && !"".equals(right)) {
            left = left.concat(minVal);
            right = right.concat(minVal);
            return left.compareTo(right) >= 1 ? right  : left;
        } else {
            if (!"".equals(left)) {
                return left.concat(minVal);
            } else if (!"".equals(right)) {
                return right.concat(minVal);
            }
        }

        return minVal;
    }

    public static int run(TreeNode node, int deep) {
        if (node == null) return -1;
        int left = run(node.left, deep + 1);
        int right = run(node.right, deep + 1);
        int minVal = node.val;
        if (left != -1 && right != -1) {
            minVal = Math.min(right * 10 + node.val, left * 10 + node.val);
        } else {
            if (left != -1) {
                minVal = left * 10 + node.val;
            } else if (right != -1) {
                minVal = right * 10 + node.val;
            }
        }
        return minVal;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(0);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        smallestFromLeaf(t1);
    }
}
