package com.niugiaogiao.linked.leetcode;


/**
 * 给你一棵以root为根的二叉树和一个head为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以head为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedList1367 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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

    public static boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public static boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (head.val != root.val) {
            return false;
        }

        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }


    static boolean status = false;

    /**
     * 执行速度太慢了
     *
     * @param head
     * @param root
     */
    public static void run(ListNode head, TreeNode root) {
        if (root == null) {
            return;
        }
        if (status) {
            return;
        }
        if (head.val == root.val) {
            if (root.left != null && head.next != null && head.next.val == root.left.val) {
                run(head.next, root.left);
            }
            if (root.right != null && head.next != null && head.next.val == root.right.val) {
                run(head.next, root.right);
            }
            if (head.next == null) {
                status = true;
            }
        }
        run(head, root.left);
        run(head, root.right);
    }

    public static ListNode createListNode(int[] data) {
        ListNode res = new ListNode(0);
        ListNode pre = res;
        for (int item : data) {
            pre.next = new ListNode(item);
            pre = pre.next;
        }

        return res.next;
    }

    public static void main(String[] args) {
        ListNode listNode = createListNode(new int[]{1, 10});
//        TreeNode r1 = new TreeNode(1);
//        TreeNode r2 = new TreeNode(4);
//        TreeNode r3 = new TreeNode(4);
//        TreeNode r4 = new TreeNode(2);
//        TreeNode r5 = new TreeNode(2);
//        TreeNode r6 = new TreeNode(1);
//        TreeNode r7 = new TreeNode(6);
//        TreeNode r8 = new TreeNode(8);
//        TreeNode r9 = new TreeNode(1);
//        TreeNode r10 = new TreeNode(3);
//        r1.left = r2;
//        r1.right = r3;
//        r2.right = r4;
//        r4.left = r6;
//        r3.left = r5;
//        r5.left = r7;
//        r5.right = r8;
//        r8.left = r9;
//        r8.right = r10;
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(1);
        TreeNode r3 = new TreeNode(10);
        TreeNode r4 = new TreeNode(1);
        TreeNode r5 = new TreeNode(9);
        r1.right = r2;
        r2.left = r3;
        r2.right = r4;
        r4.left = r5;
        run(listNode, r1);
        System.err.println(status);
    }
}
