package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/11/13.
 * 动态规划计算二项式系数
 */
public class BinomialExpression {
    public static void main(String[] args) {
        System.out.println(binomial(5,2));
    }

    public static int binomial(int n, int k) {
        int[][] c = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int temp = i < k ? i : k;
            for (int j = 0; j <= temp; j++) {
                if (j == 0 || j == i) {
                    c[i][j] = 1;
                } else {
                    c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
                }
            }
        }
        return c[n][k];
    }
}
