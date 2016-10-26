package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/10/26.
 */
/*第4章 分治法  大整数乘法--计算2个大整数的乘积*/
public class BigIntegerMultiply {
    public static void main(String[] args) {
        long a = 95211154;
        long b = 9039;
        String s1 = "95211154";
        String s2 = "9039";

        long suppose = a * b;
        long result = multiply(s1, s2);

        System.out.println(suppose + "  " + result);
        System.out.println(suppose == result);

    }

    //用字符串读入2个大整数
    public static long multiply(String a,String b)
    {
        long result = 0;
        //递归结束的条件
        if(a.length() == 1 || b.length() == 1) {
            result = mul(a, b);
        }else{
            //如果2个字符串的长度都 >= 2
            //截取前一半的字符串(较短的一半)
            String a1 = a.substring(0, a.length() / 2 );
            //截取后一半的字符串
            String a0 = a.substring(a1.length(), a.length());
            //System.out.println(a1);
            //System.out.println(a0);
            String b1 = b.substring(0, b.length() / 2);
            String b0 = b.substring(b1.length(), b.length());

            //分治的思想将整数写成这样：
            // a = a1 * 10^(n1/2) + a0, b = b1 * 10^(n2/2)，相乘展开得到以下四项
            //其中n1，n2为2个整数a，b的位数
            result = (long) (multiply(a1,b1) * Math.pow(10, a0.length() + b0.length())
                    + multiply(a1,b0) * Math.pow(10, a0.length()) + multiply(a0,b1) * Math.pow(10, b0.length())
                    + multiply(a0,b0));
        }
        return result;
    }

    //计算2个字符串表示的大整数的乘积
    private static long mul(String s1,String s2){
        //实际上只有当一个字符串的长度为1时，这个函数才会被调用
        //存放大整数s1的各位
        //存放大整数s2的各位
        int[] a = new int[s1.length()];
        int[] b = new int[s2.length()];
        //将字符'i'转化为整数i放入整数数组
        for(int i = 0;i < a.length;i++)
            a[i] = (int) s1.charAt(i) - 48;
        for(int i = 0;i < b.length;i++)
            b[i] = (int) s2.charAt(i) - 48;
        long num1 = toNum(a);
        long num2 = toNum(b);
        return num1 * num2;

    }

    private static long toNum(int[] a){ //将一个整数的位数组转化为它对应的数
        long result = 0;
        for(int i = 0;i < a.length;i++)
            result = result * 10 + a[i];
        //System.out.println(result);
        return result;
    }


}
