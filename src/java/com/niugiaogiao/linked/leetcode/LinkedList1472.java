package com.niugiaogiao.linked.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 你有一个只支持单个标签页的 浏览器，最开始你浏览的网页是homepage，你可以访问其他的网站url，也可以在浏览历史中后退steps步或前进steps步。
 * <p>
 * 请你实现BrowserHistory 类：
 * <p>
 * BrowserHistory(string homepage)，用homepage初始化浏览器类。
 * void visit(string url)从当前页跳转访问 url 对应的页面。执行此操作会把浏览历史前进的记录全部删除。
 * string back(int steps)在浏览历史中后退steps步。如果你只能在浏览历史中后退至多x 步且steps > x，那么你只后退x步。请返回后退 至多 steps步以后的url。
 * string forward(int steps)在浏览历史中前进steps步。如果你只能在浏览历史中前进至多x步且steps > x，那么你只前进 x步。请返回前进至多steps步以后的 url
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-browser-history
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedList1472 {

    static class BrowserHistory {
        int position = 0;
        int sizeCount = 0;
        LinkedList<String> history = new LinkedList<>();

        public BrowserHistory(String homepage) {
            if (isEmpty((homepage))) {
                return;
            }

            position++;
            sizeCount++;
            history.addLast(homepage);
        }

        public void visit(String url) {
            if (isEmpty((url))) {
                return;
            }
            while (position != sizeCount) {
                sizeCount--;
                history.removeLast();
            }
            position++;
            sizeCount++;
            history.addLast(url);
        }

        public String back(int steps) {
            position = Math.max(position - steps - 1, 0);
            return history.get(position);
        }

        public String forward(int steps) {
            position = Math.max(position + steps - 1, sizeCount - 1);
            return history.get(position);
        }

        public boolean isEmpty(String page) {
            return page == null || page.length() == 0;
        }
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.err.println(browserHistory.back(1));
        System.err.println(browserHistory.back(1));
        System.err.println("");
    }
}
