package com.raylew.algorithm.lanqiaocup;

/*
幸运数的另一种解法，题目要求参见LanQiao12_3.java
 */
public class LanQiao13_5 {
    public static void main(String[] args) {
        int m = 1, n = 3;
        int total = 0;
        int[] arr = new int[1000000];
        boolean[] use = new boolean[1000000];
        for (int i = 0; i < 1000000; i++) {
            arr[i] = i;
        }
        int luckyNum = 2;
        int index = 1;
        int length = arr.length;
        while (true) {
            int j = 0;
            for (int i = 1; i < length; i++) {
                if (use[i] == false) {
                    j++;
                    if (j % luckyNum == 0) {
                        use[i] = true;
                    }
                }
            }
            for (int i = index + 1; i < length; i++) {
                if (use[i] == false) {
                    index = i;
                    break;
                }
            }
            luckyNum = arr[index];
            if (luckyNum >= n) {
                break;
            }
            if (luckyNum > m && luckyNum < n) {
                total++;
            }
        }
        System.out.println("total:" + total);
    }
}
