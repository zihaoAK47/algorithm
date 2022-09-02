package com.niugiaogiao.binarytree.leetcode;

import java.util.*;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTree113 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSumBFS(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();
        queueNode.add(root);
        queueVal.add(root.val);

        Map<TreeNode, TreeNode> dict = new HashMap<>();
        dict.put(root, null);
        while (!queueNode.isEmpty()) {
            TreeNode pollNode = queueNode.poll();
            Integer pollVal = queueVal.poll();
            if (pollNode.left == null && pollNode.right == null) {
                if (pollVal == targetSum) {
                    LinkedList<Integer> path = new LinkedList<>();
                    path.addFirst(pollNode.val);
                    TreeNode temp = pollNode;
                    while ((temp = dict.get(temp)) != null) {
                        path.addFirst(temp.val);
                    }
                    res.add(path);
                }
                continue;
            }
            if (pollNode.left != null) {
                dict.put(pollNode.left, pollNode);
                queueNode.add(pollNode.left);
                queueVal.add(pollNode.left.val + pollVal);
            }
            if (pollNode.right != null) {
                dict.put(pollNode.right, pollNode);
                queueNode.add(pollNode.right);
                queueVal.add(pollNode.right.val + pollVal);
            }
        }

        return res;
    }

    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pathSumDFS(TreeNode root, int targetNum) {
        run(root, targetNum);
        return res;
    }

    public void run(TreeNode root, int targetNum) {
        if (root == null) return;

        path.addLast(root.val);
        targetNum -= root.val;
        if (root.left == null && root.right == null && targetNum == 0)
            res.add(new LinkedList<>(path));

        run(root.left, targetNum);
        run(root.right,targetNum);
        path.pollFirst();
    }
}
