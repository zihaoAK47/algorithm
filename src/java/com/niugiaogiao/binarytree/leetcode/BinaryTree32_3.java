package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.*;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * 请实现一个函数按照之字形顺序打印二叉树
 * 即第一行按照从左到右的顺序打印
 * 第二层按照从右到左的顺序打印
 * 第三行再按照从左到右的顺序打印，其他行以此类推
 * <p>
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/description/
 *
 * @author zihao
 */
public class BinaryTree32_3 {

    public static List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean up = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> deep = new LinkedList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode item = queue.poll();
                if (up) {
                    deep.addLast(item.val);
                } else {
                    deep.addFirst(item.val);
                }

                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
            up = !up;
            res.add(deep);
        }
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return res;
        }
        run(root,1);
        return res;
    }

    public void run(TreeNode node, int deep) {
        if (node == null) {
            return;
        }
        if (deep > res.size()) {
            LinkedList<Integer> t = new LinkedList<>();
            t.add(node.val);
            res.add(t);
        } else {
            LinkedList<Integer> list = (LinkedList) res.get(deep - 1);
            if ((deep - 1) % 2 == 0) {
                list.addLast(node.val);
            } else {
                list.addFirst(node.val);
            }
        }
        run(node.left, deep + 1);
        run(node.right, deep + 1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;
    }
}
