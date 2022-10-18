package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 04.12. 求和路径
 * <p>
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量
 * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/paths-with-sum-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zihao
 */
public class BinaryTree0412 {

    static int count = 0;

    public static int pathSum(TreeNode root, int sum) {
        if (root != null) {
            dfs(root, sum, 0);
            pathSum(root.left, sum);
            pathSum(root.right, sum);
            return count;
        }
        return count;
    }

    public static void dfs(TreeNode node, int sum, int t) {
        if (node == null) return;
        if (t + node.val == sum) {
            count++;
        }
        dfs(node.left, sum, t + node.val);
        dfs(node.right, sum, t + node.val);
    }


    static Map<Integer, Integer> dict = new HashMap<>();

    public static int pathSumPrefix(TreeNode root, int sum) {
        dict.put(0, 1);
        return 0;
    }

    public static int prefixDfs(TreeNode node, int cur, int targetSum) {
        if (node == null) return 0;

        int count = 0;
        cur += node.val;
        count = dict.getOrDefault(cur - targetSum, 0);
        dict.put(cur, count + 1);
        cur += prefixDfs(node.left, cur, targetSum);
        cur += prefixDfs(node.right, cur, targetSum);
        dict.put(cur, count - 1);
        return cur;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        pathSum(null, 0);
        System.err.println(count);
    }
}
