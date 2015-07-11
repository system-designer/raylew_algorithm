package com.raylew.algorithm.book1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
