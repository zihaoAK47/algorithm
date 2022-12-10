package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2471. 逐层排序二叉树所需的最少操作数目
 * https://leetcode.cn/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/description/
 * <p>
 * 在一步操作中，你可以选择 同一层 上任意两个节点，交换这两个节点的值
 * <p>
 * 返回每一层按 严格递增顺序 排序所需的最少操作数目
 * <p>
 * 节点的 层数 是该节点和根节点之间的路径的边数
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-10 23:26
 */
public class BinaryTree2471 {

    public int minimumOperations(TreeNode root) {
        if (root == null)
            return 0;

        int operatorCount = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int tempNum = Integer.MIN_VALUE;
            for (int i = 0; i < size; ++i) {
                TreeNode poll = queue.poll();
                if (tempNum == Integer.MIN_VALUE) {
                    tempNum = poll.val;
                } else if (poll.val < tempNum) {
                    operatorCount++;
                } else {
                    tempNum = poll.val;
                }

                if (poll.left != null)
                    queue.add(poll.left);

                if (poll.right != null)
                    queue.add(poll.right);
            }
        }

        return operatorCount;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 8, 5};
        // cala operator
        int len = nums.length;
        int operatorCount = 0;
        for (int i = 0; i < len; ++i) {
            int min = Integer.MAX_VALUE;
            int pos = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    pos = j;
                }
            }
            if (nums[i] > min) {
                System.err.println(nums[i] + "-----" + min);
                operatorCount++;
                nums[pos] = nums[i];
            }
        }
        System.err.println(operatorCount);
        System.err.println(Math.pow(10,5));
    }
}
