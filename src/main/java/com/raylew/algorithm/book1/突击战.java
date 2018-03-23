package com.raylew.algorithm.book1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 你有n个部下，每个部下需要完成一项任务。第i个部下需要你花Bi分钟交待任务，然后他会立刻独立地、无间断地执行Ji分钟后完成任务。
 * 你需要选择交待任务的顺序，使得所有任务尽早执行完毕（即最后一个执行完的任务应尽早结束）。
 * 注意，不能同时给两个部下交待任务，但部下们可以同时执行他们各自的任务。
 */
public class 突击战 {
    static class Data implements Comparable {
        int x, y;

        public Data(int i, int j) {
            x = i;
            y = j;
        }

        public int compareTo(Object o) {
            Data d = (Data) o;
            return d.y - y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        ArrayList<Data> list = new ArrayList<Data>();
        for (int i = 0; i < m; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            Data d = new Data(x, y);
            list.add(d);
        }
        Collections.sort(list);
        int t1 = 0, t2 = 0;
        for (int i = 0; i < list.size(); i++) {
            Data d = list.get(i);
            if (i == 0) {
                t1 = d.x;
                t2 = d.y + t1;
                continue;
            }
            if (t1 + d.x + d.y < t2) {
                t1 += d.x;
            } else {
                t1 += d.x;
                t2 = d.y + t1;
            }
        }
        System.out.println(t2);
    }
}
