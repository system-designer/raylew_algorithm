package com.raylew.algorithm.lanqiaocup;

/*
题目：1 2 3 4 5 6 7 8 9 =110 在左边数字之间加入运算符+、-
    匪警请拨110,即使手机欠费也可拨通！
    为了保障社会秩序，保护人民群众生命财产安全，警察叔叔需要与罪犯斗智斗勇，因而需要经常性地进行体力训练和智力训练！
    某批警察叔叔正在进行智力训练：
    1 2 3 4 5 6 7 8 9 = 110;
    请看上边的算式，为了使等式成立，需要在数字间填入加号或者减号（可以不填，但不能填入其它符号）。
    之间没有填入符号的数字组合成一个数，例如：12+34+56+7-8+9 就是一种合格的填法；123+4+5+67-89 是另一个可能的答案。
    请你利用计算机的优势，帮助警察叔叔快速找到所有答案。
    每个答案占一行。形如：
12+34+56+7-8+9
123+4+5+67-89
......
 */

/*
算法要点：等价于在有限个位置填入1~9和+、-符号，采用dfs算法，枚举的时候根据题意剪枝（如算数符号不能相连），统计满足条件的路径。
 */
public class LanQiao13_1 {
    /**
     * 记录dfs中的一条路径，即算式
     */
    public static String[] res = new String[18];

    public static void main(String[] args) {
        for (int i = 0; i < 18; i++) {
            String temp = new String("");
            res[i] = temp;
        }
        res[0] = "1";
        int cur = 1;
        dfs(cur);
    }

    /**
     * 深度优先搜索
     *
     * @param cur
     */
    public static void dfs(int cur) {
        if (res[cur - 1].equals("9")) {
            if (getRes(res, cur) == 110) {
                for (int k = 0; k < cur; k++) {
                    System.out.print(res[k]);
                }
                System.out.println();
            }
            return;
        }
        if (res[cur - 1].equals("+") || res[cur - 1].equals("-")) {
            int start = Integer.parseInt(res[cur - 2]);
            res[cur] = start + 1 + "";
            dfs(cur + 1);
        } else {
            int start = Integer.parseInt(res[cur - 1]);
            res[cur] = start + 1 + "";
            dfs(cur + 1);
            res[cur] = "+";
            dfs(cur + 1);
            res[cur] = "-";
            dfs(cur + 1);
        }
    }

    /**
     * 从字符串算式得到结果
     *
     * @param res
     * @param cur
     * @return
     */
    public static int getRes(String[] res, int cur) {
        String temp = "";
        int value = 0;
        for (int i = 0; i < cur; i++) {
            temp += res[i];
        }
        String[] num = temp.split("\\+|\\-");
        String[] ch = temp.split("\\d+");
        if (num == null || num.length == 0) {
            value = 123456789;
        } else {
            value += Integer.parseInt(num[0]);
            for (int i = 1; i < ch.length; i++) {
                if (ch[i].equals("+")) {
                    value += Integer.parseInt(num[i]);
                } else {
                    value -= Integer.parseInt(num[i]);
                }
            }
        }
        return value;
    }
}
