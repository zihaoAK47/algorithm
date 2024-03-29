package com.niugiaogiao.binarytree.other;

import java.util.Stack;

/**
 * 给定一颗二叉树的节点，返回该节点的后继节点
 * 该二叉树有一个指针指向父节点，头节点的指针指向空
 * <p>
 * 后继节点：指的是一颗二叉树中，在中序遍历的序列中一个节点的下一个节点是谁就是后继节点
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-25 21:56
 */

public class BinaryTreeReturnNextNode {

    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;
    }

    /**
     * 思路一 因为有 parent 指针，一直指向二叉树的头部
     * 然后从头部遍历到之前节点的 next 节点
     */
    public static Node run1(Node node) {
        if (node == null) {
            return null;
        }
        Node root = null;
        Node backNode = node;
        while (node.parent != null) {
            node = node.parent;
        }
        root = node;

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                Node pop = stack.pop();
                if (pop == backNode && pop.right != null) {
                    System.err.println(pop.right.value);
                    return pop.right;
                } else if (pop == backNode && pop.left == null) {
                    Node pop1 = stack.pop();
                    System.err.println(pop1.value);
                    return pop1;
                }
                root = pop.right;
            }
        }

        return null;
    }


    /**
     * 并不一定需要每次都从头节点进行遍历
     * 因为给了 parent 指针，假设当前节点到下一个节点的距离是 k 的话，时间复杂度就是 O(k)
     *  二叉树递归序每一个节点都到达三次 时间复杂度 O(n)
     *
     *  若要实现 O(k) 的算法，需要讨论当前节点和它下一个节点的关系
     *  1.假设 x 有右树节点，后继节点必然是右树上最左的节点 （中序遍历 左 头 右）
     *  2.如果一个节点没有右树往上找，一直找到是某一个父节点的左孩子为止
     */
    public static Node run2(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node pre = node.right;
            while (pre.left != null) {
                pre = pre.left;
            }
            System.err.println(pre);
            return pre;
        } else {
            while (node.parent == node.parent.right) {
                node = node.parent;
            }
            System.err.println(node.parent);
            return node.parent;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();

        Node n5 = new Node();

        n1.value = 1;
        n2.value = 2;
        n3.value = 3;
        n4.value = 4;
        n5.value = 7;

        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;

        n3.parent = n2;
        n2.parent = n1;
        n4.parent = n2;

        System.err.println(run1(n2));
    }
}
