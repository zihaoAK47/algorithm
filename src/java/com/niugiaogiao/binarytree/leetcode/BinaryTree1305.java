package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序
 *
 * https://leetcode.cn/problems/all-elements-in-two-binary-search-trees/
 *
 * @author zihao
 */
public class BinaryTree1305 {

    static List<Integer> res = new LinkedList<>();
    static List<Integer> r1 = new ArrayList<>();
    static List<Integer> r2 = new ArrayList<>();

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        run(root1, r1);
        run(root2, r2);
        int index = 0;
        while (index < r1.size() && index < r2.size()) {
            if (r1.get(index) >= r2.get(index)) {
                res.add(r2.get(index));
                r2.remove(index);
            } else {
                res.add(r1.get(index));
                r2.remove(index);
            }
        }
        res.addAll(r1);
        res.addAll(r2);
        return res;
    }

    public static void run(TreeNode node, List<Integer> list) {
        if (node == null) return;
        run(node.left, list);
        list.add(node.val);
        run(node.right, list);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;

        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(0);
        TreeNode r3 = new TreeNode(3);
        r1.left = r2;
        r1.right = r3;

        getAllElements(t1, r1);
    }
}
