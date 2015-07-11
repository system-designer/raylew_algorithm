package com.raylew.algorithm.lanqiaocup;

/*
题目标题：翻硬币
    小明正在玩一个“翻硬币”的游戏。
    桌上放着排成一排的若干硬币。我们用 * 表示正面，用 o 表示反面（是小写字母，不是零）。
    比如，可能情形是：**oo***oooo
    如果同时翻转左边的两个硬币，则变为：oooo***oooo
    现在小明的问题是：如果已知了初始状态和要达到的目标状态，每次只能同时翻转相邻的两个硬币,那么对特定的局面，最少要翻动多少次呢？
    我们约定：把翻动相邻的两个硬币叫做一步操作，那么要求：
程序输入：
两行等长的字符串，分别表示初始状态和要达到的目标状态。每行的长度<1000
程序输出：
一个整数，表示最小操作步数
例如：
用户输入：
**********
o****o****
程序应该输出：
5
再例如：
用户输入：
*o**o***o***
*o***o**o***
程序应该输出：
1
 */
public class LanQiao13_9 {
    public static void main(String[] args) {
        char[] ini = "*o**o***o***".toCharArray();
        char[] des = "*o***o**o***".toCharArray();
        int count = 0;
        for (int i = 0; i < ini.length - 1; i++) {
            if (ini[i] != des[i]) {
                ini[i] = (ini[i] == '*') ? 'o' : '*';
                ini[i + 1] = (ini[(i + 1) % ini.length] == '*') ? 'o' : '*';
                count++;
            }
        }
        System.out.println(count);
    }
}
