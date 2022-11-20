package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-11-20 18:22
 */
public class BinaryTree958 {

    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            // 遇到缺口之后，所有节点都必须也是缺口节点
            if (flag
                    && (poll.left != null || poll.right != null)
                    || (poll.left == null && poll.right != null)) {
                return false;
            }

            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }

            if (poll.left == null || poll.right == null) {
                flag = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        isCompleteTree(t1);
    }
}
