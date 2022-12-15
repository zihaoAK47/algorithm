package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题
 *
 * @author zihao
 */
public class BinaryTree297 {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(",");
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                sb.append(poll.left.val).append(",");
                queue.add(poll.left);
            } else {
                sb.append("#").append(",");
            }
            if (poll.right != null) {
                sb.append(poll.right.val).append(",");
                queue.add(poll.right);
            } else {
                sb.append("#").append(",");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] split = data.split(",");
        int pos = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            poll.left = split[pos].equals("#") ? null : new TreeNode(Integer.parseInt(split[pos]));
            pos++;
            poll.right = split[pos].equals("#") ? null : new TreeNode(Integer.parseInt(split[pos]));
            pos++;

            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }

        return root;
    }
}
