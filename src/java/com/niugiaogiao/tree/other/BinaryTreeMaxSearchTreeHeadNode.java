package com.niugiaogiao.tree.other;

/**
 * 给定一棵二叉树的头节点 head
 * 返回这棵二叉树中最大的二叉搜索子树的头节点
 * <p>
 * 搜索二叉树：整个树上没有重复值，左树的值都比我小，右树的值都比我大，每一棵子树都一样
 * 问题是：整棵树里面未必完全都是搜索二叉树，需要求出满足搜索二叉树的最大子树是谁？
 * 子树是整颗子树都要，如果有一部分满足，最后一个节点不满足是不可以的
 * 1.你要知道当前是否是搜索二叉树
 * 2.你要知道当前搜索二叉树的最大节点有多少
 * <p>
 * 分类：
 * 1.与 x 无关，即最大二叉搜索子树不以 x 为头，左树上满足最大二叉搜索树的大小 或者右树上满足最大二叉搜索树的大小
 * 2.与 x 有关，以 x 为头整体的大小
 * 左树是搜索二叉树，右树是搜索二叉树，左树最大值小于 x ，右树最小值 > x ，此条件才能够成立
 * <p>
 * 信息整理：
 * 左树：最大二叉搜索子树的整体大小，是否是二叉搜索子树，左树最大节点
 * 右数：最大二叉搜索子树的整体大小，是否是二叉搜索子树，右树最小节点
 * 此时左数的返回数据和右树的返回数据不是一样的，求全集，因为是递归函数，不想对左树要求和右树要求做区分
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-11 22:42
 */
public class BinaryTreeMaxSearchTreeHeadNode {

    static class Node {
        public Node left;
        public Node right;
        public int val;

        Node(int val) {
            this.val = val;
        }
    }

    static class Info {
        public boolean isBST;
        public int nodeSize;
        public int leftMax;
        public int rightMin;

        public Node root;

        public Info(boolean isBST, int nodeSize, int leftMax, int rightMin, Node root) {
            this.isBST = isBST;
            this.nodeSize = nodeSize;
            this.leftMax = leftMax;
            this.rightMin = rightMin;
            this.root = root;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isBST=" + isBST +
                    ", nodeSize=" + nodeSize +
                    ", leftMax=" + leftMax +
                    ", rightMin=" + rightMin +
                    ", root=" + root +
                    '}';
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info process1 = process(head.left);
        Info process2 = process(head.right);
        int leftMax = head.val;
        int rightMin = head.val;
        if (process1 != null) {
            leftMax = Math.max(leftMax, process1.leftMax);
            rightMin = Math.min(rightMin, process1.rightMin);
        }
        if (process2 != null) {
            leftMax = Math.max(leftMax, process2.leftMax);
            rightMin = Math.min(rightMin, process2.rightMin);
        }
        int nodeSize = 0;
        if (process1 != null) {
            nodeSize = Math.max(nodeSize, process1.nodeSize);
        }
        if (process2 != null) {
            nodeSize = Math.max(nodeSize, process2.nodeSize);
        }

        boolean isBST = false;
        Node root = null;
        if ((process1 == null ? true : process1.isBST) &&
                (process2 == null ? true : process2.isBST) &&
                (process1 == null ? true : process1.leftMax < head.val)
                &&
                (process2 == null ? true : process2.rightMin > head.val)
        ) {
            isBST = true;
            root = head;
            nodeSize = (process1 == null ? 0 : process1.nodeSize) + (process2 == null ? 0 : process2.nodeSize) + 1;
        }


        return new Info(isBST, nodeSize, leftMax, rightMin, root);
    }

    /**
     * 1.收集最大子二叉搜索树的节点个数
     * 2.收集最大子二叉搜索树的头节点
     *
     * @param head
     * @return
     */
    public static Info process1(Node head) {
        if (head == null) {
            return null;
        }
        Info process1 = process1(head.left);
        Info process2 = process1(head.right);
        int leftMax = head.val;
        int rightMin = head.val;
        leftMax = process1 != null ? Math.max(leftMax, process1.leftMax) : leftMax;
        leftMax = process2 != null ? Math.max(leftMax, process2.leftMax) : leftMax;
        rightMin = process1 != null ? Math.min(rightMin, process1.rightMin) : rightMin;
        rightMin = process2 != null ? Math.min(rightMin, process2.rightMin) : rightMin;

        int nodeSize = 0;
        if (process1 != null) {
            nodeSize = Math.max(nodeSize, process1.nodeSize);
        }
        if (process2 != null) {
            nodeSize = Math.max(nodeSize, process2.nodeSize);
        }

        Node root = null;
        boolean isBst = false;
        if ((process1 == null || process1.isBST)
                && (process2 == null || process2.isBST)
                && (process1 == null || process1.leftMax < head.val)
                && (process2 == null || process2.rightMin > head.val)) {
            isBst = true;
            root = head;
            nodeSize = (process1 == null ? 0 : process1.nodeSize)
                    + (process2 == null ? 0 : process2.nodeSize)
                    + 1;
        }

        if (root == null && process1 != null && process2 != null && process1.isBST && process2.isBST) {
            root = (process1.nodeSize > process2.nodeSize) ? process1.root : process2.root;
        }

        return new Info(isBst, nodeSize, leftMax, rightMin, root);
    }

    public static void main(String[] args) {
        Node r1 = new Node(1);
        Node r2 = new Node(2);
        Node r3 = new Node(3);
        Node r4 = new Node(4);
        Node r5 = new Node(5);
        Node r6 = new Node(6);
        Node r7 = new Node(7);
        Node r8 = new Node(8);
        r1.left = r3;
        r1.right = r7;
        r3.left = r2;
        r3.right = r4;
        r7.left = r6;
        r7.right = r8;
        r6.left = r5;

        Info info = process1(r1);
        System.err.println("");
    }
}
