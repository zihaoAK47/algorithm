package com.niugiaogiao.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-02 22:40
 */
public class PrefixTree {

    static class Node {
        public int press;
        public int end;
        public Node[] nodes;

        public Node() {
            this.nodes = new Node[26];
        }
    }

    static class Tree {
        Node root;

        public Tree() {
            this.root = new Node();
        }

        public void insertStr(String str) {
            if (null == str || str.length() == 0)
                return;
            char[] chars = str.toCharArray();
            int position = -1;
            Node temp = root;
            for (char item : chars) {
                position = item - 'a';
                if (temp.nodes[position] == null) {
                    temp.nodes[position] = new Node();
                }
                temp = temp.nodes[position];
                temp.press++;
            }
            temp.end++;
        }

        /**
         * 查找字符串之前被加入过几次
         *
         * @param str targetStr
         * @return 返回加入过的次数
         */
        public int search(String str) {
            if (null == str || str.length() == 0) {
                return 0;
            }

            Node tempNode = root;
            char[] chars = str.toCharArray();
            for (char item : chars) {
                int position = item - 'a';
                if (tempNode.nodes[position] == null) {
                    return 0;
                }

                tempNode = tempNode.nodes[position];
            }
            return tempNode.end;
        }

        /**
         * 所有加入的字符串中，有几个是以 pre 字符串作为前缀的
         *
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if (null == pre || pre.length() == 0) {
                return 0;
            }

            char[] chars = pre.toCharArray();
            Node tempNode = root;
            int position = -1;
            for (char item : chars) {
                position = item - 'a';
                if (tempNode.nodes[position] == null) {
                    return 0;
                }

                tempNode = tempNode.nodes[position];
            }

            return tempNode.press;
        }

        public void delete(String str) {
            if (search(str) == 0) {
                return;
            }

            // 首先查找是否加入过该字符串
            // 如果加入过如何删除？
            int position = -1;
            Node tempNode = root;
            char[] chars = str.toCharArray();
            for (char item : chars) {
                position = item - 'a';
                if (--tempNode.press == 0) {
                    tempNode.nodes[position] = null;
                    return;
                }
                tempNode = tempNode.nodes[position];
            }
            tempNode.end--;
        }
    }

    static class NodeNew {
        public int press = 0;
        public int end = 0;
        public Map<Integer, NodeNew> node = new HashMap<>();
    }

    static class TreeNew {

        NodeNew root;

        public TreeNew() {
            root = new NodeNew();
        }

        public void insertStr(String str) {
            if (null == str || str.length() == 0) {
                return;
            }
            char[] chars = str.toCharArray();
            NodeNew tempNode = root;
            for (char item : chars) {
                NodeNew nodeNew = tempNode.node.get((int) item);
                if (nodeNew == null) {
                    nodeNew = new NodeNew();
                    tempNode.node.put((int) item, nodeNew);
                    tempNode.press++;
                }
                tempNode = nodeNew;
            }

            tempNode.end++;
        }

        // 查找之前被加入过几次
        public int searchStr(String str) {
            if (null == str || str.length() == 0) {
                return 0;
            }

            char[] chars = str.toCharArray();
            NodeNew tempNode = root;
            for (char item : chars) {
                NodeNew nodeNew = tempNode.node.get((int) item);
                if (nodeNew == null) {
                    return 0;
                }
                tempNode = nodeNew;
            }
            return tempNode.end;
        }

        public void deleteStr(String str) {
            if (searchStr(str) == 0) {
                return;
            }

            char[] chars = str.toCharArray();
            NodeNew tempNode = root;
            for (char item : chars) {
                NodeNew nodeNew = tempNode.node.get((int) item);
                if (--nodeNew.press == 0) {
                    tempNode.node.remove((int) item);
                    return;
                }
                tempNode = nodeNew;
            }
            tempNode.end--;
        }
    }

    public static void main(String[] args) {
        TreeNew treeNew = new TreeNew();
        String str = "abc";
        treeNew.insertStr(str);
        System.err.println(treeNew.searchStr(str));
    }


    public static void main1(String[] args) {
        String str = "abc";
        Tree tree = new Tree();
        tree.insertStr(str);
        tree.insertStr(str);
        int search = tree.search("abc");
        System.err.println(search);
        System.err.println(tree.prefixNumber("ab"));
    }
}
