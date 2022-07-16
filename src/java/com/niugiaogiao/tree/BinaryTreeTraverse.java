package com.niugiaogiao.tree;

import java.util.Stack;

/**
 * 二叉树前序，中序，后序 递归遍历以及循环遍历
 * @author zi hao
 * @version 1.0
 * @date 2022-05-06 20:14
 */
public class BinaryTreeTraverse {

    static class Tree<T> {
        public T data;
        public Tree<T> left;
        public Tree<T> right;

        @Override
        public String toString() {
            return "Tree{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static <T> void beforeRecursive(Tree<T> tree) {
        if (tree == null) {
            return;
        }

        System.err.println(tree);
        beforeRecursive(tree.left);
        beforeRecursive(tree.right);
    }

    public static <T> void midRecursive(Tree<T> tree) {
        if (tree == null) {
            return;
        }

        midRecursive(tree.left);
        System.err.println(tree);
        midRecursive(tree.right);
    }

    public static <T> void afterRecursive(Tree<T> tree) {
        if (tree == null) {
            return;
        }

        afterRecursive(tree.left);
        afterRecursive(tree.right);
        System.err.println(tree);
    }

    public static <T> void beforeLoop(Tree<T> tree) {
        if (tree != null) {
            Stack<Tree<T>> stack = new Stack<>();
            stack.push(tree);
            while (!stack.isEmpty()) {
                tree = stack.pop();
                System.err.println(tree);
                if (tree.right != null)
                    stack.push(tree.right);
                if (tree.left != null)
                    stack.push(tree.left);
            }
        }
    }

    public static <T> void midLoop(Tree<T> tree) {
        if (tree != null) {
            Stack<Tree<T>> stack = new Stack<>();
            while (!stack.isEmpty() || tree != null) {
                if (tree != null) {
                    stack.push(tree);
                    tree = tree.left;
                } else {
                    tree = stack.pop();
                    System.err.println(tree);
                    tree = tree.right;
                }
            }
        }
    }

    public static <T> void afterLoop1(Tree<T> tree) {
        if (tree != null) {
            Stack<Tree<T>> s1 = new Stack<>();
            s1.push(tree);
            Stack<Tree<T>> s2 = new Stack<>();
            while (!s1.isEmpty()) {
                tree = s1.pop();
                s2.push(tree);
                if (tree.left != null) {
                    s1.push(tree.left);
                }

                if (tree.right != null) {
                    s1.push(tree.right);
                }
            }

            while (!s2.isEmpty()) {
                System.err.println(s2.pop());
            }
        }
    }

    public static <T> void afterLoop2(Tree<T> tree) {
        if (tree != null) {
            Stack<Tree<T>> stack = new Stack<>();
            stack.push(tree);
            Tree<T> c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && c.left != tree && c.right != tree) {
                    stack.push(c.left);
                } else if (c.right != null && c.right != tree) {
                    stack.push(c.right);
                } else {
                    System.err.println(stack.pop().data);
                    tree = c;
                }
            }
        }
    }

    public static void main(String[] args) {
        Tree<Integer> r1 = new Tree<>();
        r1.data = 1;

        Tree<Integer> r2 = new Tree<>();
        r2.data = 2;

        Tree<Integer> r3 = new Tree<>();
        r3.data = 3;

        Tree<Integer> r4 = new Tree<>();
        r4.data = 5;

        r1.left = r2;
        r1.right = r3;
        r2.left = r4;

        afterLoop2(r1);
    }

}
