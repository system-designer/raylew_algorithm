package com.raylew.algorithm.book1;

public class P01背包 {
    static int C = 10;
    static int[] V = {0, 2, 2, 6, 5, 4, 0};
    static int[] W = {0, 6, 3, 5, 4, 6, 0};
    static int n = 5;
    static int[][] d = new int[n + 2][C + 1];

    public static void main(String[] args) {
        int i, j;
        for (i = n; i >= 1; i--) {
            for (j = 0; j <= C; j++) {
                if (i == n) {
                    d[i][j] = 0;
                } else {
                    d[i][j] = d[i + 1][j];
                }
                if (j >= V[i]) {
                    if (d[i][j] < (d[i + 1][j - V[i]] + W[i])) {
                        d[i][j] = d[i + 1][j - V[i]] + W[i];
                    }
                }
            }
            printArr();
        }
        System.out.println(d[1][C]);
    }

    public static void printArr() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }
}
