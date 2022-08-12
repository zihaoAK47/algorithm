package com.niugiaogiao.tree.other;

import java.util.List;

/**
 * 派对的最大快乐值
 * 这个公司要办 party，可以决定哪些员工来，哪些员工不来
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.目标是让派对的整体快乐值最大
 * 给定一棵多叉树头节点 boss，返回派对的最大快乐值
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-12 23:19
 */
public class BinaryTreeMaxHappy {

    static class Employees {
        public int happy;
        public List<Employees> next;
    }

    static class Info {
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }

    }

    public static Info run(Employees head) {
        if (head.next.isEmpty()) {
            return new Info(head.happy, 0);
        }
        int yes = head.happy;
        int no = 0;
        for (Employees item : head.next) {
            Info res = run(item);
            yes += res.no;
            no += Math.max(res.yes, res.no);
        }

        return new Info(yes, no);
    }

}
