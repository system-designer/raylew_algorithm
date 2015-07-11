package com.raylew.algorithm.other;

/*
  螺旋数：
  1  2  3  4
  12 13 14  5
  11 16 15  6
  10  9  8  7
要求输入N，输出一个NxN的方阵,例如输入N=4，则输出上面所示的方阵。
 */
public class 螺旋数 {

    public static int N = 100;

    public static void main(String[] args) {
        int i = 0, j = -1;
        int flag = 1;
        int[][] arr = new int[N][N];
        int total = (N % 2 == 0) ? N / 2 : N / 2 + 1;
        int num = 1, mark = 0;
        while (mark < total && num <= (N * N)) {
            if (flag == 1) {//左
                j++;
                arr[i][j] = num;
                if (j == (N - 1 - mark)) {
                    flag = 2;
                }
            } else if (flag == 2) {//下
                i++;
                arr[i][j] = num;
                if (i == (N - 1 - mark)) {
                    flag = 3;
                }
            } else if (flag == 3) {//右
                j--;
                arr[i][j] = num;
                if (j == mark) {
                    flag = 4;
                }
            } else if (flag == 4) {//上
                i--;
                arr[i][j] = num;
                if (i == (mark + 1)) {
                    flag = 1;
                    mark++;
                }
            }
            num++;
        }
        int zws = (N * N + "").length();
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                for (int k = (arr[i][j] + "").length(); k <= zws; k++) {
                    System.out.print(" ");
                }
                System.out.print(" " + arr[i][j]);
            }
            System.out.println("");
        }
    }
}
