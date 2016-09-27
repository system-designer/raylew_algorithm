package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/9/27.
 */
public class 旅行商问题 {
    public static int[] A=new int[4+1];
    public static int[][] distanceMatrix={
            {0,2,5,7},
            {2,0,8,3},
            {5,8,0,1},
            {7,3,1,0}
    };
    public static int minDistance=Integer.MAX_VALUE;
    /**
     * 以字典序生成全排列，初始4,0（深搜）
     */
    public static void dicorder_perm(int n, int cur,int start) {
        if (cur == n) {
            A[A.length-1]=start;
            int distance=0;
            for (int i = 1; i < A.length; i++) {
                distance+=distanceMatrix[A[i-1]-1][A[i]-1];
            }
            if(distance<minDistance){
                minDistance=distance;
            }
        } else {
            for (int i = 1; i <= n; i++) {// 尝试在cur位置放置i
                if(i!=start) {
                    boolean ok = true;
                    for (int j = 0; j < cur; j++) {// 判断i和之前的有没有重复
                        if (A[j] == i) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        A[cur] = i;
                        dicorder_perm(n, cur + 1, start);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int start=2;
        A[0]=start;
        dicorder_perm(4,1,start);
        System.out.println(minDistance);
    }
}
