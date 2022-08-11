package com.niugiaogiao.tree.other;

/**
 * 给定一棵二叉树的头节点 head，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
 * 1.最大距离与当前 x 无关，要么是左树上的最大距离 要么是右树上的最大距离，选择左树和右树最大的那个
 * 2.最大距离与 x 有关，最大距离通过 x  左树高度 + 1 + 右树高度
 * <p>
 * 需要左树返回最大距离以及高度，右树返回最大距离以及高度
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-10 23:02
 */
public class BinaryTreeNodeMaxDistance {

    static class Node {
        public Node left;
        public Node right;
        public int val;
    }

    static class Info {
        public int max;
        public int height;

        public Info(int max, int height) {
            this.max = max;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "max=" + max +
                    ", height=" + height +
                    '}';
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info process1 = process(head.left);
        Info process2 = process(head.right);
        int selfHeight = Math.max(process1.height, process2.height) + 1;
        int max = Math.max(Math.max(process1.max, process2.max), process1.height + process2.height + 1);
        return new Info(max, selfHeight);
    }

    public static void main(String[] args) {
        Node r1 = new Node();
        Node r2 = new Node();
        Node r3 = new Node();
        Node r4 = new Node();
        Node r5 = new Node();
        Node r6 = new Node();
        Node r7 = new Node();
        Node r8 = new Node();
        Node r9 = new Node();
        r1.val = 1;
        r2.val = 2;
        r3.val = 3;
        r4.val = 4;
        r5.val = 5;
        r6.val = 6;
        r7.val = 7;
        r8.val = 8;
        r9.val = 9;
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        r6.left = r8;
        r7.left = r9;
        System.err.println(process(r1));
    }
}
