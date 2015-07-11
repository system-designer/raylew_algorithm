package com.raylew.algorithm.book1;

public class 用快排求第k小的数 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 9, 8, 1, 7, 5, 6, 2, 0};
        int num = findK(arr, 0, arr.length - 1, 2);
        System.out.println(num);
    }

    /**
     * 求第K小的数
     * @param a 数组
     * @param low 快排算法中的low点
     * @param high 快排算法中的high点
     * @param k 第k小的数
     * @return
     */
    public static int findK(int[] a, int low, int high, int k) {
        int q = partition(a, low, high);
        int pos = q - low + 1;
        if (low == high)
            return a[low];
        if (k == pos)
            return a[q];
        else if (k < pos)
            return findK(a, low, q - 1, k);
        else
            return findK(a, q + 1, high, k - pos);
    }

    public static void quick(int[] a, int low, int high) {
        if (low < high) {
            int index = partition(a, low, high);
            quick(a, low, index - 1);
            quick(a, index + 1, high);
        }
    }

    public static int partition(int[] a, int low, int high) {
        int pivot = a[low];
        while (low < high) {
            while (low < high && a[high] >= pivot) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= pivot) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }
}
