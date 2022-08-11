package com.niugiaogiao.tree.other;

/**
 * 给定一棵二叉树的头节点 head
 * 返回这棵二叉树中最大的二叉搜索子树的头节点
 * <p>
 * 搜索二叉树：整个树上没有重复值，左树的值都比我小，右树的值都比我大，每一棵子树都一样
 * 问题是：整棵树里面未必完全都是搜索二叉树，需要求出满足搜索二叉树的最大子树是谁？
 * 子树是整颗子树都要，如果有一部分满足，最后一个节点不满足是不可以的
 * 1.你要知道当前是否是搜索二叉树
 * 2.你要知道当前搜索二叉树的最大节点有多少
 * <p>
 * 分类：
 * 1.与 x 无关，即最大二叉搜索子树不以 x 为头，左树上满足最大二叉搜索树的大小 或者右树上满足最大二叉搜索树的大小
 * 2.与 x 有关，以 x 为头整体的大小
 * 左树是搜索二叉树，右树是搜索二叉树，左树最大值小于 x ，右树最小值 > x ，此条件才能够成立
 * <p>
 * 信息整理：
 * 左树：最大二叉搜索子树的整体大小，是否是二叉搜索子树，左树最大节点
 * 右数：最大二叉搜索子树的整体大小，是否是二叉搜索子树，右树最小节点
 * 此时左数的返回数据和右树的返回数据不是一样的，求全集，因为是递归函数，不想对左树要求和右树要求做区分
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-11 22:42
 */
public class BinaryTreeMaxSearchTreeHeadNode {
}