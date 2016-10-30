package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/10/30.
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] a = {15, 23, 81, 99, 58, 42, 24, 37};
        int[] b=radixSort(a);
        for(int i=0;i<b.length;i++){
            System.out.print(b[i]+" ");
        }
    }

    public static int[] radixSort(int[] a) {
        int[] b = null;
        //99<128
        int w=7,d=1;
        for (int p = 0; p < w/d; p++) {
            int c[] = new int[1<<d];
            //the next three for loops implement counting-sort
            b = new int[a.length];
            for (int i = 0; i < a.length; i++)
                c[(a[i] >> d*p)&((1<<d)-1)]++;
            for (int i = 1; i < 1<<d; i++)
                c[i] += c[i-1];
            for (int i = a.length-1; i >= 0; i--)
                b[--c[(a[i] >> d*p)&((1<<d)-1)]] = a[i];
            a = b;
        }
        return b;
    }
}
