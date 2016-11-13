package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/9/27.
 * 蛮力法解背包问题
 */
public class 背包问题 {
    //背包能承受的最大重量
    public static int maxWeight=7;
    //物品的价值
    public static int[] values={5,7,15,9};
    //物品的重量
    public static int[] weights={1,2,3,4};

    /**
     * 二进制法求背包问题
     * @param n
     */
    public static int getMaxValue(int n){
        int maxValue=Integer.MIN_VALUE;
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
            int weight=0;
            int value=0;
            for (int j = 0; j < n; j++) {
                if (pattern.charAt(j) == '1') {
                    weight+=weights[j];
                    value+=values[j];
                }
            }
            if(weight<=maxWeight&&value>maxValue){
                maxValue=value;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int maxValue=getMaxValue(4);
        System.out.println(maxValue);
    }
}
