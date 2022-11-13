package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-11-13 18:17
 */
public class BinaryTree68 {

    Map<TreeNode, TreeNode> dict = new HashMap<>();

    public TreeNode lowestCommonAncestorDFS1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        dict.put(root, null);
        save(root);
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = dict.get(p);
        }
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = dict.get(q);
        }

        return null;
    }

    public void save(TreeNode node) {
        if (node.left != null) {
            dict.put(node.left, node);
            save(node.left);
        }
        if (node.right != null) {
            dict.put(node.right, node);
            save(node.right);
        }
    }


    TreeNode res = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || q == null || p == null) {
            return null;
        }
        run(root, p , q);
        return res;
    }

    public boolean run(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean findLeft = run(node.left, p, q) || node == p || node == q;
        boolean findRight = run(node.right, p , q) || node == p || node == q;
        if (findLeft && findRight) {
            res = node;
        }
        return findLeft || findRight;
    }
}
