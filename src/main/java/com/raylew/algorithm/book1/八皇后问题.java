package com.raylew.algorithm.book1;

/*
八皇后问题
在8*8的棋盘上摆放8个皇后，使其不能互相攻击，即任意的两个皇后不能处在同意行，同一列，或同意斜线上。
可以把八皇后问题拓展为n皇后问题，即在n*n的棋盘上摆放n个皇后，使其任意两个皇后都不能处于同一行、同一列或同一斜线上。
 */
public class 八皇后问题 {
    //皇后数目
    public static int n = 8;
    //结果总数
    public static int total = 0;
    //col[n]=x表示第n列的x行放置了皇后
    public static int[] col = new int[n];

    public static void main(String[] args) {
        dfs(0);
        System.out.println(total);
    }

    /**
     * dfs算法
     *
     * @param cur 当前dfs遍历的行
     */
    public static void dfs(int cur) {
        if (cur == n) {
            total++;
        } else {
            int i = 0, j = 0;
            for (i = 0; i < n; i++) {// 遍历n列
                boolean ok = true;
                col[cur] = i;
                for (j = 0; j < cur; j++) {// 遍历之前已经放置皇后的行，查看皇后放在第i列是否满足
                    if (col[cur] == col[j] || (cur - col[cur]) == (j - col[j])
                            || (cur + col[cur]) == (j + col[j])) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    dfs(cur + 1);
                }
            }
        }
    }
}
