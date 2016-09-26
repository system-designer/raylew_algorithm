package com.raylew.algorithm.book1;

public class 归并排序 {
    public static void main(String[] args) {
//        int[] arr = {1, 3, 2, 8, 5, 7, 4, 6, 9, 0};
        int[] arr = {8, 3, 2, 9, 7, 1, 5, 4};
        merge(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void merge(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge(arr, low, mid);
            merge(arr, mid + 1, high);
            mergeSort(arr, low, mid, high);
        }
    }

    //归并排序
    public static void mergeSort(int[] arr, int low, int mid, int high) {
        int[] tempArr = new int[arr.length];
        int i = low, j = mid + 1, index = low;
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                tempArr[index++] = arr[i++];
            } else {
                tempArr[index++] = arr[j++];
            }
        }
        //剩下的数拼接到末尾
        while (i <= mid) {
            tempArr[index++] = arr[i++];
        }
        while (j <= high) {
            tempArr[index++] = arr[j++];
        }
        //重新赋值到原数组
        for (int k = low; k <= high; k++) {
            arr[k] = tempArr[k];
        }
    }
}
