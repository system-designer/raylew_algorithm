package com.raylew.algorithm.ntoj;

import java.util.Arrays;
import java.util.Scanner;

/*
数学黑洞6174。已知：一个任意的四位正整数，将数字重新组合成一个最大的数和最小的数相减，重复这个过程，最多七步，必得6174。
即：7641-1467=6174。将永远出不来。求证：所有四位数数字(全相同的除外)，均能得到6174。输出掉进黑洞的步数。
输入一个四位数字不全相同的四位正整数
输出掉进黑洞的步数
样例输入
1034
样例输出
Step=2
 */
public class B0004 {

    public static int fir = 0;
    public static int sec = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        fir = Math.abs(maxStr(num + "") - minStr(num + ""));
        int p = 0;
        while (sec != fir) {
            sec = Math.abs(maxStr(fir + "") - minStr(fir + ""));
            fir = Math.abs(maxStr(sec + "") - minStr(sec + ""));
            p++;
            System.out.println("first:" + fir + ",second" + sec);
        }
        System.out.println(p + 1);
    }

    /**
     * 最小字符串
     *
     * @param str
     * @return
     */
    public static int minStr(String str) {
        char[] temp = str.toCharArray();
        Arrays.sort(temp);
        return Integer.parseInt(String.valueOf(temp));
    }

    /**
     * 最大字符串
     *
     * @param str
     * @return
     */
    public static int maxStr(String str) {
        char[] temp = str.toCharArray();
        Arrays.sort(temp);
        String s = "";
        for (int i = temp.length - 1; i >= 0; i--) {
            s += temp[i];
        }
        return Integer.parseInt(s);
    }
}
