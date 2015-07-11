package com.raylew.algorithm.hsoj;

/*
 王子救公主，迷宫问题
500年前，Jesse是我国最卓越的剑客。他英俊潇洒，而且机智过人^_^。
突然有一天，Jesse心爱的公主被魔王困在了一个巨大的迷宫中。
Jesse听说这个消息已经是两天以后了，他知道公主在迷宫中还能坚持T天，他急忙赶到迷宫，开始到处寻找公主的下落。
 时间一点一点的过去，Jesse还是无法找到公主。最后当他找到公主的时候，美丽的公主已经死了。
 从此Jesse郁郁寡欢，茶饭不思，一年后追随公主而去了。
 T_T 500年后的今天，Jesse托梦给你，希望你帮他判断一下当年他是否有机会在给定的时间内找到公主。
 他会为你提供迷宫的地图以及所剩的时间T。请你判断他是否能救出心爱的公主。
输入格式（Input）
题目包括多组测试数据。
每组测试数据以三个整数N,M,T(0<n, m≤20, t>0)开头，分别代表迷宫的长和高，以及公主能坚持的天数。
紧接着有M行，N列字符，由"."，"*"，"P"，"S"组成。其中
"." 代表能够行走的空地。
"*" 代表墙壁，Jesse不能从此通过。
"P" 是公主所在的位置。
"S" 是Jesse的起始位置。
每个时间段里Jesse只能选择“上、下、左、右”任意一方向走一步。
输入以0 0 0结束。
输出格式（Output）
如果能在规定时间内救出公主输出“YES”，否则输出“NO”。
输入样例（Sample Input）
4 4 10
....
....
....
S**P
0 0 0
输出样例（Sample Output）
YES
 */
public class HSOJ1006 {

    public static int count = 0;
    //表示王子的移动方向，1向上2向下3向左4向右
    public static int flag = 1;

    public static void main(String[] args) {
        String[][] str = {{".", ".", ".", "*"}, {"*", "*", ".", "."},
                {"*", ".", ".", "."}, {".", ".", "*", "."}};
        boolean[][] bo = {{true, true, true, false},
                {false, false, true, true}, {false, true, true, true},
                {true, true, false, true}};
        move(0, 0, str, bo);
    }

    public static void move(int i, int j, String[][] str, boolean[][] bo) {
        if (judge(bo)) {
            System.out.println("over");
        } else {
            bo[i][j] = false;
            if (i == 2 && j == 3) {
                System.out.print(count + " ");
            }
            if (i == 0 && j == 0) {
                count = 0;
            }

            if (i > 0 && bo[i - 1][j] == true) {// 向上
                count++;
                flag = 1;
                bo[i][j] = false;
                move(i - 1, j, str, bo);
            }
            if (i < 3 && bo[i + 1][j] == true) {// 向下
                count++;
                flag = 2;
                bo[i][j] = false;
                move(i + 1, j, str, bo);
            }
            if (j > 0 && bo[i][j - 1] == true) {// 向左
                count++;
                flag = 3;
                bo[i][j] = false;
                move(i, j - 1, str, bo);
            }
            if (j < 3 && bo[i][j + 1] == true) {// 向右
                count++;
                flag = 4;
                bo[i][j] = false;
                move(i, j + 1, str, bo);
            }
            count--;
        }
    }

    /**
     * 递归出口
     *
     * @param bo
     * @return
     */
    public static boolean judge(boolean[][] bo) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (bo[i][j] == false) {
                    count++;
                }
            }
        }
        return (count == 16) ? true : false;
    }
}
