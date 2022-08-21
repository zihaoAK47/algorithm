package com.niugiaogiao.linked.leetcode;

/**
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * <p>
 * 返回转换后的单向链表的头节点。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binode-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-21 23:11
 */
public class LinkedList1712 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static TreeNode head = null;
    static TreeNode pre = null;

    public static TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        run(root);
        return pre;
    }

    public static void run(TreeNode root) {
        if (root == null) {
            return;
        }
        run(root.left);
        if (head == null) {
            head = root;
            pre = root;
        } else {
            root.left = null;
            pre.right = root;
            pre = pre.right;
        }
        run(root.right);
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(4);
        TreeNode r2 = new TreeNode(1);
        TreeNode r3 = new TreeNode(5);
        r1.left = r2;
        r1.right = r3;
        convertBiNode(r1);
        System.err.println("");
    }

}
