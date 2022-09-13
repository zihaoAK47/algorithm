package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-12 20:31
 */
public class BinaryTree236 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<TreeNode, TreeNode> dict = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dict.put(root, null);
        saveDict(root);
        Set<TreeNode> pSet = new HashSet<>();
        while (p != null) {
            pSet.add(p);
            p = dict.get(p);
        }

        while (!pSet.contains(q)) {
            q = dict.get(q);
        }

        return q;
    }

    public void saveDict(TreeNode node) {
        if (node.left != null) {
            dict.put(node.left, node);
            saveDict(node.left);
        }
        if (node.right != null) {
            dict.put(node.right, node);
            saveDict(node.right);
        }
    }

    static TreeNode parent = null;

    public static TreeNode lowestCommonAncestorDFS(TreeNode root, TreeNode p, TreeNode q) {
        run(root, p, q);
        return parent;
    }

    public static boolean run(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return false;
        boolean left = run(node.left, p, q) || node == p || node == q;
        boolean right = run(node.right, p, q) || node == q || node == p;
        parent = left && right ? node : parent;
        return left || right;
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(5);
        TreeNode r3 = new TreeNode(1);
        TreeNode r4 = new TreeNode(6);
        TreeNode r5 = new TreeNode(2);
        TreeNode r6 = new TreeNode(0);
        TreeNode r7 = new TreeNode(8);
        TreeNode r8 = new TreeNode(7);
        TreeNode r9 = new TreeNode(4);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        r5.left = r8;
        r5.right = r9;

//        TreeNode t1 = new TreeNode(2);
//        TreeNode t2 = new TreeNode(1);
//        t1.left = t2;

        lowestCommonAncestorDFS(r1, r7, r8);
        System.err.println(parent.val);
    }

}
