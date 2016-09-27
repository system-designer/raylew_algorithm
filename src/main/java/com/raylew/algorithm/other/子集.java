package com.raylew.algorithm.other;

/**
 * 求一个集合的幂集,递归求解
 */
public class 子集 {

    public static void main(String[] args) {
        int A[] = new int[4];
        print_subset1(A.length, A, 0);
        System.out.println("===========分割线============");
        print_subset2(A.length, A, 0);
        System.out.println("===========分割线============");
        print_subset3(A.length);
    }

    /**
     * 增量构造法
     *
     * @param n
     * @param A
     * @param cur
     */
    public static void print_subset1(int n, int[] A, int cur) {
        for (int i = 0; i < cur; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        int s;
        if (cur > 0) {
            s = A[cur - 1] + 1;
        } else {
            s = 0;
        }
        for (int i = s; i < n; i++) {
            A[cur] = i;
            print_subset1(n, A, cur + 1);
        }
    }

    /**
     * 位向量法
     *
     * @param n
     * @param B
     * @param cur
     */
    public static void print_subset2(int n, int[] B, int cur) {
        if (cur == n) {
            for (int i = 0; i < cur; i++) {
                if (B[i] == 1) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        } else {
            B[cur] = 1;
            print_subset2(n, B, cur + 1);
            B[cur] = 0;
            print_subset2(n, B, cur + 1);
        }
    }

    /**
     * 二进制法
     * @param n
     */
    public static void print_subset3(int n){
        int setCount=(int)Math.pow(2,n);
        for(int i=0;i<setCount;i++){
            String pattern=Integer.toBinaryString(i);
            if(pattern.length()<n){
                StringBuilder appendStr=new StringBuilder();
                for(int j=0;j<n-pattern.length();j++){
                    appendStr.append("0");
                }
                appendStr.append(pattern);
                pattern=appendStr.toString();
            }
            for (int j = 0; j < n; j++) {
                if (pattern.charAt(j) == '1') {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }

    }
}
