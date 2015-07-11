package com.raylew.algorithm.ntoj;

/*
模式识别的“中心”问题：
       模式识别的一个关键问题是判别图形的“中心”，当图形经过扫描仪扫描后，得到一个实数矩阵，我们首先要找到该图形的“中心”，然后才能开始识别。设实数矩阵由m行n列组成(1≤100，n≤100)，所谓的中心(i,j)是使第i行上边元素(不包括第i行)的总和与第i行下边元素(不包括第i行)的总和之差的绝对值最小，而且第j列左边元素(不包括第j列)的总和与第j列右边元素(不包括第j列)的总和之差的绝对值最小。
      现已知一扫描所得的实数矩阵，求其“中心”。若有多个“中心”，给出任意一个“中心”即可。
输入
第一行输入两个整数m、n，以下m行是实数矩阵，每行各有n个实教。在每一行中，数据之间只有一个空格。每行的行首、行末无多余空格。
输出
Center=(xxx，yyy)
（xxx、yyy分别表示中心的行和列）
样例输入
5 5
0.2 0.3 0.2 0.3 0.2
0.2 0.3 0.4 0.2 0.2
0.3 0.4 0.2 0.2 0.4
0.5 0.2 0.2 0.2 0.3
0.3 0.3 0.4 0.4 0.2
样例输出
Center=(3,3)
 */
public class B0001 {

    public static int length = 5;
    public static int width = 5;

    public static void main(String[] args) {
        double[][] matrix = {{0.1, 0.2, 0.3, 0.4, 0.5}, {0.1, 0.2, 0.3, 0.4, 0.5}, {0.1, 0.2, 0.3, 0.4, 0.5}, {0.1, 0.2, 0.3, 0.4, 0.5}, {0.1, 0.2, 0.3, 0.4, 0.5}};
        double[][] rowDifference = new double[length][width];
        double[][] colDifference = new double[length][width];
        //得到每一个数的中心判断条件的值
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                double upSum = 0;
                double downSum = 0;
                for (int i1 = 0; i1 < i; i1++) {
                    upSum += matrix[i1][j];
                }
                for (int i2 = i; i2 < length; i2++) {
                    downSum += matrix[i2][j];
                }
                rowDifference[i][j] = Math.abs((upSum - downSum));
                double leftSum = 0;
                double rightSum = 0;
                for (int j1 = 0; j1 < j; j1++) {
                    leftSum += matrix[i][j1];
                }
                for (int j2 = j; j2 < width; j2++) {
                    rightSum += matrix[i][j2];
                }
                colDifference[i][j] = Math.abs((leftSum - rightSum));
            }
        }
        //得到最小的存在数组
        double minR = rowDifference[0][0];
        double minC = colDifference[0][0];
        int x = 0, y = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (rowDifference[i][j] <= minR) {
                    minR = rowDifference[i][j];
                    x = i;
                    if (colDifference[i][j] <= minC) {
                        minC = colDifference[i][j];
                        y = j;
                    }
                }
            }
        }
        //输出模式中心
        System.out.print("中心:" + x + "," + y);
    }
}
