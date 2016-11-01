package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/11/1.
 */
public class TurnCake {
    public static void main(String[] args) {
        //下标0是底部
        int[] cakes={1,4,9,5,8,2,3};
        int max=Integer.MIN_VALUE,maxIndex=-1;
        for(int i=0;i<cakes.length;i++){
            if(cakes[i]>max){
                max=cakes[i];
                maxIndex=i;
            }
        }
        //翻转最大的到顶部
        for(int i=maxIndex;i<(maxIndex+cakes.length-1)/2;i++){
            int temp=cakes[i];
            cakes[i]=cakes[cakes.length-1+maxIndex-i];
            cakes[cakes.length-1+maxIndex-i]=temp;
        }
        System.out.println("翻转最大的到顶部");
        for(int i=0;i<cakes.length;i++){
            System.out.print(cakes[i] + " ");
        }
        System.out.println();
        //翻转最大的到底部
        for(int i=0;i<(cakes.length-1)/2;i++){
            int temp=cakes[i];
            cakes[i]=cakes[cakes.length-1-i];
            cakes[cakes.length-1-i]=temp;
        }
        System.out.println("翻转最大的到底部");
        for(int i=0;i<cakes.length;i++){
            System.out.print(cakes[i] + " ");
        }
        System.out.println();
    }
}
