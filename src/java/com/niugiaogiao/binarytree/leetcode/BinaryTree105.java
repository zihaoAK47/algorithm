package com.niugiaogiao.binarytree.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-28 19:57
 */
public class BinaryTree105 {


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

    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder,
                                int preorder_left, int preorder_right,
                                int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_left]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_left]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder,
                preorder_left + 1, preorder_left + size_left_subtree,
                inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder,
                preorder_left + size_left_subtree + 1, preorder_right,
                inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    public static TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }

        int inorderIndex = 0;
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = deque.peek();
            int val = preorder[i];
            if (inorder[inorderIndex] != node.val) {
                node.left = new TreeNode(val);
                deque.push(node.left);
            } else {
                while (!deque.isEmpty() && deque.peek().val == inorder[inorderIndex]) {
                    inorderIndex++;
                    node = deque.poll();
                }
                node.right = new TreeNode(val);
                deque.push(node.right);
            }
        }

        return root;
    }

    Map<Integer, Integer> dict = new HashMap<>();
    int[] tPreorder;
    int[] tInorder;
    int index = 0;

    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        tPreorder = preorder;
        tInorder = inorder;
        index = 0;
        int len = preorder.length;
        dict = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            dict.put(inorder[i], i);
        }

        return run(0, len - 1);
    }

    public TreeNode run(int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode node = new TreeNode(tPreorder[index]);
        index++;
        int mid = dict.get(node.val);
        node.left = run(start, mid - 1);
        node.right = run(mid + 1, end);
        return node;
    }

    public static void main(String[] args) {
        buildTree2(new int[]{3, 9, 8, 5, 4, 10, 20, 15, 7}, new int[]{4, 5, 8, 10, 9, 3, 15, 20, 7});
    }
}
