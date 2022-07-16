package com.niugiaogiao.other;

/**
 * @author zi hao
 * @version 1.0
 * @date 2020/11/1 21:28
 */

/**
 * 给出两个非空的链表用来表示两个非负的整数
 * 其中,它们各自的位数是按照 逆序 的方式存储的,并且它们的每个节点只能存储 一位 数字
 * 如果,我们将这两个数相加起来,则会返回一个新的链表来表示它们的和
 * 您可以假设除了数字 0 之外,这两个数都不会以 0 开头
 *
 * 示例
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

public class TwoNumberAdd {

    /**
     * 1.链表 - 非空 - 非负的整数
     * 2.位数逆序存储
     * 3.每个节点只能存储 一位 数字
     * 4.这两个数都不会以 0 开头
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /* 数据验证 */
    public static boolean varNumber(ListNode addOne,ListNode addTwo) {
        /* 是否为空 */
        if (null == addOne || null == addTwo) {
            return false;
        }


        return true;
    }
    /* 增加链表 */
    public static ListNode addList(char[] resultBuf) {
        if (null == resultBuf) {
            return null;
        }

        int len = resultBuf.length;
        ListNode head = new ListNode(resultBuf[len - 1] - '0',null);
        for (int i = len - 2 ; i >= 0 ; i --) {
            ListNode tempNode = new ListNode(resultBuf[i] - '0',head);
            head = tempNode;
        }

        return head;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode tempHead = result;
        int flag = 0;
        while (null != l1 || null != l2 || flag != 0) {
            int tempNumberOne = (null != l1) ? l1.val : 0;
            int tempNumberTwo = (null != l2) ? l2.val : 0;
            int addResult = tempNumberOne + tempNumberTwo + flag;

            flag = (addResult >= 10) ? addResult / 10 : 0;
            ListNode tempNode = new ListNode((flag == 0) ? addResult : addResult % 10);
            tempHead.next = tempNode;
            tempHead = tempNode;

            l1 = (null != l1 && null != l1.next) ? l1.next : null;
            l2 = (null != l2 && null != l2.next) ? l2.next : null;
        }

        return result.next;
    }

    /* 测试链表插入 */
    public static ListNode testInsert(int numbers) {
        /* 创建头节点 */
        ListNode head = null;
        while (0 != numbers) {
            int mod = numbers % 10;
            numbers /= 10;
            if (null == head) {
                head = new ListNode(mod,null);
            } else {
                ListNode tempHead = head;
                while (null != tempHead.next) {
                    tempHead = tempHead.next;
                }
                tempHead.next = new ListNode(mod,null);
            }
        }

        return head;
    }

    /* 链表显示 */
    public static void showLists(ListNode tempHead) {
        while (null != tempHead) {
            System.out.print(tempHead.val + "  ");
            tempHead = tempHead.next;
        }
        System.out.println("");
    }

    //首先把两个链表相加的值求出
    //也就是说需要挨个获取两个链表的值
    //两个都为空的时候条件结束
    //如果某一个提前结束那么这个就是值就是 0
    //需要考虑进位的情况
    public static void main(String[] args) {
        ListNode numOne = testInsert(100);
        ListNode numTwo = testInsert(100);
        showLists(addTwoNumbers(numOne,numTwo));
    }
}
