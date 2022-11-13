package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-13 18:17
 */
public class BinaryTree68_1 {

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
        run(root, p, q);
        return res;
    }

    public boolean run(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean findLeft = run(node.left, p, q) || node == p || node == q;
        boolean findRight = run(node.right, p, q) || node == p || node == q;
        if (findLeft && findRight) {
            res = node;
        }
        return findLeft || findRight;
    }
}
