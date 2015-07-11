package com.raylew.algorithm.book1;

/*
 求一个串的最大周期数
 */
public class 周期串 {
    public static void main(String[] args) {
        String str = "HoHoHo";
        boolean ok = true;
        int length = str.length();
        for (int i = 1; i <= length && (length % i == 0); i++) {
            ok = true;
            for (int j = i; j < length; j++) {
                if (str.charAt(j) != str.charAt((j % i))) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println(i);
                break;
            }
        }
    }
}
