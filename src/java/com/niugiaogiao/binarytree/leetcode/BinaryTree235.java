package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-12 12:25
 */
public class BinaryTree235 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<TreeNode, TreeNode> dict = new HashMap<>();

    public TreeNode lowestCommonAncestorBFS1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        dict.put(root, null);
        setDictNode(root);
        Set<TreeNode> p1 = new HashSet<>();
        p1.add(p);
        while (p != null) {
            p = dict.get(p);
            p1.add(p);
        }
        while (!p1.contains(q)) {
            q = dict.get(q);
        }
        return q;
    }

    public void setDictNode(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            dict.put(node.left, node);
            setDictNode(node.left);
        }
        if (node.right != null) {
            dict.put(node.right, node);
            setDictNode(node.right);
        }
    }

    public static TreeNode lowestCommonAncestorBFS2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new LinkedList<>();
        List<TreeNode> path2 = new LinkedList<>();
        TreeNode t = root;
        while (t != p) {
            path1.add(t);
            if (t.val < p.val) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        path1.add(t);

        t = root;
        while (t != q) {
            path2.add(t);
            if (t.val < q.val) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        path2.add(t);

        TreeNode cur = null;
        for (int i = 0 ; i < path1.size() && i < path2.size() ; i++) {
            if (path1.get(i).val == path2.get(i).val) {
                cur = path1.get(i);
            } else {
                break;
            }
        }

        return cur;
    }

    public TreeNode lowestCommonAncestorBFS(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = null;
        while (root != null) {
            cur = root;
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                break;
            }
        }

        return cur;
    }

    public TreeNode lowestCommonAncestorDFS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorDFS(root.right, p ,q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorDFS(root.left, p ,q);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        t1.left = t2;
        lowestCommonAncestorBFS2(t1, t2, t1);
    }

}
