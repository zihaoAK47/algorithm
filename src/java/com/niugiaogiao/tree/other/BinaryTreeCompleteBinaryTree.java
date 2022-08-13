package com.niugiaogiao.tree.other;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉树的头节点 head，返回这棵二叉树中是不是完全二叉树
 * 完全二叉树 从左到右依次变满的状态都算完全二叉树
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-13 17:15
 */
public class BinaryTreeCompleteBinaryTree {

    static class Node {
        public Node left;
        public Node right;
        public int val;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 1.任何节点有右孩子无左孩子直接返回 false
     * 2.一旦遇到左右孩子都没有的节点，后续所遇到的所有节点必须为叶子节点
     */

    public static boolean run(Node root) {
        if (root == null) {
            return false;
        }
        boolean isLeaves = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node itemNode = queue.poll();

            if (
                    (isLeaves && (itemNode.left != null || itemNode.right != null))
                            ||
                            (itemNode.left == null && itemNode.right != null)
            ) {
                return false;
            }


            if (itemNode.left != null) {
                queue.add(itemNode.left);
            }
            if (itemNode.right != null) {
                queue.add(itemNode.right);
            }

            if (itemNode.left == null || itemNode.right == null) {
                isLeaves = true;
            }
        }

        return true;
    }

    static class Info {
        /**
         * 高度
         */
        public int height;
        /**
         * 是否完全二叉树
         */
        public boolean isCompletely;
        /**
         * 是否满二叉树
         */
        public boolean isFull;

        public Info(int height, boolean isCompletely, boolean isFull) {
            this.height =  height;
            this.isCompletely = isCompletely;
            this.isFull = isFull;
        }
    }

    /**
     * 信息分类
     * 1.当前节点是一棵满二叉树，需要获取的信息：左子树和右子树一定都是满二叉树，并且高度一样
     *
     * 2. 0 当前二叉树是这样一种情况    左树需要知道是否是完全二叉树，右树是否是满二叉树 并且 左树的高度 > 右树的高度
     *   0 0
     *  0
     *
     * 3.   0     当前节点是这样一种情况  左树是满的，右树是满的  并且左树高度 > 右树高度 1
     *    0   0
     *   0 0
     *
     * 4.   0       当前节点是这样一种情况 左树是满的，右树是完全二叉树，并且高度一样
     *    0   0
     *  0  0 0
     *
     */
    public static Info process(Node root) {
        if (root == null) {
            return new Info(0, true, true);
        }

        Info process1 = process(root.left);
        Info process2 = process(root.right);
        // self height
        int height = Math.max(process1.height, process2.height) + 1;
        boolean isFull = process1.isFull && process2.isFull && process1.height == process2.height;
        boolean isCompletely = false;
        if (isFull) {
            isCompletely = true;
        } else {
            if (process1.isCompletely && process2.isCompletely) {
                if (process1.isCompletely && process2.isFull && process1.height - process2.height == 1) {
                    isCompletely = true;
                }
                if (process1.isFull && process2.isFull && process1.height - process2.height == 1) {
                    isCompletely = true;
                }
                if (process1.isFull && process2.isCompletely && process1.height == process2.height) {
                    isCompletely = true;
                }
            }
        }

        return new Info(height, isCompletely, isFull);
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
        Node r9 = new Node(9);

        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
//        r2.right = r5;
//        r4.left = r8;
//        r4.right = r9;
//        r3.left = r6;
//        r3.right = r7;


        System.err.println(run(r1));
    }

}
