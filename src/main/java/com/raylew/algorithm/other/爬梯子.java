package com.raylew.algorithm.other;

import java.util.Scanner;

/**
 * Created by Raymond on 2016/12/28.
 */
public class 爬梯子 {
    public static void main(String[] args){
        爬梯子 tr=new 爬梯子();
        int step,num=0,moveNum=0;
        Scanner sc=new Scanner(System.in);
        step=sc.nextInt();
        moveNum=tr.up(step);
        System.out.print("递归法爬梯子的方法："+moveNum+"种");
        num=tr.up1(step);
        System.out.print("动态规划法爬梯子的方法："+num+"种");
    }
    //递归法
    int up(int n){
        if(n==1)
        return 1;
        else if(n==2)
            return 2;
        else return up(n-1)+up(n-2);
    }
    //动态规划法
    int up1(int n){
        int state[]=new int[100];
        int num1;
        if(state[n]>0)
            return state[n];
        if(n==1)
            num1=1;
        else if(n==2)
            num1=2;
        else
            num1=up1(n-1)+up1(n-2);
        state[n]=num1;
        return num1;
    }
}
