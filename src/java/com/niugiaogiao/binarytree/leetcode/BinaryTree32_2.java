package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 *
 * @author zihao
 */

public class BinaryTree32_2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> deep = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                deep.add(item.val);
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
            res.add(deep);
        }

        return res;
    }
}
