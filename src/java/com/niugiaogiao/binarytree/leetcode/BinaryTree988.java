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

    static StringBuilder sb = new StringBuilder();
    static String res = ((char) ('z' + 1)) + "";

    public static String smallestFromLeaf(TreeNode root) {
        run(root);
        return res;
    }

    public static void run(TreeNode node) {
        if (node == null) return;
        sb.append((char) (node.val + 'a'));
        if (node.left == null && node.right == null) {
            sb.reverse();
            String t = sb.toString();
            sb.reverse();
            if (t.compareTo(res) < 0) {
                res = t;
            }
        }

        run(node.left);
        run(node.right);
        sb.deleteCharAt(sb.length() - 1);
    }

    /**
     * 错误的实现方式，不会回朔
     */
    public static String runField(TreeNode node) {
        if (node == null) return "";
        String left = runField(node.left);
        String right = runField(node.right);
        String minVal = ((char) (node.val + 'a')) + "";
        if (!"".equals(left) && !"".equals(right)) {
            left = left.concat(minVal);
            right = right.concat(minVal);
            return left.compareTo(right) >= 1 ? right : left;
        } else {
            if (!"".equals(left)) {
                return left.concat(minVal);
            } else if (!"".equals(right)) {
                return right.concat(minVal);
            }
        }

        return minVal;
    }
}
