package com.raylew.algorithm.other;

import java.util.Scanner;
import java.util.Stack;

/**
 * 进制转换
 *
 * @author Administrator
 */
public class 进制转换 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        String str1 = radixString(Integer.parseInt(str), 2);//十进制转化为2进制
        System.out.println(str1);
        String str2 = radixNum(str, 2, 10);//2进制转化为10进制
        System.out.println(str2);
    }

    /**
     * 将十进制数转为任意进制
     *
     * @param num   带转化的数
     * @param radix 目标进制
     * @return
     */
    public static String radixString(int num, int radix) {
        if (radix > 16) {
            throw new RuntimeException("进制数超出范围，radix<=16");
        }
        StringBuffer str = new StringBuffer("");
        String digths = "0123456789ABCDEF";
        Stack<Character> s = new Stack<Character>();
        while (num != 0) {
            s.push(digths.charAt(num % radix));
            num /= radix;
        }
        while (!s.isEmpty()) {
            str.append(s.pop());
        }
        return str.toString();
    }

    /**
     * 16进制内任意进制转换
     *
     * @param num       带转化的数
     * @param srcRadix  原进制
     * @param destRadix 目标进制
     * @return
     */
    public static String radixNum(String num, int srcRadix, int destRadix) {
        if (srcRadix == destRadix) {
            return num;
        }
        String digths = "0123456789ABCDEF";
        char[] chars = num.toCharArray();
        int len = chars.length;
        if (destRadix != 10) {//目标进制不是十进制 先转化为十进制  
            num = radixNum(num, srcRadix, 10);
        } else {
            int n = 0;
            for (int i = len - 1; i >= 0; i--) {
                n += digths.indexOf(chars[i]) * Math.pow(srcRadix, len - i - 1);
            }
            return n + "";
        }
        return radixString(Integer.valueOf(num), destRadix);
    }
}
