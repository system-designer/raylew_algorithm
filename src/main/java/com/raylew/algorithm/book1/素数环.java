package com.raylew.algorithm.book1;

/*
素数环
n个数字（1,2,3...n）围成一个圈，要求相邻的两个数字之和是质数。
题目要求根据给出的n，计算所有能够组成满足条件的圈的数字序列。
 */
public class 素数环 {
    public static int N = 6;
    public static int total = 0;
    public static int[] arr = new int[N];
    public static boolean[] primeArr = new boolean[2 * N + 1];

    public static void main(String[] args) {
        for (int i = 2; i <= 2 * N; i++) {
            int j;
            for (j = 2; j < i; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j == i) {
                primeArr[i] = true;
            }
        }
        dfs(0);
        System.out.println(total);
    }

    public static void dfs(int cur) {
        if (cur == N) {
            int i;
            for (i = 0; i < N; i++) {
                if (!primeArr[arr[i] + arr[(i + 1) % N]]) {
                    break;
                }
            }
            if (i == N) {
                for (int j = 0; j < N; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
                total++;
            }
        }else{
            for (int i = 1; i <= N; i++) {
                arr[cur] = i;
                int j;
                for (j = 0; j < cur; j++) {
                    if (arr[cur] == arr[j]) {
                        break;
                    }
                }
                if (j == cur) {
                    dfs(cur + 1);
                }
            }
        }
    }

}
