package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1448. 统计二叉树中好节点的数目
 * https://leetcode.cn/problems/count-good-nodes-in-binary-tree/description/
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值
 */
public class BinaryTree1448 {

    static TreeNode ROOT = null;
    static Map<TreeNode, TreeNode> dict = new HashMap<>();
    public static int goodNodes(TreeNode root) {
        ROOT = root;
        dict.put(root, null);
        saveParent(root);
        return calc(root);
    }

    public static void saveParent(TreeNode node) {
        if (node.left != null) {
            dict.put(node.left, node);
        }
        if (node.right != null) {
            dict.put(node.right, node);
        }
    }

    public static int calc(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int resCount = 0;
        TreeNode parent = node;
        while ((parent = dict.get(parent)) != null) {
            if (parent.val > node.val) {
                break;
            }
        }
        if (parent == null) {
            resCount++;
        }
        resCount += calc(node.left);
        resCount += calc(node.right);
        return resCount;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(2);
        t1.left = t2;
        t2.left = t3;
        t3.right = t4;
        goodNodes(t1);
    }
}
