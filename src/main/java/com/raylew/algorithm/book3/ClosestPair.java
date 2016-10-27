package com.raylew.algorithm.book3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Raymond on 2016/10/27.
 * 最近对问题
 */
public class ClosestPair {
    //点集
    private static Point[] points;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 0)
            return;
        points = new Point[n];
        for (int i = 0; i < n; i++) {
            Point point = new Point();
            point.setX(scanner.nextDouble());
            point.setY(scanner.nextDouble());
            points[i] = point;
        }
        Arrays.sort(points);
        double minDistance = getClosestPair(0, points.length - 1);
        System.out.println(minDistance);
    }

    /**
     * 最近对
     *
     * @param left
     * @param right
     * @return
     */
    public static double getClosestPair(int left, int right) {
        if (left == right)
            return Double.MAX_VALUE;
        if (left + 1 == right)
            return dis(left, right);
        int mid = (left + right) / 2;
        double d1 = getClosestPair(left, mid);
        double d2 = getClosestPair(mid + 1, right);
        double d = d1 < d2 ? d1 : d2;
        int i, j, k = 0;
        //存放需要计算距离的点的下标
        int[] tempIndexArr = new int[right - left + 1];
        //分离区间(mid_x-d,mid_x+d)的点
        for (i = left; i <= right; i++) {
            if (Math.abs(points[mid].getX() - points[i].getX()) <= d)
                tempIndexArr[k++] = i;
        }
        //排序
        Arrays.sort(tempIndexArr);
        //计算区间内部点的距离
        for (i = 0; i < k; i++) {
            for (j = i + 1; j < k && (points[tempIndexArr[j]].getY() - points[tempIndexArr[i]].getY() < d); j++) {
                double d3 = dis(tempIndexArr[i], tempIndexArr[j]);
                if (d > d3)
                    d = d3;
            }
        }
        return d;
    }

    /**
     * 连点距离
     *
     * @param i 在points中的坐标
     * @param j 在points中的坐标
     * @return
     */
    public static double dis(int i, int j) {
        return Math.sqrt((points[i].getX() - points[j].getX()) * (points[i].getX() - points[j].getX())
                + (points[i].getY() - points[j].getY()) * (points[i].getY() - points[j].getY()));
    }
}

class Point implements Comparable {
    private double x;
    private double y;

    @Override
    public int compareTo(Object obj) {
        Point point = (Point) obj;
        if (this.x > point.getX()) {
            return 1;
        } else {
            return 0;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

