package com.niugiaogiao.tree;

/**
 * 二叉树打印函数
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-23 20:54
 */
public class BinaryTreePrint {

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

    private static void print(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        print(head.right, height + 1, "v", len);

        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.err.println(getSpace(height * len) + val);

        print(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }

        return buf.toString();
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode();
        TreeNode r2 = new TreeNode();
        TreeNode r3 = new TreeNode();
        TreeNode r4 = new TreeNode();
        r1.left = r2;
        r2.right = r3;
        r1.val = 1;
        r2.val = 2;
        r3.val = 3;
        r4.val = 5;
        r1.right = r4;

        print(r1, 0, "H", 17);
    }
}
