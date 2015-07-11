package com.raylew.algorithm.book1;

/**
 * @author Ray Lew
 */
public class 四色问题 {
    public static int N = 8;// 8
    public static int tot = 0;

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}
        };
        boolean[] use = new boolean[N];
        int[] color = new int[N];
        for (int i = 1; i <= 4; i++) {
            color[0] = i;
            use[0] = true;
            dfs(0, matrix, use, color);
            use[0] = false;
        }
        System.out.println(tot);
    }

    public static void dfs(int v_index, int[][] matrix, boolean[] use, int[] color) {
        if (success(use)) {
            tot++;
        }
        for (int j = 0; j < N; j++) {
            if (j != v_index) {
                if (matrix[v_index][j] == 1) {
                    if (use[j] == false) {
                        for (int k = 1; k <= 4; k++) {
                            if (k != color[v_index]) {
                                color[j] = k;
                                use[j] = true;
                                dfs(j, matrix, use, color);
                                use[j] = false;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param use
     * @return
     */
    public static boolean success(boolean[] use) {
        int i = 0;
        for (i = 0; i < use.length; i++) {
            if (i != 4) {
                if (use[i] == false) {
                    break;
                }
            }
        }
        return (i == use.length) ? true : false;
    }
}
