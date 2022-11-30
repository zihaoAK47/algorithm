package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false
 *
 * https://leetcode.cn/problems/cousins-in-binary-tree/description/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-30 23:39
 */
public class BinaryTree993 {

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        int deepX = -1;
        int deepY = -1;
        TreeNode parentX = null;
        TreeNode parentY = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int itemDeep = 0;
        while (!queue.isEmpty() && (deepX == -1 || deepY == -1)) {
            itemDeep++;
            int size = queue.size();
            for (int i = 0 ; i < size ; ++i) {
                TreeNode itemNode = queue.poll();
                if (itemNode.left != null) {
                    queue.add(itemNode.left);
                    if (itemNode.left.val == x) {
                        deepX = itemDeep + 1;
                        parentX = itemNode;
                    } else if (itemNode.left.val == y) {
                        deepY = itemDeep + 1;
                        parentY = itemNode;
                    }
                }
                if (itemNode.right != null) {
                    queue.add(itemNode.right);
                    if (itemNode.right.val == x) {
                        deepX = itemDeep + 1;
                        parentX = itemNode;
                    } else if (itemNode.right.val == y) {
                        deepY = itemDeep + 1;
                        parentY = itemNode;
                    }
                }
            }
        }

        return parentX != null && parentY != null && deepX == deepY && parentX.val != parentY.val;
    }
}
