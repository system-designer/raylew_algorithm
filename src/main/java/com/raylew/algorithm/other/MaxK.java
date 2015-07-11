package com.raylew.algorithm.other;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/*
 在一组数中求最大的5个数，不需要将所有的数排序
 */
public class MaxK {
    /**
     * 求最大的5个数
     * @param lst
     * @return
     */
    public static List<Integer> max5(List<Integer> lst) {
        if (lst.size() <= 5)
            return lst;

        int a = lst.remove(lst.size() - 1);
        List<Integer> b = max5(lst);

        for (int i = 0; i < b.size(); i++) {
            int t = b.get(i);
            if (a > t) {
                lst.set(i, a);
                a = t;
            }
        }

        return b;
    }

    public static void main(String[] args) {
        List<Integer> lst = new Vector<Integer>();
        lst.addAll(Arrays.asList(12, 127, 85, 66, 27, 34, 15, 344, 156, 344,
                29, 47));
        System.out.println(max5(lst));
    }
}
