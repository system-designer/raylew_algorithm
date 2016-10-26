package com.raylew.algorithm.book3;

/**
 * 全排列
 */
public class 全排列 {

    public static char[] text = {'a', 'b', 'c', 'd'};
    public static int[] A = new int[4];

    public static void main(String[] args) {
        permutation(text, 0, text.length);
        System.out.println("==========分割线===========");
        dicorder_perm(4, 0);
        System.exit(0);
    }

    /**
     * 全排列输出
     *
     * @param a [] 要输出的字符数组
     * @param m 输出字符数组的起始位置
     * @param n 输出字符数组的长度
     */
    public static void permutation(char a[], int m, int n) {
        int i;
        char t;
        if (m < n - 1) {
            permutation(a, m + 1, n);
            for (i = m + 1; i < n; i++) {
                // a[m]和a[i]交换位置
                t = a[m];
                a[m] = a[i];
                a[i] = t;
                permutation(a, m + 1, n);
                // a[m]和a[i]还原位置
                t = a[m];
                a[m] = a[i];
                a[i] = t;
            }
        } else {
            printResult(a);
        }
    }

    /**
     * 输出指定字符数组
     *
     * @param text 将要输出的字符数组
     */
    public static void printResult(char[] text) {
        for (int i = 0; i < text.length; i++) {
            System.out.print(text[i]);
        }
        System.out.println();
    }

    /**
     * 以字典序生成全排列，初始4,0（深搜）
     */
    public static void dicorder_perm(int n, int cur) {
        if (cur == n) {
            for (int i = 0; i < A.length; i++) {
                System.out.print(A[i]);
            }
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {// 尝试在cur位置放置i
                boolean ok = true;
                for (int j = 0; j < cur; j++) {// 判断i和之前的有没有重复
                    if (A[j] == i) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    A[cur] = i;
                    dicorder_perm(n, cur + 1);
                }
            }
        }
    }
}
