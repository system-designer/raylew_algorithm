package com.raylew.algorithm.ntoj;

import java.math.BigDecimal;
import java.math.BigInteger;

/*
 * 求一个数的阶乘数末尾的0的个数
 */
public class Unknown {
    private static int zeroNum(int n) {
        int ret = 0, j;
        for (int i = 1; i <= n; i++) {
            j = i;
            while (j % 5 == 0) {
                ret++;
                j = j / 5;
            }
        }
        return ret;
    }

    private static BigInteger fac(long n) {
        BigDecimal a = new BigDecimal(1);
        BigDecimal b;
        for (long i = 1; i <= n; i++) {
            b = new BigDecimal(i);
            a = a.multiply(b);
        }
        return a.toBigInteger();
    }

    public static void main(String[] args) {
        System.out.println("12!为" + fac(12) + ",后面零的个数为" + zeroNum(12));
    }
}
