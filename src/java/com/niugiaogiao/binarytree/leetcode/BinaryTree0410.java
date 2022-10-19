package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 04.10. 检查子树
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 * <p>
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 * <p>
 * 注意：此题相对书上原题略有改动
 * <p>
 * 链接：https://leetcode.cn/problems/check-subtree-lcci
 *
 * @author zihao
 */
public class BinaryTree0410 {

    public static boolean checkSubTree1(TreeNode t1, TreeNode t2) {
        if (t2 == null) return true;
        if (t1 == null) return false;
        return run1(t1, t2) || checkSubTree1(t1.left, t2) || checkSubTree1(t1.right, t2);
    }

    public static boolean run1(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        if (t1.val == t2.val) {
            return run1(t1.left, t2.left) && run1(t1.right, t2.right);
        }
        return false;
    }

    static TreeNode tempT2 = null;
    static Boolean findTree = false;

    public static boolean checkSubTree(TreeNode t1, TreeNode t2) {
        tempT2 = t2;
        run(t1, t2);
        return findTree;
    }

    /**
     * 倒者搜索不行，出现相同元素，如果不出现相同元素该算法可解
     */
    public static boolean run(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 != null) return false;
        if (t1 == null && t2 == null) return true;

        if (t1.val == t2.val) {
            boolean res1 = run(t1.left, t2.left);
            boolean res2 = run(t1.right, t2.right);
            if (res1 && res2 && !findTree) {
                findTree = true;
            }
            return res1 && res2;
        } else {
            t2 = tempT2;
            boolean res1 = run(t1, t2);
            boolean res2 = run(t1, t2);
            return res1 || res2;
        }
    }

    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(44);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t5 = new TreeNode(5);
//        TreeNode t6 = new TreeNode(6);
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t8 = new TreeNode(8);
//        TreeNode t9 = new TreeNode(9);
//        t1.left = t2;
//        t1.right = t3;
//        t2.left = t4;
//        t2.right = t5;
//        t3.left = t6;
//        t3.right = t7;
//        t4.left = t8;
//        t4.right = t9;
//
//        TreeNode r1 = new TreeNode(4);
//        TreeNode r2 = new TreeNode(8);
//        TreeNode r3 = new TreeNode(9);
//        r1.left = r2;
//        r1.right = r3;

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        r1.left = r2;
        r1.right = r3;

        System.err.println(checkSubTree(t1, r1));
    }
}
