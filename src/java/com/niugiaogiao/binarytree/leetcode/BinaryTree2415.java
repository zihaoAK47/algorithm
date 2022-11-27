package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 2415. 反转二叉树的奇数层
 * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
 * 反转后，返回树的根节点。
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 * 节点的 层数 等于该节点到根节点之间的边数。
 * https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-28 0:27
 */
public class BinaryTree2415 {

    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int deep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> val = new ArrayList<>();
            Stack<TreeNode> tempNode = new Stack<>();
            for (int i = 0 ; i < size ; ++i) {
                TreeNode poll = queue.poll();
                val.add(poll.val);
                tempNode.push(poll);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }

            if (deep % 2 != 0) {
                for (int i = 0 ; i < val.size() ; ++i) {
                    tempNode.pop().val = val.get(i);
                }
            }
            deep++;
        }

        return root;
    }
}
