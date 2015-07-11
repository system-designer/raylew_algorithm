package com.raylew.algorithm.book1;

/*
求阶乘的精确值
 */
public class 阶乘的精确值 {
    public static void main(String[] args) {
        int n = 30;
        int maxN = 3000;
        int[] f = new int[maxN];
        f[0] = 1;
        int i, j;
        for (i = 2; i <= n; i++) {
            int c = 0;
            for (j = 0; j < maxN; j++) {
                int s = f[j] * i + c;
                f[j] = s % 10;
                c = s / 10;
            }
        }
        for (j = maxN - 1; j >= 0; j--) {
            if (f[j] != 0) {
                break;
            }
        }
        for (i = j; i >= 0; i--) {
            System.out.print(f[i]);
        }
    }
}
