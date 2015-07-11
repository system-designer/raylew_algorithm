package com.raylew.algorithm.book1;

import java.util.Iterator;
import java.util.LinkedList;

/*
 三个水杯
描述
    给出三个水杯，大小不一，并且只有最大的水杯的水是装满的，其余两个为空杯子。
    三个水杯之间相互倒水，并且水杯没有标识，只能根据给出的水杯体积来计算。现在要求你写出一个程序，使其输出使初始状态到达目标状态的最少次数。　　
输入
    第一行一个整数N(0<N<50)表示N组测试数据
    接下来每组测试数据有两行，第一行给出三个整数V1 V2 V3 (V1>V2>V3 V1<100 V3>0)表示三个水杯的体积。
    第二行给出三个整数E1 E2 E3 （体积小于等于相应水杯体积）表示我们需要的最终状态
输出
    每行输出相应测试数据最少的倒水次数。如果达不到目标状态输出-1
样例输入
    　　2 6 3 1 4 1 1 9 3 2 7 1 1
样例输出
    　　3 -1
 */
public class 倒水问题 {
    //测试数据：三个水杯的大小
    public static int a = 6, b = 3, c = 1;

    public static void main(String[] args) {
        //bfs算法初始化
        LinkedList<State> list_backup = new LinkedList<State>();
        LinkedList<State> list = new LinkedList<State>();
        //测试数据：初始化
        State State = new State(a, 0, 0);
        list.add(State);
        int count = 0;
        // bfs算法开始
        while (list.size() > 0) {
            State ts = list.removeFirst();
            int ta = ts.getBig();
            int tb = ts.getMid();
            int tc = ts.getLittle();
            if (ta == 4) {//最终目标为4
                count++;
            }
            //计算当前状态可能产生的所有下一状态，总数等于排列组合中A(3,2)=6
            if (ta > 0 && tb < b) {
                State add1 = new State(ta - (b - tb), b, tc);
                if (!judge(list_backup, add1)) {
                    list.addLast(add1);
                }
            }
            if (ta > 0 && tc < c) {
                State add2 = new State(ta - (c - tc), tb, c);
                if (!judge(list_backup, add2)) {
                    list.addLast(add2);
                }
            }
            if (tb > 0 && tc < c) {
                State add3 = new State(ta, tb - (c - tc), c);
                if (!judge(list_backup, add3)) {
                    list.addLast(add3);
                }
            }
            if (tb > 0 && ta < a) {
                State add4 = new State(ta + tb, 0, tc);
                if (!judge(list_backup, add4)) {
                    list.addLast(add4);
                }
            }
            if (tc > 0 && ta < a) {
                State add5 = new State(ta + tc, tb, 0);
                if (!judge(list_backup, add5)) {
                    list.addLast(add5);
                }
            }
            if (tc > 0 && tb < b) {
                State add6 = null;
                if (tc <= (b - tb)) {
                    add6 = new State(ta, tb + tc, 0);
                } else {
                    add6 = new State(ta, b, tc - (b - tb));
                }
                if (!judge(list_backup, add6)) {
                    list.addLast(add6);
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 判断当前状态是否访问过
     *
     * @param list 存放已经访问的状态
     * @param add  当前状态
     * @return
     */
    public static boolean judge(LinkedList<State> list, State add) {
        boolean vis = false;// 没有访问
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            State comp = (State) iterator.next();
            if (comp.getBig() == add.getBig() && comp.getMid() == add.getMid()
                    && comp.getLittle() == add.getLittle()) {
                vis = true;
                break;
            }
        }
        if (!vis) {
            list.add(add);
        }
        return vis;
    }
}

/**
 * 将三个水桶中水的数量抽象为一个类
 */
class State {
    //大桶中水的数目
    private Integer big;
    //中桶中水的数目
    private Integer mid;
    //小桶中水的数目
    private Integer little;

    public State() {

    }

    public State(Integer big, Integer mid, Integer little) {
        super();
        this.big = big;
        this.mid = mid;
        this.little = little;
    }

    public Integer getBig() {
        return big;
    }

    public void setBig(Integer big) {
        this.big = big;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getLittle() {
        return little;
    }

    public void setLittle(Integer little) {
        this.little = little;
    }

}