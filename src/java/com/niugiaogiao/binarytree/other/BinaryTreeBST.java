package com.niugiaogiao.binarytree.other;

/**
 * 二叉排序树的插入，删除，查找
 *
 * @author zihao
 */
public class BinaryTreeBST {

    static class BSTTree {
        int val;
        BSTTree left;
        BSTTree right;

        public BSTTree(int val) {
            this.val = val;
        }
    }

    static class BSTOperator {

        BSTTree root;

        private void addUseLoop(int val) {
            BSTTree tempRoot = root;
            BSTTree lastRoot = null;
            while (tempRoot != null) {
                lastRoot = tempRoot;
                if (tempRoot.val < val) {
                    tempRoot = tempRoot.right;
                } else {
                    tempRoot = tempRoot.left;
                }
            }

            if (lastRoot.left == null) {
                lastRoot.left = new BSTTree(val);
            } else {
                lastRoot.right = new BSTTree(val);
            }
        }

        private BSTTree addRecursive1(BSTTree node, int val) {
            if (node == null) {
                return new BSTTree(val);
            }
            if (node.val < val) {
                node.right = addRecursive1(node.right, val);
            } else {
                node.left = addRecursive1(node.left, val);
            }

            return node;
        }

        private void addRecursive2(BSTTree node, int val) {
            if (node.val < val) {
                if (node.right == null) {
                    node.right = new BSTTree(val);
                } else {
                    addRecursive2(node.right, val);
                }
            } else {
                if (node.left == null) {
                    node.left = new BSTTree(val);
                } else {
                    addRecursive2(node.left, val);
                }
            }
        }

        public boolean add(int val) {
            if (root == null) {
                root = new BSTTree(val);
                return true;
            }
            addUseLoop(val);
//            addRecursive1(root, val);
//            addRecursive2(root, val);
            return true;
        }

        public boolean delete(int val) {
            BSTTree targetNode = search(val);
            BSTTree parentNode = searchParentNode(val);
            // 是否最后一个父节点
            if (root == targetNode && targetNode.left == null && targetNode.right == null) {
                this.root = null;
                return true;
            }

            // 查看目标节点位于父节点的左边还是右边
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left == targetNode) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
                return true;
            }
            // 目标节点拥有一个子树
            if (targetNode.left == null || targetNode.right == null) {
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        if (parentNode.left == targetNode) {
                            parentNode.left = targetNode.left;
                        } else {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        this.root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        if (parentNode.left == targetNode) {
                            parentNode.left = targetNode.right;
                        } else {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        this.root = targetNode.right;
                    }
                }
                return true;
            }
            // 寻找删除节点最右的一个节点
            BSTTree lastNode = targetNode.right;
            while (lastNode.left != null && lastNode.left.val != targetNode.val) {
                lastNode = lastNode.left;
            }
            int delVal = lastNode.val;
            delete(lastNode.val);
            targetNode.val = delVal;

            return true;
        }

        private BSTTree searchParentNode(int val) {
            BSTTree tempRoot = root;
            BSTTree parent = null;
            while (tempRoot != null && tempRoot.val != val) {
                if ((tempRoot.left != null && tempRoot.left.val == val) || (tempRoot.right != null && tempRoot.right.val == val)) {
                    parent = tempRoot;
                    break;
                }
                tempRoot = (tempRoot.val < val) ? tempRoot.right : tempRoot.left;
            }

            return parent;
        }

        private BSTTree searchUseLoop(int val) {
            BSTTree tempNode = root;
            while (tempNode != null) {
                if (tempNode.val == val) {
                    return tempNode;
                }
                tempNode = (tempNode.val < val) ? tempNode.right : tempNode.left;
            }
            return null;
        }

        private BSTTree searchRecursive(BSTTree node, int val) {
            if (node == null) return null;

            if (node.val == val) {
                return node;
            } else if (node.val < val) {
                return searchRecursive(node.right, val);
            } else {
                return searchRecursive(node.left, val);
            }
        }

        public BSTTree search(int val) {
//            return searchUseLoop(val);
            return searchRecursive(root, val);
        }

        public boolean update(int oldVal, int newVal) {
            BSTTree search = search(oldVal);
            if (search != null) {
                search.val = newVal;
                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        BSTOperator bstOperator = new BSTOperator();
        bstOperator.add(3);
        bstOperator.add(1);
        bstOperator.add(4);
        bstOperator.add(2);
        System.err.println("");
    }
}