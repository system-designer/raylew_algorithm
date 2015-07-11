package com.raylew.algorithm.lanqiaocup;

/*
题目标题: 第39级台阶
    小明刚刚看完电影《第39级台阶》，离开电影院的时候，他数了数礼堂前的台阶数，恰好是39级!
    站在台阶前，他突然又想着一个问题：
    如果我每一步只能迈上1个或2个台阶。先迈左脚，然后左右交替，最后一步是迈右脚，也就是说一共要走偶数步。那么，上完39级台阶，有多少种不同的上法呢？
    请你利用计算机的优势，帮助小明寻找答案。
 */

/*
算法要点：从39级开始，dfs到0，同时回溯和剪枝
 */
public class LanQiao13_8 {
    static int count = 0;
    static int sum = 0;

    public static void main(String[] args) {
        dfs(39);
        System.out.println(sum);
    }

    public static void dfs(int left) {
        if (left == 0) {
            if (count % 2 == 0)
                sum++;
            return;
        }
        if (left == -1) {
            return;
        }
        count++;
        dfs(left - 1);
        dfs(left - 2);
        count--;
    }
}
