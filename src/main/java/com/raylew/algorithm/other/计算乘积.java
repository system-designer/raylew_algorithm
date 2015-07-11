package com.raylew.algorithm.other;

/*
  题目
  输出所有的满足条件的算式，x*y=z(x,y,z由1~9不重复,无遗漏组成)
  答案：
  138*42=5796 157*28=4396 159*48=7632
  186*39=7254 198*27=5346 297*18=5346 483*12=5796 1738*4=6952 1963*4=7852 成功构建
  (运行时间: 7 分钟46 秒)
 */
public class 计算乘积 {
    //标记1~9中已经用过的数字
    public static boolean[] b = new boolean[9];

    public static void main(String[] args) {
        //循环产生i*j=k，输出满足条件的算式
        for (int i = 100; i <= 9999; i++) {
            if (!removeX(i)) {
                markNum(i);//标记x已用过的数
                for (int j = 1; j <= 99; j++) {
                    if (!removeY(j)) {
                        markNum(j);//标记y已用过的数
                        for (int k = 1000; k <= 9999; k++) {
                            if (!removeY(k)) {
                                markNum(k);
                                if (i * j == k && isRes()) {
                                    System.out.println(i + "*" + j + "=" + k);
                                }
                                setB(k);
                            }
                        }
                        setB(j);//取消y标记
                    }
                }
                setB(i);//取消x标记
            }
        }
    }

    public static boolean removeX(int x) {
        boolean res = false;
        String str = x + "";
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    res = true;
                    break;
                }
            }
            if (res == true) {
                break;
            }
        }
        return res;
    }

    public static boolean removeY(int x) {
        boolean res = false;
        String str = Integer.toString(x);
        for (int j = 0; j < 9; j++) {
            if (str.contains((j + 1) + "") && b[j] == true) {
                res = true;
            }
        }
        if (!res) {
            for (int i = 0; i < str.length() - 1; i++) {
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) {
                        res = true;
                        break;
                    }
                }
                if (res == true) {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 标记已经用过的数字
     *
     * @param x
     */
    public static void markNum(int x) {
        String str = Integer.toString(x);
        for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j <= 9; j++) {
                if (Integer.parseInt(str.charAt(i) + "") == j) {
                    b[j - 1] = true;
                }
            }
        }
    }

    /**
     * 取消标记已经用过的数字
     *
     * @param num
     */
    public static void setB(int num) {
        String temp = num + "";
        for (int i = 0; i < b.length; i++) {
            if (temp.contains((i + 1) + "")) {
                b[i] = false;
            }
        }
    }

    public static boolean isRes() {
        int i = 0;
        for (; i < b.length; i++) {
            if (b[i] == false) {
                break;
            }
        }
        boolean res = (i == b.length) ? true : false;
        return res;
    }
}
