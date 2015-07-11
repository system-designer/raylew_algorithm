package com.raylew.algorithm.book1;

import java.util.Scanner;

public class 分金币问题 {
    static int m, sum;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        m = scan.nextInt();
        int[] a = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = scan.nextInt();
            sum += a[i];
            b[i] = a[i];
        }
        sum /= m;
        int y = f(a), min = y;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < m; j++)
                a[j] = b[j];
            next(a);
            int t = f(a);
            if (min > t) {
                min = t;
            }
        }
        System.out.println(min);
    }

    public static int f(int[] a) {
        int x = 0, y = 0;
        for (int i = 0; i < m - 1; i++) {
            if (a[i] > sum) {
                x = a[i] - sum;
                a[i + 1] += x;
                y += x;
            } else {
                x = sum - a[i];
                a[i + 1] -= x;
                y += x;
            }
        }
        return y;
    }

    public static void next(int[] a) {
        int k = a[m - 1];
        for (int i = m - 2; i >= 0; i--) {
            a[i + 1] = a[i];
        }
        a[0] = k;
    }
}
