package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/10/30.
 * 计数排序
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] a={13,11,12,13,12,12};
        System.out.println("比较计数排序");
        int[] b1=comparingSort(a);
        for(int i=0;i<b1.length;i++){
            System.out.print(b1[i]+" ");
        }
        System.out.println();
        System.out.println("分布计数排序");
        int[] b2=countingSort(a);
        for(int i=0;i<b2.length;i++){
            System.out.print(b2[i]+" ");
        }
    }

    /**
     * 比较计数排序
     * @param a 原数组
     * @return
     */
    public static int[] comparingSort(int[] a){
        int length=a.length;
        int[] countArr=new int[length];
        for(int i=0;i<length;i++){
            countArr[i]=0;
        }
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j<length;j++){
                if(a[i]<a[j]){
                    countArr[j]=countArr[j]+1;
                }else{
                    countArr[i]=countArr[i]+1;
                }
            }
        }
        int[] b=new int[length];
        for(int i=0;i<length;i++){
            b[countArr[i]]=a[i];
        }
        return b;
    }

    /**
     * 分布计数排序
     * @param a 原数组
     * @return
     */
    public static int[] countingSort(int[] a) {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++){
            if(a[i]>max){
                max=a[i];
            }
        }
        int k=max+1;
        int c[] = new int[k];
        //计算频率值
        for (int i = 0; i < a.length; i++)
            c[a[i]]++;
        //重用于分布
        for (int i = 1; i < k; i++)
            c[i] += c[i-1];
        int b[] = new int[a.length];
        for (int i = a.length-1; i >= 0; i--)
            b[--c[a[i]]] = a[i];
        return b;
    }
}
