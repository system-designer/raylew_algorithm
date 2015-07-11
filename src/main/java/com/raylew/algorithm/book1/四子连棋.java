package com.raylew.algorithm.book1;

import java.util.Arrays;
import java.util.LinkedList;

/*
在一个4*4的棋盘上摆放了14颗棋子，其中有7颗白色棋子，7颗黑色棋子，有两个空白地带，
任何一颗黑白棋子都可以向上下左右四个方向移动到相邻的空格，这叫行棋一步，黑白双方交替走棋，
任意一方可以先走，如果某个时刻使得任意一种颜色的棋子形成四个一线（包括斜线），这样的状态为目标棋局。
输入描述 Input Description
从文件中读入一个4*4的初始棋局，黑棋子用B表示，白棋子用W表示，空格地带用O表示。
输出描述 Output Description
用最少的步数移动到目标棋局的步数。
样例输入 Sample Input
BWBO
WBWB
BWBW
WBWO
样例输出 Sample Output
5
 */
public class 四子连棋 {
    //标记已经访问的状态，判重
    public static boolean[] stateArr = new boolean[43036875];
    //从初始状态到某一状态的距离
    public static int[] distArr = new int[43036875];

    public static void main(String[] args) {
        // 测试数据：初始棋局，1表示黑棋，2表示白棋，0表示空位
        int[][] beginState = {{1, 2, 1, 0}, {2, 1, 2, 1}, {1, 2, 1, 2},
                {2, 1, 2, 0}};
        LinkedList<int[][]> state_list = new LinkedList<int[][]>();
        LinkedList<int[][]> state_list2 = new LinkedList<int[][]>();
        state_list.addLast(beginState);
        stateArr[getStateHash(beginState)] = true;
        int pre = 1;
        //bfs算法开始
        while (true) {
            int[][] state = state_list.removeFirst();
            if (success(state)) {
                System.out.println(distArr[getStateHash(state)]);
                for (int i = 0; i < state.length; i++) {
                    for (int j = 0; j < state.length; j++) {
                        System.out.print(state[i][j]);
                    }
                    System.out.println();
                }
                break;
            }
            //存放两个空位位置
            int[][] index_0_arr = new int[2][2];
            getIndex_0(state, index_0_arr);
            // 第一个空位
            int row_fir_0 = index_0_arr[0][0];
            int col_fir_0 = index_0_arr[0][1];
            //第一个空位上下左右移动最多可产生4种不同的状态
            // up
            if (row_fir_0 > 0 && (state[row_fir_0 - 1][col_fir_0] != 0)) {
                int new_row_fir_0 = row_fir_0 - 1;
                if (state[new_row_fir_0][col_fir_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_fir_0][col_fir_0] = pre;
                    newState[new_row_fir_0][col_fir_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // down
            if (row_fir_0 < 3 && (state[row_fir_0 + 1][col_fir_0] != 0)) {
                int new_row_fir_0 = row_fir_0 + 1;
                if (state[new_row_fir_0][col_fir_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_fir_0][col_fir_0] = pre;
                    newState[new_row_fir_0][col_fir_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // left
            if (col_fir_0 > 0 && (state[row_fir_0][col_fir_0 - 1] != 0)) {
                int new_col_fir_0 = col_fir_0 - 1;
                if (state[row_fir_0][new_col_fir_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_fir_0][col_fir_0] = pre;
                    newState[row_fir_0][new_col_fir_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // right
            if (col_fir_0 < 3 && (state[row_fir_0][col_fir_0 + 1] != 0)) {
                int new_col_fir_0 = col_fir_0 + 1;
                if (state[row_fir_0][new_col_fir_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_fir_0][col_fir_0] = pre;
                    newState[row_fir_0][new_col_fir_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }

            // 第二个空位
            int row_sec_0 = index_0_arr[1][0];
            int col_sec_0 = index_0_arr[1][1];
            //第二个空位上下左右移动最多可产生4种不同的状态
            // up
            if (row_sec_0 > 0 && (state[row_sec_0 - 1][col_sec_0] != 0)) {
                int new_row_sec_0 = row_sec_0 - 1;
                if (state[new_row_sec_0][col_fir_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_sec_0][col_sec_0] = pre;
                    newState[new_row_sec_0][col_fir_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // down
            if (row_sec_0 < 3 && (state[row_sec_0 + 1][col_sec_0] != 0)) {
                int new_row_sec_0 = row_sec_0 + 1;
                if (state[new_row_sec_0][col_sec_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_sec_0][col_sec_0] = pre;
                    newState[new_row_sec_0][col_sec_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // left
            if (col_sec_0 > 0 && (state[row_sec_0][col_sec_0 - 1] != 0)) {
                int new_col_sec_0 = col_sec_0 - 1;
                if (state[row_sec_0][new_col_sec_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_sec_0][col_sec_0] = pre;
                    newState[row_sec_0][new_col_sec_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // right
            if (col_sec_0 < 3 && (state[row_sec_0][col_sec_0 + 1] != 0)) {
                int new_col_sec_0 = col_sec_0 + 1;
                if (state[row_sec_0][new_col_sec_0] == pre) {
                    int[][] newState = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newState[i] = Arrays.copyOf(state[i], 4);
                    }
                    newState[row_sec_0][col_sec_0] = pre;
                    newState[row_sec_0][new_col_sec_0] = 0;
                    int hash = getStateHash(newState);
                    if (stateArr[hash] == false) {
                        stateArr[hash] = true;
                        distArr[hash] = distArr[getStateHash(state)] + 1;
                        state_list2.addLast(newState);
                    }
                }
            }
            // bfs的两个队列：当queue1为空时，将queue2赋值给queue1
            if (state_list.size() == 0) {
                state_list = state_list2;
                state_list2 = new LinkedList<int[][]>();
                pre = (pre == 1) ? 2 : 1;// pre=1表示下一步由黑方走
            }
        }
    }

    /**
     * 得到空位位置
     *
     * @param state       当前状态
     * @param index_0_arr 用数组存储2个空位的位置
     */
    public static void getIndex_0(int[][] state, int[][] index_0_arr) {
        boolean first = false;
        LOOP:
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == 0) {
                    if (first == false) {
                        first = true;
                        index_0_arr[0][0] = i;
                        index_0_arr[0][1] = j;
                    } else {
                        index_0_arr[1][0] = i;
                        index_0_arr[1][1] = j;
                        break LOOP;
                    }
                }
            }
        }
    }

    /**
     * 判断当前状态是否满足条件，即四子连棋
     *
     * @param state 当前状态
     * @return
     */
    public static boolean success(int[][] state) {
        boolean ok = false;
        for (int i = 0; i < 4; i++) {
            if ((state[i][0] == state[i][1] && state[i][1] == state[i][2] && state[i][2] == state[i][3])
                    || (state[0][i] == state[1][i]
                    && state[1][i] == state[2][i] && state[2][i] == state[3][i])) {
                ok = true;
                break;
            }
        }
        if (ok == false) {
            if (state[0][0] == state[1][1] && state[1][1] == state[2][2]
                    && state[2][2] == state[3][3]) {
                ok = true;
            }
        }
        return ok;
    }

    /**
     * hash算法，将状态映射到数集上
     *
     * @param state 当前状态
     * @return
     */
    public static int getStateHash(int[][] state) {
        int sum = 0;
        int p = 15;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                sum += (int) (state[i][j] * Math.pow(3, p));
                p--;
            }
        }
        return sum;
    }
}
