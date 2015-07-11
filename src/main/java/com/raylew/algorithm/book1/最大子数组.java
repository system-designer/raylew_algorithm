package com.raylew.algorithm.book1;

/*
求一个数组中和最大的连续子数组
 */
public class 最大子数组 {
    public static int[] arr = {13, -3, -25, -20, -3, -16, -23, 18, 20, -7, 12,
            -5, -22, 15, -4, 7};
    public static int max_left_index;
    public static int max_right_index;
    public static int left_sum;
    public static int right_sum;
    public static int cross_sum;

    public static void main(String[] args) {
        findMax(0, arr.length - 1);
        System.out.println(left_sum + "," + right_sum + "," + cross_sum);
        System.out.println(max_left_index + "," + max_right_index);
    }

    public static void findMax(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            findMax(low, mid);
            findMax(mid + 1, high);
            findCrossMax(low, mid, high);
        }
    }

    public static void findCrossMax(int low, int mid, int high) {
        left_sum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            if (left_sum < sum) {
                left_sum = sum;
                max_left_index = i;
            }
        }
        right_sum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            if (right_sum < sum) {
                right_sum = sum;
                max_right_index = i;
            }
        }
        cross_sum = left_sum + right_sum;
    }
}
