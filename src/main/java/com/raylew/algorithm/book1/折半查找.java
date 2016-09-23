package com.raylew.algorithm.book1;

public class 折半查找 {
    public static void main(String[] args) {
        int[] arr = {1, 6, 3, 7, 9, 8, 4, 0, 5, 2};
        insert(arr);
        System.out.println(search(9, arr));
    }

    //插入排序
    public static void insert(int[] arr) {
        if (arr != null) {
            for (int i = 1; i < arr.length; i++) {
                int temp = arr[i], j = i;
                if (arr[j - 1] > temp) {
                    while (j >= 1 && arr[j - 1] > temp) {
                        arr[j] = arr[j - 1];
                        j--;
                    }
                }
                arr[j] = temp;
            }
        } else {
            System.out.print("array hasn't been initialized!");
        }
    }

    public static int search(int num, int[] arr) {
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (num > arr[mid]) {
                left = mid + 1;
            } else if (num < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -(left + 1);
    }
}
