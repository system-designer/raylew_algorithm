package com.raylew.algorithm.book3.model;

/**
 * Created by Raymond on 2016/10/27.
 */
public class Point implements Comparable {
    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object obj) {
        Point point = (Point) obj;
        if (this.x > point.getX()) {
            return 1;
        } else if (this.x == point.getX()) {
            return 0;
        } else {
            return -1;
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
