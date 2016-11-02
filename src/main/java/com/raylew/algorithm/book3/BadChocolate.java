package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/10/31.
 * <title>坏巧克力</title>
 * 两玩家轮流掰一块m*n格的巧克力，
 * 其中一块1*1的小块是坏的，
 * 每次只能沿着方格边界一掰到底。
 * 每掰一次，掰的人就把不含坏巧克力的那块吃掉
 * (每次都掰成两快，不含坏巧克力的那块全吃掉)，
 * 谁碰到最后那块坏巧克力就算输了。
 * 写一个可以和计算机玩的程序，
 * 在程序的胜局电脑应该走出制胜一步，
 * 败局只要随机下出合格的一步就行。
 * （考虑先走后走以及方块形状与游戏胜败的关系）
 */
public class BadChocolate {
    private static int m = 4, n = 6;
    private static int[][] chocolate = {
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1}
    };
    private static int[] row = new int[m];
    private static int[] col = new int[n];
    private static int badRow;
    private static int badCol;
    private static int path;
    private static int count = 0;

    public static void main(String[] args) {
        //set row and col,-1 bad
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (chocolate[i][j] == 0) {
                    badRow = i;
                    badCol = j;
                    row[i] = -1;
                    col[j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row[i] != -1) {
                row[i] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (col[j] != -1) {
                col[j] = 1;
            }
        }
        path = 0;
        dfs(0, m-1, 0, n-1);
        System.out.println();
        System.out.println(count);
    }

    /**
     * dfs遍历所有掰开的可能
     *
     * @param rowStart 起始行
     * @param rowEnd   终止行
     * @param colStart 起始列
     * @param colEnd   终止列
     */
    public static void dfs(int rowStart, int rowEnd, int colStart, int colEnd) {
        print(rowStart, rowEnd, colStart, colEnd);
        if (rowStart == rowEnd && colStart == colEnd) {
            System.out.println(path + " ");
            count++;
            return;
        } else {
            if (rowStart < rowEnd) {
                //逐行
                for (int i = rowStart; i <= rowEnd; i++) {
                    if (row[i] != -1) {
                        path++;
                        if (i >= badRow) {
                            dfs(rowStart, i - 1, colStart, colEnd);
                        } else {
                            dfs(i + 1, rowEnd, colStart, colEnd);
                        }
                        path--;
                    }
                }
            }
            if (colStart < colEnd) {
                //逐列
                for (int j = colStart; j <= colEnd; j++) {
                    if (col[j] != -1) {
                        path++;
                        if (j >= badCol) {
                            dfs(rowStart, rowEnd, colStart, j - 1);
                        } else {
                            dfs(rowStart, rowEnd, j + 1, colEnd);
                        }
                        path--;
                    }
                }
            }
        }
    }

    public static boolean judge() {
        int countRow = 0;
        for (int i = 0; i < m; i++) {
            if (row[i] == 0) {
                countRow++;
            }
        }
        int countCol = 0;
        for (int j = 0; j < n; j++) {
            if (col[j] == 0) {
                countCol++;
            }
        }
        return countRow == (m - 1) && countCol == (n - 1);
    }

    public static void print(int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                System.out.print(chocolate[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
