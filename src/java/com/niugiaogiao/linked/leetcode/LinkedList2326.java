package com.niugiaogiao.linked.leetcode;

/**
 * 给你两个整数：m 和 n ，表示矩阵的维数。
 * 另给你一个整数链表的头节点 head 。
 * 请你生成一个大小为 m x n 的螺旋矩阵，矩阵包含链表中的所有整数。链表中的整数从矩阵 左上角 开始、顺时针 按 螺旋 顺序填充。如果还存在剩余的空格，则用 -1 填充。
 * 返回生成的矩阵
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LinkedList2326 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        int x = 0;
        int y = 0;
        int limitStartX = 0;
        int limitStartY = 0;
        int limitEndX = m;
        int limitEndY = n;
        // 1 左 2 下 3 右 4 上
        int direction = 1;
        int[][] result = new int[m][n];
        for (int loop = 0; loop < m * n; loop++) {
            if (head != null) {
                result[x][y] = head.val;
                head = head.next;
            } else {
                result[x][y] = -1;
            }
            switch (direction) {
                case 1:
                    if (y + 1 < limitEndY) {
                        y++;
                    } else {
                        x++;
                        // 表示当前行填充完毕
                        limitStartX++;
                        direction = 2;
                    }
                    break;
                case 2:
                    if (x + 1 < limitEndX) {
                        x++;
                    } else {
                        y--;
                        // 表示当前列填充完毕
                        limitEndY--;
                        direction = 3;
                    }
                    break;
                case 3:
                    if (y - 1 >= limitStartY) {
                        y--;
                    } else {
                        x--;
                        // 当前末尾行填充完毕
                        limitEndX--;
                        direction = 4;
                    }
                    break;
                case 4:
                    if (x - 1 >= limitStartX) {
                        x--;
                    } else {
                        y++;
                        // 当前末尾行填充完毕
                        limitStartY++;
                        direction = 1;
                    }
                    break;
            }
        }

        return result;
    }

    static int[][] res;
    static int x = 0;
    static int y = 0;
    static int limitStartX = 0;
    static int limitStartY = 0;
    static int limitEndX = 0;
    static int limitEndY = 0;
    static char direction = 'r';
    static ListNode tHead = null;

    public static int[][] spiralMatrix1(int m, int n, ListNode head) {
        init(m, n, head);
        for (int loop = 0; loop < m * n; loop++) {
            setVal();
            nowRight();
        }

        return res;
    }

    private static void init(int m, int n, ListNode head) {
        tHead = head;
        limitEndX = m;
        limitEndY = n;
        res = new int[m][n];
    }

    private static void setVal() {
        if (tHead != null) {
            res[x][y] = tHead.val;
            tHead = tHead.next;
        } else {
            res[x][y] = -1;
        }
    }

    private static void nowRight() {
        if (direction != 'r') {
            nowDown();
            return;
        }
        if (y + 1 < limitEndY) {
            y++;
        } else {
            x++;
            // 表示当前行填充完毕
            limitStartX++;
            direction = 'd';
        }
    }

    private static void nowDown() {
        if (direction != 'd') {
            nowLeft();
            return;
        }
        if (x + 1 < limitEndX) {
            x++;
        } else {
            y--;
            // 表示当前列填充完毕
            limitEndY--;
            direction = 'l';
        }
    }

    private static void nowLeft() {
        if (direction != 'l') {
            nowUp();
            return;
        }
        if (y - 1 >= limitStartY) {
            y--;
        } else {
            x--;
            // 当前末尾行填充完毕
            limitEndX--;
            direction = 'u';
        }
    }

    private static void nowUp() {
        if (direction != 'u') {
            return;
        }
        if (x - 1 >= limitStartX) {
            x--;
        } else {
            y++;
            // 当前末尾行填充完毕
            limitStartY++;
            direction = 'r';
        }
    }

    public static ListNode createNode(int[] data) {
        ListNode newHead = new ListNode(0);
        ListNode pre = newHead;
        for (int item : data) {
            pre.next = new ListNode(item);
            pre = pre.next;
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node = createNode(new int[]{3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0});
//        ListNode node = createNode(new int[]{0, 1, 2});
        spiralMatrix1(3, 5, node);
    }
}
