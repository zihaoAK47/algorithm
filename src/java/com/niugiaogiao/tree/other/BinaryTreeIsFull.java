package com.niugiaogiao.tree.other;

/**
 * 给定一棵二叉树的头节点 head，返回这棵二叉树是否是满二叉树
 * 满二叉树性质：高度是 L，节点树是 N 的话
 * 只有满二叉树满足 2^l - 1 = N
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-13 16:56
 */
public class BinaryTreeIsFull {

    static class Node {
        public Node left;
        public Node right;
    }

    static class Info {
        public int height;
        public int nodeCount;

        public Info(int height, int nodeCount) {
            this.height = height;
            this.nodeCount = nodeCount;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info process1 = process(node.left);
        Info process2 = process(node.right);
        int selfHeight = Math.max(process1.height, process2.height) + 1;
        int nodeCount = process1.nodeCount + process2.nodeCount + 1;
        return new Info(selfHeight, nodeCount);
    }

    public static void main(String[] args) {
        Node r1 = new Node();
        Node r2 = new Node();
        Node r3 = new Node();
        r1.left = r2;
        r1.right = r3;
        Info process = process(r1);
        System.err.println(Math.pow(2,process.height) - 1 == process.nodeCount);
    }
}
