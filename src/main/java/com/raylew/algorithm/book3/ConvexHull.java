package com.raylew.algorithm.book3;

import com.raylew.algorithm.book3.model.Point;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Raymond on 2016/10/27.
 * 凸包问题
 */
public class ConvexHull {
    public static void main(String[] args) {
        Point[] points = new Point[15];

        points[0] = new Point(-5, 7);
        points[1] = new Point(3, -6);
        points[2] = new Point(5, 4);
        points[3] = new Point(-5, -5);
        points[4] = new Point(1, 7);
        points[5] = new Point(6, 0);

        points[6] = new Point(0, 0);
        points[7] = new Point(-5, 0);
        points[8] = new Point(3, -2);
        points[9] = new Point(3, 4);

        points[10] = new Point(1, 6);
        points[11] = new Point(5, 3);
        points[12] = new Point(-4, -5);
        points[13] = new Point(-3, 6);
        points[14] = new Point(2, 5);

        //预排序处理
        Arrays.sort(points);

        LinkedList<Point> list = new LinkedList<Point>();
        //list存放全部的顶点
        for (int i = 0; i < points.length; i++)
            list.add(points[i]);
        //result用来存放最终的结果顶点
        List<Point> result = getConvexHulls(list);
        System.out.println("一共有 " + result.size() + " 个顶点, " + "凸包的顶点是： ");
        Iterator it = result.iterator();
        while (it.hasNext()) {
            Point next = (Point) it.next();
            System.out.print("(" + next.getX() + "," + next.getY() + ")" + "  ");
        }
    }

    /**
     * 求凸包定点集
     * @param list
     * @return
     */
    public static List<Point> getConvexHulls(LinkedList<Point> list) {
        //将凸包顶点以result链表返回
        List<Point> result = new LinkedList<Point>();

        Point temp1 = list.removeFirst();
        Point temp2 = list.removeLast();
        result.add(temp1);
        result.add(temp2);

        //递归的处理temp1 ---> temp2左右两侧的点
        dealWithLeft(temp1, temp2, result, list);
        //注意每次要将result带着，存放结果集
        dealWithLeft(temp2, temp1, result, list);

        return result;
    }

    private static void dealWithLeft(Point p1, Point p2, List result, List list) {
        //递归的处理p1，p2构成的射线左边的点
        Iterator it = list.iterator();

        //找出左边最高的点pMax
        Point pMax = null;
        double max = 0;
        while (it.hasNext()) {
            Point next = (Point) it.next();
            double x1 = p1.getX(), y1 = p1.getY();
            double x2 = p2.getX(), y2 = p2.getY();
            double x3 = next.getX(), y3 = next.getY();

            double compute = x1 * y2 + x3 * y1 + x2 * y3 - x3 * y2 - x2 * y1 - x1 * y3;
            if (compute > max) {
                max = compute;
                pMax = next;
            }
        }

        //又找到了一个顶点
        if (pMax != null) {
            result.add(pMax);
            list.remove(pMax);

            //递归
            dealWithLeft(p1, pMax, result, list);
            dealWithLeft(pMax, p2, result, list);
        }
    }

    /**
     * 判断target是否在p1和p2直线的左边
     * @param target
     * @param p1
     * @param p2
     * @return
     */
    private static boolean onLeft(Point target, Point p1, Point p2) {
        //判断target是否在p1--->p2射线的左侧
        double x1 = p1.getX(), y1 = p1.getY();
        double x2 = p2.getX(), y2 = p2.getY();
        double x3 = target.getX(), y3 = target.getY();

        double compute = x1 * y2 + x3 * y1 + x2 * y3 - x3 * y2 - x2 * y1 - x1 * y3;
        if (compute > 0)
            return true;
        else
            return false;
    }
}
