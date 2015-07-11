package com.raylew.algorithm.ntoj;

import java.util.Scanner;

/*
阶乘问题。也许你早就知道阶乘的含义，N阶乘是由1到N相乘而产生，如：
    12! = 1 x 2 x 3 x 4 x 5 x 6 x 7 x 8 x 9 x 10 x 11 x 12 = 479,001,600
12的阶乘最右边的非零位为6。
    写一个程序，计算N(1<=N<=50,000,000)阶乘的最右边的非零位的值。
注意:10,000,000！有2499999个零。
输入一个正整数N
输出表示最右边的非零位的值
样例输入
12
样例输出
6
 */
public class B0007 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int count = 0;
        for (int i = 1; i <= input; i++) {
            int temp = i;
            while (temp > 0) {
                if (temp % 5 == 0) {
                    count++;
                    temp = temp / 5;
                } else {
                    break;
                }
            }
        }
        System.out.println(count);
    }

}
