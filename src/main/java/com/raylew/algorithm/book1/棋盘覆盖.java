package com.raylew.algorithm.book1;

/*
棋盘覆盖
在一个2^k×2^k 个方格组成的棋盘中，恰有一个方格与其他方格不同，称该方格为一特殊方格，且称该棋盘为一特殊棋盘。
在棋盘覆盖问题中，要用图示的4种不同形态的L型骨牌覆盖给定的特殊棋盘上除特殊方格以外的所有方格，且任何2个L型骨牌不得重叠覆盖。
 */
public class 棋盘覆盖 {
    static int MAX_SIZE = 16;
    static int board[][] = new int[MAX_SIZE][MAX_SIZE];
    static int tile = 0;

    public void chessboard(int tr, int tc, int dr, int dc, int size) {
        if (size == 1)
            return;
        int t = tile++, s = size / 2;
        if (dr < tr + s && dc < tc + s)
            chessboard(tr, tc, dr, dc, s);
        else {
            board[tr + s - 1][tc + s - 1] = t;
            chessboard(tr, tc, tr + s - 1, tc + s - 1, s);
        }
        if (dr < tr + s && dc >= tc + s)
            chessboard(tr, tc + s, dr, dc, s);
        else {
            board[tr + s - 1][tc + s] = t;
            chessboard(tr, tc + s, tr + s - 1, tc + s, s);
        }
        if (dr >= tr + s && dc < tc + s)
            chessboard(tr + s, tc, dr, dc, s);
        else {
            board[tr + s][tc + s - 1] = t;
            chessboard(tr + s, tc, tr + s, tc + s - 1, s);
        }
        if (dr >= tr + s && dc >= tc + s)
            chessboard(tr + s, tc + s, dr, dc, s);
        else {
            board[tr + s][tc + s] = t;
            chessboard(tr + s, tc + s, tr + s, tc + s, s);
        }
    }

    public static void main(String[] args) {
        int i, j;
        棋盘覆盖 p1 = new 棋盘覆盖();
        p1.chessboard(0, 0, 0, 1, 4);
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++)
                System.out.print(board[i][j] + " ");
            System.out.print("\n");
        }
    }
}
