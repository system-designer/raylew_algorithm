package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/10/31.
 * 高斯消元法改进-部分选主元消元法
 */
public class GaussElimination {
    public static void main(String[] args) {
        double[][] a = {{2, -1, 1, 1},
                {4, 1, -1, 5},
                {1, 1, 1, 0}
        };
        int i, pivot, j, k, p, q, l;
        double temp, b;
        for (i = 0; i < 3; i++) {
            //选主元(行)
            pivot = i;
            for (j = i + 1; j < 3; j++) {
                if (Math.abs(a[j][i]) > Math.abs(a[pivot][i]))
                    pivot = j;
            }
            //两行交换
            for (k = 0; k <= 3; k++) {
                b = a[i][k];
                a[i][k] = a[pivot][k];
                a[pivot][k] = b;
            }
            //逐行相消
            for (l = i + 1; l < 3; l++) {
                temp = a[l][i] / a[i][i];
                for (k = i; k <= 3; k++) {
                    a[l][k] = a[l][k] - a[i][k] * temp;
                }
            }
        }
        for (p = 0; p < 3; p++) {
            for (q = 0; q < 4; q++) {
                System.out.print(a[p][q] + " ");
            }
            System.out.println();
        }

    }
}
