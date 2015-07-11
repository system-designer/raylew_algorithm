package com.raylew.algorithm.lanqiaocup;

/*
微生物增值
假设有两种微生物 X 和 Y，X出生后每隔3分钟分裂一次（数目加倍），
Y出生后每隔2分钟分裂一次（数目加倍）。
一个新出生的X，半分钟之后吃掉1个Y，并且，从此开始，每隔1分钟吃1个Y。
现在已知有新出生的 X=10, Y=89，求60分钟后Y的数目。如果X=10，Y=90呢？
本题的要求就是写出这两种初始条件下，60分钟后Y的数目。题目的结果令你震惊吗？
这不是简单的数字游戏！真实的生物圈有着同样脆弱的性质！
也许因为你消灭的那只 Y 就是最终导致 Y 种群灭绝的最后一根稻草！
 */

/*
算法要点：找出循环节
 */
public class LanQiao12_1 {
    public static void main(String[] args) {
        int x = 10, y = 89;
        for (int i = 0; i < 10; i++) {
            y = y - x;// 0.5
            y = y - x;// 1.5
            y = y * 2;// 2
            y = y - x;// 2.5
            x = 2 * x;// 3
            y = y - x;// 3.5
            y = y * 2;// 4
            y = y - x;// 4.5
            y = y - x;// 5.5
            y = y * 2;// 6
            x = 2 * x;// 6
            System.out.println(y);
        }
    }
}
