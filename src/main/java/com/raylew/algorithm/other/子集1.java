/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raylew.algorithm.other;

/**
 * 求一个集合的幂集,递归求解
 */
public class 子集1 {

    public static int[] set = {1, 2, 3, 4};
    public static boolean[] isOut = {false, false, false, false};

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            getSet(i);
        }
    }

    /**
     * 递归
     */
    public static void getSet(int index) {
        printElem();
        isOut[index] = true;
        for (int i = 0; i < index; i++) {
            isOut[i] = false;
        }
        for (int j = 0; j < index; j++) {
            getSet(j);
        }
        if (index == 3) {
            printElem();
        }
    }

    public static void printElem() {
        for (int i = 0; i < set.length; i++) {
            if (isOut[i]) {
                System.out.print(set[i] + ",");
            }
        }
        System.out.println("");
    }

    /**
     * 非递归1，二进制法
     */
    public static void perm() {
        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 4; j++) {
//                int temp = 1 << j;
//                int bin = temp & i;
//                System.out.print(bin);
//            }
//            System.out.println("");
            System.out.println(Integer.toBinaryString(i));
        }
    }

    /**
     * 非递归2
     */
    public static void getSet2() {
        int[] set = {1, 2, 3, 4};
        boolean[] isOut = {false, false, false, false};
        int elemNum = 1;
        int i = 0;
        while (elemNum < 4) {
            while (i < 4) {
                System.out.println(i);
                i++;
            }
        }
    }
}
