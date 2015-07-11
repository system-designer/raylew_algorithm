package com.raylew.algorithm.book1;

import java.util.Arrays;
import java.util.LinkedList;

/*
八数码问题
八数码问题也称为九宫问题。在3×3的棋盘，摆有八个棋子，每个棋子上标有1至8的某一数字，不同棋子上标的数字不相同。
棋盘上还有一个空格，与空格相邻的棋子可以移到空格中。要求解决的问题是：给出一个初始状态和一个目标状态，找出一种从初始转变成目标状态的移动棋子步数最少的移动步骤。
所谓问题的一个状态就是棋子在棋盘上的一种摆法。棋子移动后，状态就会发生改变。
解八数码问题实际上就是找出从初始状态到达目标状态所经过的一系列中间过渡状态。
 */

/*
算法要点：隐式图上的bfs
 */
public class 八数码问题 {
    //用于状态到0~9!中数字的hash算法
    public static int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320};
    //标注哪些状态已经访问，判重
    public static boolean[] stateArr = new boolean[362879];
    //记录初始状态到某一状态的距离
    public static int[] distArr = new int[362879];
    //记录初始状态到某一状态的路径
    public static String[] pathArr = new String[362879];

    public static void main(String[] args) {
        //用长度为9的一维数组表示状态
        int[] beginState = {2, 3, 4, 1, 5, 0, 7, 6, 8};
        int[] endState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        //bfs算法初始化
        LinkedList<int[]> state_list = new LinkedList<int[]>();
        state_list.add(beginState);
        stateArr[state_hash(beginState)] = true;
        int times = 0;
        //bfs算法开始
        while (state_list.size() > 0) {
            int[] state = state_list.removeFirst();
            if (success(state, endState)) {
                System.out.println(distArr[state_hash(endState)]);
                System.out.println(pathArr[state_hash(endState)].replaceAll(
                        "null", ""));
                break;
            }
            //计算初始状态beginState中空的位置
            int index_0 = getIndex_0(state);
            int row_0 = index_0 / 3;
            int col_0 = index_0 % 3;
            // 一个数字分别沿上下左右四个方向移动，一个状态可能产生四个不同的状态
            // 0 up
            if (row_0 > 0) {
                int new_index_0 = (row_0 - 1) * 3 + col_0;
                int[] newState = Arrays.copyOf(state, state.length);
                newState[index_0] = newState[new_index_0];
                newState[new_index_0] = 0;
                if (stateArr[state_hash(newState)] == false) {
                    state_list.addLast(newState);
                    stateArr[state_hash(newState)] = true;
                    pathArr[state_hash(newState)] = pathArr[state_hash(state)]
                            + "u";
                    distArr[state_hash(newState)] = distArr[state_hash(state)] + 1;
                }
            }
            // 0 down
            if (row_0 < 2) {
                int new_index_0 = (row_0 + 1) * 3 + col_0;
                int[] newState = Arrays.copyOf(state, state.length);
                newState[index_0] = newState[new_index_0];
                newState[new_index_0] = 0;
                if (stateArr[state_hash(newState)] == false) {
                    state_list.addLast(newState);
                    stateArr[state_hash(newState)] = true;
                    pathArr[state_hash(newState)] = pathArr[state_hash(state)]
                            + "d";
                    distArr[state_hash(newState)] = distArr[state_hash(state)] + 1;
                }
            }
            // 0 left
            if (col_0 > 0) {
                int new_index_0 = row_0 * 3 + (col_0 - 1);
                int[] newState = Arrays.copyOf(state, state.length);
                newState[index_0] = newState[new_index_0];
                newState[new_index_0] = 0;
                if (stateArr[state_hash(newState)] == false) {
                    state_list.addLast(newState);
                    stateArr[state_hash(newState)] = true;
                    pathArr[state_hash(newState)] = pathArr[state_hash(state)]
                            + "l";
                    distArr[state_hash(newState)] = distArr[state_hash(state)] + 1;
                }
            }
            // 0 right
            if (col_0 < 2) {
                int new_index_0 = row_0 * 3 + (col_0 + 1);
                int[] newState = Arrays.copyOf(state, state.length);
                newState[index_0] = newState[new_index_0];
                newState[new_index_0] = 0;
                if (stateArr[state_hash(newState)] == false) {
                    state_list.addLast(newState);
                    stateArr[state_hash(newState)] = true;
                    pathArr[state_hash(newState)] = pathArr[state_hash(state)]
                            + "r";
                    distArr[state_hash(newState)] = distArr[state_hash(state)] + 1;
                }
            }
        }
    }

    /**
     * 一种hash算法，将不同的状态映射到0~9!中，节省空间
     *
     * @param state 当前状态
     * @return
     */
    public static int state_hash(int[] state) {
        int key = 0;
        for (int i = 0; i < state.length; i++) {
            int cnt = 0;
            for (int j = i + 1; j < state.length; j++) {
                if (state[i] > state[j]) {
                    cnt++;
                }
            }
            key = key + factorial[8 - i] * cnt;
        }
        return key;
    }

    /**
     * 判断当前状态与最终状态是否相同
     *
     * @param state    当前状态
     * @param endState 最终状态
     * @return
     */
    public static boolean success(int[] state, int[] endState) {
        return Arrays.equals(state, endState);
    }

    /**
     * 得到八数码中空位
     *
     * @param state 当前状态
     * @return
     */
    public static int getIndex_0(int[] state) {
        int index_0 = -1;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                index_0 = i;
                break;
            }
        }
        return index_0;
    }
}
