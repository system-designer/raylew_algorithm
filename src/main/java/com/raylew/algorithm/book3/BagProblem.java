package com.raylew.algorithm.book3;

import java.util.Scanner;

/**
 * Created by Raymond on 2016/11/13.
 * 动态规划解背包问题
 */
public class BagProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入背包能容纳的重量：");
        int w = scanner.nextInt();
        System.out.println("请输入物品件数：");
        int n = scanner.nextInt();
        int[][] tab = new int[n + 1][w + 1];// 结果表
        int[][] a = new int[n][2];// 物品的重量价值记录笔
        System.out.println("请分别输入" + n + "件物品的重量和价值：");
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < 2; k++) {
                a[i][k] = scanner.nextInt();
            }
        }
        System.out.println(n + "个物品的重量、价值分别是：");
        for (int i = 0; i < n; i++) {
            System.out.print("(" + a[i][0] + "kg," + a[i][1] + ")\t");
        }
        System.out.println();
        for (int i = 1; i < n + 1; i++) {
            for (int k = 1; k < w + 1; k++) {
                if (k < a[i - 1][0]) {
                    tab[i][k] = tab[i - 1][k];
                } else {
                    tab[i][k] = max(tab[i - 1][k - a[i - 1][0]] + a[i - 1][1], tab[i - 1][k]);
                }
            }
        }
        System.out.println("绘制的二维表格：");
        for (int i = 0; i < n + 1; i++) {
            for (int k = 0; k < w + 1; k++) {
                System.out.print(tab[i][k] + "\t");
            }
            System.out.println();
        }
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }
}
