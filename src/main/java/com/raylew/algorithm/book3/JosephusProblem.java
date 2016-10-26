package com.raylew.algorithm.book3;
import java.util.Scanner;

/**
 * Created by Raymond on 2016/10/26.
 */
/**
 *使用数组实现约瑟夫环问题
 *由m个人围成一个首尾相连的圈报数。
 *从第一个人开始，从1开始报数，报到n的人出圈，
 *剩下的人继续从1开始报数，直到所有的人都出圈为止。
 *对于给定的m和n，求出所有人的出圈顺序.
 */
public class JosephusProblem {
    public static void main(String[] args){
        System.out.println("程序说明如下：");
        System.out.println("由m个人围成一个首尾相连的圈报数。从第一个人开始，从1开始报数，报到n的人出圈，剩下的人继续从1开始报数，直到所有的人都出圈为止。对于给定的m和n，求出所有人的出圈顺序.");

        //提示输入总人数
        System.out.println("请输入做这个游戏的总人数：");
        Scanner sca=new Scanner(System.in);
        int m=sca.nextInt();
        //提示输入要出圈的数值
        System.out.println("请输入要出圈的数值：");
        int n=sca.nextInt();
        System.out.println("按出圈的次序输出序号：");
        //创建有m个值的数组
        int[] a=new int[m];
        //初始长度，以后出圈一个，长度就减一
        int len=m;
        //给数组赋值
        for(int i=0;i<a.length;i++)
            a[i]=i+1;
        //i为元素下标，j代表当前要报的数
        int i=0;
        int j=1;
        while(len>0){
            if(a[i%m]>0){
                if(j%n==0){//找到要出圈的人，并把圈中人数减一
                    System.out.print(a[i%m]+"  ");
                    a[i%m]=-1;
                    j=1;
                    i++;
                    len--;
                }else{
                    i++;
                    j++;
                }
            }else{//遇到空位了，就跳到下一位，但j不加一，也就是这个位置没有报数
                i++;
            }
        }
    }
}
