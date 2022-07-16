package com.niugiaogiao.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树宽度优先
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-11 9:29
 */
public class BinaryTree {

    /**
     * 二叉树按层进行宽度优先遍历
     * 按层遍历头节点入队，接着循环判断，出队就打印
     * 若左节点不为空左节点入队，右节点不为空右节点入队
     *
     * @param tTree 树节点
     * @param <T>   具体类型
     */
    public static <T> void leaverTraverse(Tree<T> tTree) {
        if (tTree == null) {
            return;
        }
        Queue<Tree<T>> queue = new LinkedList<>();
        queue.add(tTree);
        while (!queue.isEmpty()) {
            Tree<T> treeItem = queue.poll();
            System.err.println(treeItem.data);
            if (treeItem.left != null) {
                queue.add(treeItem.left);
            }
            if (treeItem.right != null) {
                queue.add(treeItem.right);
            }
        }
    }

    /**
     * 二叉树求最大层级节点树
     *
     * @param tree 二叉树节点
     * @param <T>  具体类型
     */
    private static <T> int maxLeaver(Tree<T> tree) {
        if (tree == null) {
            return -1;
        }

        Queue<Tree<T>> queue = new LinkedList<>();
        queue.add(tree);

        Map<Tree<T>, Integer> leaverMap = new HashMap<>();
        leaverMap.put(tree, 1);

        // 最大宽度节点数量
        int max = 0;
        // 记录当前层节点数量，根据约定一个节点出来的时候再把他加入到节点里面去
        int curLevelNodes = 0;
        // 当前所在二叉树第几层
        int curLevel = 1;
        while (!queue.isEmpty()) {
            Tree<T> itemPeek = queue.poll();
            int currentLever = leaverMap.get(itemPeek);
            if (itemPeek.left != null) {
                queue.add(itemPeek.left);
                leaverMap.put(itemPeek.left, currentLever + 1);
            }
            if (itemPeek.right != null) {
                queue.add(itemPeek.right);
                leaverMap.put(itemPeek.right, currentLever + 1);
            }

            // 表示当前层没有结束还在统计，此时节点数量更新
            if (curLevel == currentLever) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        // 触发更新 max ，因为最后层里面没有新的层去结算最后层了
        max = Math.max(max, curLevelNodes);
        return max;
    }

    private static <T> int maxTemp(Tree<T> tree) {
        if (tree == null) {
            return -1;
        }
        Queue<Tree<T>> queue = new LinkedList<>();
        queue.add(tree);
        HashMap<Tree<T>, Integer> leaveMap = new HashMap<>();
        leaveMap.put(tree, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Tree<T> cur = queue.poll();
            int curNodeLevel = leaveMap.get(cur);
            if (cur.left != null) {
                leaveMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                leaveMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    private static <T> int maxLeaver1(Tree<T> tree) {
        if (tree == null) {
            return -1;
        }

        Queue<Tree<T>> queue = new LinkedList<>();
        queue.add(tree);
        Tree<T> curEnd = tree;
        Tree<T> nextEnd = null;
        int maxNode = 0;
        int currentLeaverNodeCount = 0;
        while (!queue.isEmpty()) {
            Tree<T> itemNode = queue.poll();
            if (itemNode.left != null) {
                queue.add(itemNode.left);
                nextEnd = itemNode.left;
            }
            if (itemNode.right != null) {
                queue.add(itemNode.right);
                nextEnd = itemNode.right;
            }

            currentLeaverNodeCount++;

            if (itemNode == curEnd) {
                maxNode = Math.max(maxNode, currentLeaverNodeCount);
                currentLeaverNodeCount = 0;
                curEnd = nextEnd;
            }
        }

        return maxNode;
    }

    /**
     * 前序遍历序列化
     *
     * @param tree
     * @param <T>
     */
    private static <T> void serializationRun(Tree<T> tree) {
        Queue<String> save = new LinkedList<>();
        serialization(tree, save);
        System.err.println(save);
    }

    /**
     * 前序遍历序列化
     *
     * @param node
     * @param queue
     * @param <T>
     */
    private static <T> void serialization(Tree<T> node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(node.data));
            serialization(node.left, queue);
            serialization(node.right, queue);
        }
    }

    /**
     * 前序遍历反序列化
     *
     * @param queue
     * @param <T>
     */
    private static <T> Tree<T> deSerializationRun(Queue<String> queue) {
        String poll = queue.poll();
        if (poll == null) {
            return null;
        }
        Tree<T> node = new Tree<>();
        node.data = (T) poll;
        node.left = deSerializationRun(queue);
        node.right = deSerializationRun(queue);
        return node;
    }

    /**
     * 序列化按层
     *
     * @param tree
     * @param <T>
     */
    private static <T> Queue<String> serializationByLeaver(Tree<T> tree) {
        Queue<Tree<T>> queue = new LinkedList<>();
        Queue<String> result = new LinkedList<>();
        if (tree == null) {
            result.add(null);
        } else {
            queue.add(tree);
            result.add(String.valueOf(tree.data));
            while (!queue.isEmpty()) {
                Tree<T> itemNode = queue.poll();
                if (itemNode.left != null) {
                    result.add(String.valueOf(itemNode.left.data));
                    queue.add(itemNode.left);
                } else {
                    result.add(null);
                }

                if (itemNode.right != null) {
                    result.add(String.valueOf(itemNode.right.data));
                    queue.add(itemNode.right);
                } else {
                    result.add(null);
                }
            }
        }
        System.err.println(result);
        return result;
    }

    /**
     * 反序列化按层
     *
     * @param queue
     * @param <T>
     * @return
     */
    private static <T> Tree<T> deSerializationByLeaver(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Tree<T> head = createNode(queue.poll());
        Queue<Tree<T>> tempQueue = new LinkedList<>();
        if (head != null) {
            tempQueue.add(head);
        }

        while (!tempQueue.isEmpty()) {
            Tree<T> itemNode = tempQueue.poll();
            itemNode.left = createNode(queue.poll());
            itemNode.right = createNode(queue.poll());
            if (itemNode.left != null) {
                tempQueue.add(itemNode.left);
            }
            if (itemNode.right != null) {
                tempQueue.add(itemNode.right);
            }
        }

        return head;
    }

    private static <T> Tree<T> createNode(String val) {
        if (val == null) {
            return null;
        }

        return new Tree<T>((T) val);
    }

    public static void main(String[] args) {
//        Tree<Integer> tree1 = new Tree<>(1);
//        Tree<Integer> tree2 = new Tree<>(2);
//        Tree<Integer> tree3 = new Tree<>(3);
//        Tree<Integer> tree4 = new Tree<>(4);
//        Tree<Integer> tree5 = new Tree<>(5);
//        Tree<Integer> tree6 = new Tree<>(6);
//        Tree<Integer> tree7 = new Tree<>(7);
//        Tree<Integer> tree8 = new Tree<>(8);
//        tree1.left = tree2;
//        tree1.right = tree3;
//        tree2.left = tree4;
//        tree2.right = tree5;
//        tree4.left = tree8;
//        tree3.left = tree6;
//        tree3.right = tree7;
//        System.err.println(maxLeaver(tree1));
//        System.err.println(maxLeaver1(tree1));
//        System.err.println(maxTemp(tree1));
//        serializationRun(tree1);

        Tree<Integer> tree1 = new Tree<>(1);
        Tree<Integer> tree2 = new Tree<>(2);
        tree1.left = tree2;
        Queue<String> strings = serializationByLeaver(tree1);
        System.err.println(deSerializationByLeaver(strings));
    }

    static class Tree<T> {
        private T data;
        private Tree<T> left;
        private Tree<T> right;

        public Tree(T data, Tree<T> left, Tree<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Tree() {
        }

        public Tree(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Tree<T> getLeft() {
            return left;
        }

        public void setLeft(Tree<T> left) {
            this.left = left;
        }

        public Tree<T> getRight() {
            return right;
        }

        public void setRight(Tree<T> right) {
            this.right = right;
        }
    }
}
