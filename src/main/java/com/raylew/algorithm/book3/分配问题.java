package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/9/27.
 */
public class 分配问题 {
    //任务
    public static int[] A=new int[4];
    //9,2,7,8分别表示任务1...任务4分配给第一个任的代价
    public static int[][] costMatrix={
            {9,2,7,8},
            {6,4,3,7},
            {5,8,1,8},
            {7,6,9,4}
    };
    public static int minCost=Integer.MAX_VALUE;

    /**
     * 以字典序生成全排列，初始4,0（深搜）
     */
    public static void dicorder_perm(int n, int cur) {
        if (cur == n) {
            int cost=0;
            for (int i = 0; i < A.length; i++) {
                //任务A[i]-1分配给i
                cost+=costMatrix[i][A[i]-1];
            }
            if(cost<minCost){
                minCost=cost;
            }
        } else {
            for (int i = 1; i <= n; i++) {// 尝试在cur位置放置i
                boolean ok = true;
                for (int j = 0; j < cur; j++) {// 判断i和之前的有没有重复
                    if (A[j] == i) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    A[cur] = i;
                    dicorder_perm(n, cur + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        dicorder_perm(4,0);
        System.out.println(minCost);
    }
}
