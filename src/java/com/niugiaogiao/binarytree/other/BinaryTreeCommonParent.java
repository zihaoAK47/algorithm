package com.niugiaogiao.binarytree.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一棵二叉树的头节点 head 。和另外两个节点 a 和 b
 * 返回 a 和 b 的最低公共祖先
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-14 7:28
 */
public class BinaryTreeCommonParent {

    static class Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node process1(Node root, Node node1, Node node2) {
        if (root == null) {
            return null;
        }
        // 存入 hash 表中
        Map<Node, Node> dict = new HashMap<>();
        dict.put(root, null);
        saveHash(root, dict);
        // 将 node1 的节点存入 set 中
        Set<Node> nodeParent1 = new HashSet<>();
        while (node1 != null) {
            Node parent = dict.get(node1);
            nodeParent1.add(parent);
            node1 = parent;
        }
        // search common parent
        while (!nodeParent1.contains(node2)) {
            node2 = dict.get(node2);
        }

        return node2;
    }

    public static void saveHash(Node node, Map<Node, Node> dict) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            dict.put(node.left, node);
            saveHash(node.left, dict);
        }
        if (node.right != null) {
            dict.put(node.right, node);
            saveHash(node.right, dict);
        }
    }

    static class Info {
        boolean leftFlag;
        boolean rightFlag;
        Node parentNode;

        public Info(boolean leftFlag, boolean rightFlag, Node parentNode) {
            this.leftFlag = leftFlag;
            this.rightFlag = rightFlag;
            this.parentNode = parentNode;
        }
    }

    /**
     * 假设以 x 节点为头
     * 1. o1，o2 最初的交汇点可能不在 x 这棵树上，也可能 x 树上既没有 o1 也 没有 o2
     * 2. o1, o2 只有一个节点在 x 上
     * 3. o1，o2 都在 x 为头的树上
     * 3.1 左子树或者右子树分别有一个
     * 3.2 左子树上包含所有的 o1 和 o2
     * 3.3 右子树上包含所欲的 o1 和 o2
     * 4. x 本身可能是 o1 或者 o2
     */
    public static Info process2(Node root, Node node1, Node node2) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info info1 = process2(root.left, node1, node2);
        Info info2 = process2(root.right, node1, node2);
        boolean leftFlag = info1.leftFlag || info1.rightFlag || root == node1;
        boolean rightFlag = info2.leftFlag || info2.rightFlag || root == node2;

        Node currentNode = null;
        if (info1.parentNode != null) {
            currentNode = info1.parentNode;
        }
        if (info2.parentNode != null) {
            currentNode = info2.parentNode;
        }
        if (currentNode == null) {
            if (leftFlag && rightFlag) {
                currentNode = root;
            }
        }

        return new Info(leftFlag, rightFlag, currentNode);
    }

    public static void main(String[] args) {
//        Node r1 = new Node(1);
//        Node r2 = new Node(2);
//        Node r3 = new Node(3);
//        Node r4 = new Node(4);
//        Node r5 = new Node(5);
//        r1.left = r2;
//        r1.right = r3;
//        r2.left = r4;
//        r2.right = r5;
//        System.err.println(process1(r1, r4, r5).val);
        Node r1 = new Node(2);
        Node r2 = new Node(1);
//        Node r3 = new Node(3);
//        Node r4 = new Node(4);
//        Node r5 = new Node(5);
        r1.left = r2;
//        r1.right = r3;
//        r2.left = r4;
//        r3.left = r5;

        Info info = process2(r1, r1, r2);
        System.err.println(info.parentNode.val);
    }
}
