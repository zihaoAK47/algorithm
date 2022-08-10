package com.niugiaogiao.tree.other;

/**
 * 给定一棵二叉树的头节点 head，返回这颗二叉树是不是平衡二叉树
 * 什么是平衡树？
 * 在一棵二叉树中每个棵子树左树的高度差和右树的高度差不超过1
 * 1.左树是平衡树
 * 2.右树也是平衡树
 * 3.左数高度和右数高度差不超过 1
 * 上方列出了以 x 为头答案的可能性
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-10 22:30
 */
public class BinaryTreeIsBalance {

    static class Node {
        public Node left;
        public Node right;
    }

    static class Info {
        public boolean isBalance;
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isBalance=" + isBalance +
                    ", height=" + height +
                    '}';
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info process1 = process(head.left);
        Info process2 = process(head.right);
        int height = Math.max(process1.height, process2.height) + 1;
        if (!process1.isBalance || !process2.isBalance
                || Math.abs(process1.height - process2.height) > 1) {
            return new Info(false, height);
        }

        return new Info(true, height);
    }

    public static void main(String[] args) {
        Node r1 = new Node();
        Node r2 = new Node();
        Node r3 = new Node();
        Node r4 = new Node();
        Node r5 = new Node();
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r4.left = r5;
        System.err.println(process(r1));
    }
}
