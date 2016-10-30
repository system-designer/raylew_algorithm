package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/10/29.
 * 俄国农夫乘法
 */
public class RussianFarmerMultiply {
    public static void main(String[] args) {
        System.out.println(multiply(45, 45));
    }

    public static int multiply(int n, int m) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return m;
        } else {
            if (n % 2 == 0) {
                return multiply(n / 2, 2 * m);
            } else {
                return multiply(n / 2, 2 * m) + m;
            }
        }
    }
}
