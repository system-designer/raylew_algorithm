package com.raylew.algorithm.book1;

import java.util.Arrays;

public class 组合问题 {
    public static int N = 3;
    public static int goal = 10;
    public static int total = 0;
    public static int[] arr = {2, 3, 6, 7};
    public static int[] res = new int[1000];

    public static void main(String[] args) {
        dfs(0);
        System.out.println(total);
    }

    public static void dfs(int cur) {
        int sum = 0;
        for (int i = 0; i < cur; i++) {
            sum += res[i];
        }
        if (sum >= goal) {
            if (sum == goal) {
                total++;
                for (int i = 0; i < cur; i++) {
                    System.out.print(res[i] + ",");
                }
                System.out.println();
            }
            return;
        }
        int index = Arrays.binarySearch(arr, 2);
        for (int i = index; i < N; i++) {
            if (cur == 0) {
                res[cur] = arr[i];
                dfs(cur + 1);
            } else {
                if (arr[i] >= res[cur - 1]) {
                    res[cur] = arr[i];
                    dfs(cur + 1);
                }
            }
        }
    }
}
