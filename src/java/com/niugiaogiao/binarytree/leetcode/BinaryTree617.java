package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两棵二叉树： root1 和 root2
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）
 * 你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 * <p>
 * 链接：https://leetcode.cn/problems/merge-two-binary-trees
 *
 * @author zihao
 */
public class BinaryTree617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return run(root1, root2);
    }

    public TreeNode run(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return null;
        TreeNode cur = new TreeNode(0);
        if (n1 != null && n2 != null) {
            cur.val = n1.val + n2.val;
        } else if (n1 != null) {
            cur.val = n1.val;
        } else {
            cur.val = n2.val;
        }
        cur.left = run(n1 == null ? n1 : n1.left, n2 == null ? n2 : n2.left);
        cur.right = run(n1 == null ? n1 : n1.right, n2 == null ? n2 : n2.right);
        return cur;
    }

    /**
     * 实现方式 2
     */
    public TreeNode mergeTreesDFS(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTreesDFS(root1.left, root2.left);
        node.right = mergeTreesDFS(root1.right, root2.right);
        return node;
    }

    public TreeNode mergeTreeBFS(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        Queue<TreeNode> result = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        TreeNode resNode = new TreeNode(root1.val + root2.val);
        result.add(resNode);
        queue1.add(root1);
        queue2.add(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode item1 = queue1.poll();
            TreeNode item2 = queue2.poll();
            TreeNode itemNode = result.poll();
            TreeNode itemLeft1 = item1.left;
            TreeNode itemLeft2 = item2.left;
            TreeNode itemRight1 = item1.right;
            TreeNode itemRight2 = item2.right;
            if (itemLeft1 != null || itemLeft2 != null) {
                if (itemLeft1 != null && itemLeft2 != null) {
                    TreeNode left = new TreeNode(itemLeft1.val + itemLeft2.val);
                    itemNode.left = left;
                    queue1.add(itemLeft1);
                    queue2.add(itemLeft2);
                    result.add(left);
                } else if (itemLeft1 != null) {
                    itemNode.left = itemLeft1;
                } else {
                    itemNode.left = itemLeft2;
                }
            }
            if (itemRight1 != null || itemRight2 != null) {
                if (itemRight1 != null && itemRight2 != null) {
                    TreeNode right = new TreeNode(itemRight1.val + itemRight2.val);
                    itemNode.right = right;
                    queue1.add(itemRight1);
                    queue2.add(itemRight2);
                    result.add(right);
                } else if (itemRight1 != null) {
                    itemNode.right = itemRight1;
                } else {
                    itemNode.right = itemRight2;
                }
            }
        }

        return resNode;
    }
}