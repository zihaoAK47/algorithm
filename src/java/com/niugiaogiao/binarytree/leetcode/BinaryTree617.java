package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

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
}
