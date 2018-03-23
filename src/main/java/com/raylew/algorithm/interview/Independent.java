package com.raylew.algorithm.interview;

import java.util.Scanner;

/**
 * Created by Raymond on 2017/8/12.
 */
public class Independent {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        //住宿
        int x=scanner.nextInt();
        //剩下的水果
        int f=scanner.nextInt();
        //剩下的钱
        int d=scanner.nextInt();
        //水果单价
        int p=scanner.nextInt();
        int result =(d+f*p)/(x+p);
        if(f<=result){
            System.out.println(result);
        }else{
            System.out.println(d/x);
        }
    }
}
