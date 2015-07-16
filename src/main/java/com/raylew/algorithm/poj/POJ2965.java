package com.raylew.algorithm.poj;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by RayLew on 2015/7/10.
 * QQ:897929321
 * from:poj2965
 * algorithm:dfs,enum
 */

/*
Sample Input
- + - -
- - - -
- - - -
- + - -
Sample Output
6
1 1
1 3
1 4
4 1
4 3
4 4
 */

public class POJ2965 {
    boolean[] state = new boolean[65536];//状态
    int N = 4;
    int minimumCount = 0;

    public static void main(String[] args) {
        int N = 4;
        int[][] matrix = new int[N][N];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String str = scanner.next();
                if (str.equals("+")) {
                    matrix[i][j] = 1;
                }
            }
        }
        POJ2965 poj2965 = new POJ2965();
        poj2965.bfs(matrix);
    }

    /**
     * enum all possible situation
     */
    public void bfs(int[][] matrix) {
        //init
        LinkedList<String> list1 = new LinkedList<String>();
        state[convertMatrixToNum(matrix)] = true;
        list1.add(convertMatrixToString(matrix));
        LinkedList<String> list2 = new LinkedList<String>();
        //start bfs
        while (true) {
            if (list1.size() == 0) {
                if (list2.size() == 0) {
                    System.out.println("Impossible");
                    break;
                }
                minimumCount++;
                for (String item : list2) {
                    list1.add(item);
                }
                list2 = new LinkedList<String>();
            }
            int[][] first_matrix = convertStringToMatrix(list1.removeFirst());
            state[convertMatrixToNum(first_matrix)] = true;
            if (isMatched(first_matrix)) {//matched
                System.out.println(minimumCount);
                //display(first_matrix);
                return;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int[][] new_matrix = flip(first_matrix, i, j);
                    if (isMatched(new_matrix)) {//matched
                        System.out.println(minimumCount + 1);
                        //display(new_matrix);
                        return;
                    } else {
                        //unmatched
                        int hash = convertMatrixToNum(new_matrix);
                        if (!state[hash]) {
                            String matrixStr = convertMatrixToString(new_matrix);
                            list2.add(matrixStr);
                        }
                    }
                }
            }
        }
    }

    /**
     * flip the i row and j column into the opposite
     *
     * @param matrix
     * @param i
     * @param j
     */
    public int[][] flip(int[][] matrix, int i, int j) {
        //flip i row
        for (int k = 0; k < N; k++) {
            matrix[i][k] = 1 - matrix[i][k];
        }
        //flip j column
        for (int k = 0; k < N; k++) {
            matrix[k][j] = 1 - matrix[k][j];
        }
        return matrix;
    }

    /**
     * judge whether the new matrix is 0
     *
     * @param matrix
     * @return
     */
    public boolean isMatched(int[][] matrix) {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                num += matrix[i][j];
            }
        }
        //all of them is 0
        return (num == 0);
    }

    /**
     * convert matrix to a number(exactly hash value)
     *
     * @param matrix
     * @return
     */
    public int convertMatrixToNum(int[][] matrix) {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int radix = N * N - 1 - (i * N + j);
                num += matrix[i][j] * Math.pow(2, radix);
            }
        }
        return num;
    }

    /**
     * convert matrix to string
     *
     * @param matrix
     * @return
     */
    public String convertMatrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(matrix[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * convert string to matrix
     *
     * @param str
     * @return
     */
    public int[][] convertStringToMatrix(String str) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int index = i * N + j;
                matrix[i][j] = Integer.parseInt(str.charAt(index) + "");
            }
        }
        return matrix;
    }

    /**
     * print matrix to console
     *
     * @param matrix
     */
    public void display(int[][] matrix) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
