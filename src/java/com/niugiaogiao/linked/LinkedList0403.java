package com.niugiaogiao.linked;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedList0403 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        List<ListNode> list = new ArrayList<>();
        ListNode dummyHead = new ListNode(-1);
        while (!queue.isEmpty()) {
            ListNode cur = dummyHead;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                tree = queue.poll();
                cur.next = new ListNode(tree.val);
                cur = cur.next;
                if (tree.left != null) {
                    queue.offer(tree.left);
                }
                if (tree.right != null) {
                    queue.offer(tree.right);
                }
            }
            list.add(dummyHead.next);
        }

        return list.toArray(new ListNode[0]);
    }


    public ListNode[] listOfDepth1(TreeNode tree) {
        List<ListNode> result = new ArrayList<>();
        run(tree, 0 , result);
        return result.toArray(new ListNode[0]);
    }

    private void run(TreeNode tree, int deep, List<ListNode> result) {
        if (tree == null) {
            return;
        } else if (result.size() == deep) {
            result.add(new ListNode(tree.val));
        } else {
            ListNode cur = new ListNode(tree.val);
            cur.next = result.get(deep);
            result.set(deep, cur);
        }

        run(tree.right, deep + 1, result);
        run(tree.left, deep + 1, result);
    }


    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        r1.left = r2;
        r1.right = r3;
    }

}
