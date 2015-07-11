package com.raylew.algorithm.book1;

/*
 困难的串
 如果一个字符串包含两个相邻的重复字串，则称它是“容易的串”，其他串称为“困难的串”。例如，BB,ABCDACABCAB,ABCDABCD都是容易的，
 而D,DC,ABDAB,CBABCBA都是困难的。输入正整数n和L，输出由前L个字符组成的，字典序第k小的困难的串。
 例如，当L=3时，前7个困难的串分别为：A,AB,ABA,ABAC,ABACA,ABACAB,ABACABA.输入保证答案不超过80个字符。
 样例输入：
 7 3
 30 3
 样例输出：
 ABACABA
 ABACABCACBABCABACABCACBACABA
 */
public class 困难的串 {
    public static int L = 3;
    public static int[] str = new int[1000];
    public static int total = 0;

    public static void main(String[] args) {
        dfs(0);
    }

    public static void dfs(int cur) {
        if (total >= 30) {
            if (total == 30) {
                for (int i = 0; i < cur; i++) {
                    char c = (char) (65 - 1 + str[i]);
                    System.out.print(c);
                }
            }
            return;
        }
        for (int i = 1; i <= L; i++) {
            str[cur] = i;
            boolean ok1 = true;

            for (int j = 1; (2 * j) <= cur + 1; j++) {
                boolean ok2 = false;
                for (int k = 0; k < j; k++) {
                    if (str[cur - k] != str[cur - k - j]) {
                        ok2 = true;
                    }
                }
                if (!ok2) {//
                    ok1 = false;
                    break;
                }
            }
            if (ok1) {
                total++;
                dfs(cur + 1);
            }
        }
    }
}
