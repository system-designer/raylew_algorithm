package com.raylew.algorithm.lanqiaocup;

/*
标题：带分数
    100 可以表示为带分数的形式：100 = 3 + 69258 / 714
    还可以表示为：100 = 82 + 3546 / 197
    注意特征：带分数中，数字1~9分别出现且只出现一次（不包含0）。
    类似这样的带分数，100 有 11 种表示法。
题目要求：
从标准输入读入一个正整数N (N<1000*1000)
程序输出该数字用数码1~9不重复不遗漏地组成带分数表示的全部种数。
注意：不要求输出每个表示，只统计有多少表示法！
例如：
用户输入：
100
程序输出：
11
再例如：
用户输入：
105
程序输出：
6
 */
public class LanQiao13_4 {
    static int num_times[] = new int[10];

    public static void main(String[] args) {
        int n = 95000;
        int total = 0;
        int num_i[] = new int[10];
        for (int i = 1; i <= n - 1; i++) {
            for (int t = 0; t < 10; t++) {
                num_times[t] = 0;
            }
            if (dup(i)) {
                continue;
            }
            int len_k = (9 - (i + "").length()) / 2;
            int max_k = 1;
            for (int t = 1; t <= len_k; t++) {
                max_k = max_k * 10;
            }
            for (int t = 0; t < 10; t++)
                // ��ʱ����i���õ������ָ���
                num_i[t] = num_times[t];
            for (int k = 1; k < max_k; k++) {
                for (int t = 0; t < 10; t++)
                    // �ָ�i���õ������ָ���
                    num_times[t] = num_i[t];
                if (dup(k)) {
                    continue;
                }
                int j = (n - i) * k;
                if (j < k) {
                    continue;
                }
                if (dup(j)) {
                    continue;
                }
                if (checkAll()) {
                    total++;
                    System.out.println(n + "=" + i + "+" + j + "/" + k);
                }
            }
        }
        System.out.println(total);
    }

    // �ж�ÿ�����Ƿ�����ظ�������
    public static boolean dup(int num) {
        while (num > 0) {
            int t = num % 10;
            num_times[t]++;
            if (num_times[t] > 1) {
                return true;
            }
            num = num / 10;
        }
        if (num_times[0] > 0) {
            return true;
        }
        return false;
    }

    public static boolean checkAll() {
        for (int t = 1; t < 10; t++) {
            if (num_times[t] == 0) {
                return false;
            }
        }
        return true;
    }
}
