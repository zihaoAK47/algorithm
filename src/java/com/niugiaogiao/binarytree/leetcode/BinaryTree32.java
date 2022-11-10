package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * <p>
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/description/
 *
 * @author zihao
 */

public class BinaryTree32 {

    public int[] levelOrderBFS(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode item = queue.poll();
            res.add(item.val);
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }


        int[] returnRes = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            returnRes[i] = res.get(i);
        }

        return returnRes;
    }


    List<List<Integer>> saveNode = new LinkedList<>();
    int count = 0;
    public int[] levelOrderDFS(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        run(root, 1);
        int[] res = new int[count];
        int flag = 0;
        for (int i = 0; i < saveNode.size(); ++i) {
            List<Integer> list = saveNode.get(i);
            for (int j = 0; j < list.size(); ++j) {
                res[flag++] = list.get(j);
            }
        }
        return res;
    }

    public void run(TreeNode node, int deep) {
        if (node == null) {
            return;
        }
        count++;
        if (deep > saveNode.size()) {
            List<Integer> t = new ArrayList<>();
            t.add(node.val);
            saveNode.add(deep - 1, t);
        } else {
            List<Integer> list = saveNode.get(deep - 1);
            list.add(node.val);
        }
        run(node.left, deep + 1);
        run(node.right, deep + 1);
    }
}