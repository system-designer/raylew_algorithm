package com.raylew.algorithm.book1;

/**
 * Created by Raymond on 2016/9/23.
 */
public class 快速排序 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 8, 5, 7, 4, 6, 9, 0};
        quick(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    /**
     * 快速排序
     */
    public static void quick(int[] a, int low, int high) {
        if (low < high) {
            // 每执行一次partition函数数组就按照pivot分成两块了
            int index = partition(a, low, high);
            quick(a, low, index - 1);
            quick(a, index + 1, high);
        }
    }

    // 分块方法，在数组a中，对下标从low到high的数列进行划分
    public static int partition(int[] a, int low, int high) {
        // 把比pivot(初始的pivot=a[low]小的数移动到pivot的左边
        int pivot = a[low];
        while (low < high) {
            // 把比pivot大的数移动到pivot的右边,先移动右边是因为左边的pivot位置空出来了
            while (low < high && a[high] >= pivot) {
                high--;
            }
            a[low] = a[high];
            // 把比pivot小的数移动到pivot的左边
            while (low < high && a[low] <= pivot) {
                low++;
            }
            a[high] = a[low];
        }
        // 此时low==high
        a[low] = pivot;
        // 返回划分后的pivot的位置
        return low;
    }
}
