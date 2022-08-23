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
            position = Math.max(position - steps, 0);
            position = position == 0 ? 1 : position;
            return history.get(position - 1);
        }

        public String forward(int steps) {
            position = Math.min(position + steps, sizeCount);
            return history.get(position - 1);
        }

        public boolean isEmpty(String page) {
            return page == null || page.length() == 0;
        }
    }

    static class BrowserHistory1 {
        int position = 0;
        int sizeCount = 0;
        HistoryData head = new HistoryData(null);
        HistoryData pre = head;
        HistoryData cur = pre;

        static class HistoryData {
            public String url;
            public HistoryData next;
            public HistoryData(String url) {
                this.url = url;
            }
        }

        public BrowserHistory1(String homepage) {
            if (isEmpty((homepage))) {
                return;
            }

            position++;
            sizeCount++;
            pre.next = new HistoryData(homepage);
            pre = pre.next;
            cur = pre;
        }

        public void visit(String url) {
            if (isEmpty((url))) {
                return;
            }
            cur.next = new HistoryData(url);
            cur = cur.next;
            pre = cur;
            position++;
            sizeCount = position;
        }

        public String back(int steps) {
            position = Math.max(position - steps, 0);
            position = position == 0 ? 1 : position;
            if (position - 1 == 0) {
                cur = head.next;
                return head.next.url;
            }
            int pos = 0;
            HistoryData tempCur = head.next;
            while (pos != position - 1) {
                pos++;
                tempCur = tempCur.next;
            }
            cur = tempCur;
            return cur.url;
        }

        public String forward(int steps) {
            int backPosition = position - 1;
            position = Math.min(position + steps, sizeCount);
            if (position == sizeCount) {
                cur = pre;
                return pre.url;
            }
            int pos = backPosition;
            HistoryData tempCur = cur;
            while (pos != position - 1) {
                pos++;
                tempCur = tempCur.next;
            }
            cur = tempCur;
            return cur.url;
        }

        public boolean isEmpty(String page) {
            return page == null || page.length() == 0;
        }
    }

    static class BrowserHistory3 {
        int position = 0;
        int sizeCount = 0;
        String[] historyData = new String[101];

        public BrowserHistory3(String homepage) {
            if (isEmpty((homepage))) {
                return;
            }

            historyData[position] = homepage;
            position++;
            sizeCount++;
        }

        public void visit(String url) {
            if (isEmpty((url))) {
                return;
            }
            historyData[position] = url;
            position++;
            sizeCount = position;
        }

        public String back(int steps) {
            position = Math.max(position - steps, 0);
            position = position == 0 ? 1 : position;
            return historyData[position - 1];
        }

        public String forward(int steps) {
            position = Math.min(position + steps, sizeCount);
            return historyData[position - 1];
        }

        public boolean isEmpty(String page) {
            return page == null || page.length() == 0;
        }
    }

    static class BrowserHistory4 {
        Deque<String> s1 = new LinkedList<>();
        Deque<String> s2 = new LinkedList<>();

        public BrowserHistory4(String homepage) {
            if (isEmpty((homepage))) {
                return;
            }
            s1.push(homepage);
        }

        public void visit(String url) {
            if (isEmpty((url))) {
                return;
            }
            s1.push(url);
            s2.clear();
        }

        public String back(int steps) {
            for (int i = 0 ; i < steps && s1.size() > 1 ; i ++) {
                s2.push(s1.pop());
            }

            return s1.peek();
        }

        public String forward(int steps) {
            for (int i = 0 ; i < steps && !s2.isEmpty() ; i++) {
                s1.push(s2.pop());
            }

            return s1.peek();
        }

        public boolean isEmpty(String page) {
            return page == null || page.length() == 0;
        }
    }

    public static void main(String[] args) {
        BrowserHistory3 browserHistory = new BrowserHistory3("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.err.println(browserHistory.back(1));
        System.err.println(browserHistory.back(1));
        System.err.println(browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        System.err.println(browserHistory.forward(2));
        System.err.println(browserHistory.back(2));
        System.err.println(browserHistory.back(7));
    }

    public static void main2(String[] args) {

//        ["forward","visit","visit","forward","visit","back","visit","visit","forward"]
//        [[7],["crqje.com"],["iybch.com"],[5],["uun.com"],[10],["hci.com"],["whula.com"],[10]]
        BrowserHistory browserHistory = new BrowserHistory("esgriv.com");
        browserHistory.visit("cgrt.com");
        browserHistory.visit("tip.com");
        System.err.println(browserHistory.back(9));
        browserHistory.visit("kttzxgh.com");
        System.err.println(browserHistory.forward(7));
        browserHistory.visit("crqje.com");
        browserHistory.visit("iybch.com");
        System.err.println(browserHistory.forward(5));
        browserHistory.visit("uun.com");
        System.err.println(browserHistory.back(10));
        browserHistory.visit("hci.com");
        browserHistory.visit("whula.com");
        System.err.println(browserHistory.forward(10));
    }
}
