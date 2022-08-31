package com.niugiaogiao.binarytree.other;

/**
 * 一段纸条竖着放在桌子上，纸条下边向上方对折 1 次，压出折痕后展开，此时折痕是 凹 下去的
 * 如果从纸条的下边向上连续对折 2 次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕，下折痕和上折痕
 * 给定一个输入参数 N ，代表纸条都从下边向上方连续对折 N 次，请从上打印所有折痕的方向
 * 例如 N = 1
 * 输出：down
 *
 * N = 2
 * down down up
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-31 23:42
 */
public class BinaryTreeArticleOrigami {

    /**
     * 中序遍历
     * 为什么不用建立出所有整棵树，因为这颗树是有明确规则的
     * 左树都属 凹  右树都是 凸  头节点默认也是 凹的一棵树
     * 空间复杂度：O(N) 比建立出整颗树 O(2^n - 1) 要好
     */
    public static void run(int n, String up) {
        if (n == 0) {
            return;
        }

        run(n - 1, "凹");
        System.err.println(up);
        run(n - 1, "凸");
        n--;
    }

    public static void main(String[] args) {
        run(3, "凹");
    }
}
