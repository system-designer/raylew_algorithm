package com.raylew.algorithm.book1;

import java.util.Arrays;

public class 最大兼容活动集 {
    public static void main(String[] args) {
        int[] starts = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] ends = {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        Activity[] acts = new Activity[11];
        for (int i = 0; i < 11; i++) {
            Activity act = new Activity(i + 1, starts[i], ends[i]);
            acts[i] = act;
        }
        Arrays.sort(acts);
        String res = acts[0].getIndex() + "";
        int k = 0;
        for (int i = 0; i < 11; i++) {
            if (acts[i].getStart() > acts[k].getEnd()) {
                k = i;
                res += ("," + acts[i].getIndex());
            }
        }
        System.out.println(res);
    }
}

class Activity implements Comparable {
    private Integer index;
    private Integer start;
    private Integer end;

    public Activity(Integer index, Integer start, Integer end) {
        super();
        this.index = index;
        this.start = start;
        this.end = end;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public int compareTo(Object o) {
        Activity act = (Activity) o;
        return this.end - act.end;
    }

}
