package com.raylew.algorithm.book3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Raymond on 2016/10/30.
 */
public class TopoSort {
    private static String[] str = { "r", "s", "t", "u", "v", "w", "x", "y" };
    private static LinkedList[] adjacency_list;
    private static Vertex2[] vs;
    private static int time = 0;

    public static void main(String[] args) {
        adjacency_list = new LinkedList[8];
        vs = new Vertex2[8];
        for (int i = 0; i < adjacency_list.length; i++) {
            Vertex2 temp = new Vertex2(i);
            temp.setColor("WHITE");
            vs[i] = temp;
        }
        // 初始化邻接表
        LinkedList<Vertex2> list0 = new LinkedList<Vertex2>();
        list0.add(vs[1]);
        list0.add(vs[4]);
        adjacency_list[0] = list0;

        LinkedList<Vertex2> list1 = new LinkedList<Vertex2>();
        list1.add(vs[0]);
        list1.add(vs[5]);
        adjacency_list[1] = list1;

        LinkedList<Vertex2> list2 = new LinkedList<Vertex2>();
        list2.add(vs[3]);
        list2.add(vs[5]);
        list2.add(vs[6]);
        adjacency_list[2] = list2;

        LinkedList<Vertex2> list3 = new LinkedList<Vertex2>();
        list3.add(vs[2]);
        list3.add(vs[6]);
        list3.add(vs[7]);
        adjacency_list[3] = list3;

        LinkedList<Vertex2> list4 = new LinkedList<Vertex2>();
        list4.add(vs[0]);
        adjacency_list[4] = list4;

        LinkedList<Vertex2> list5 = new LinkedList<Vertex2>();
        list5.add(vs[1]);
        list5.add(vs[2]);
        list5.add(vs[6]);
        adjacency_list[5] = list5;

        LinkedList<Vertex2> list6 = new LinkedList<Vertex2>();
        list6.add(vs[2]);
        list6.add(vs[3]);
        list6.add(vs[5]);
        list6.add(vs[7]);
        adjacency_list[6] = list6;

        LinkedList<Vertex2> list7 = new LinkedList<Vertex2>();
        list7.add(vs[3]);
        list7.add(vs[6]);
        adjacency_list[7] = list7;
        // DFS
        System.out.println("DFS遍历序列");
        for (int i = 0; i < vs.length; i++) {
            Vertex2 u = vs[i];
            if (u.getColor().equals("WHITE")) {
                dfsVisit(u);
            }
        }
        System.out.println();
        System.out.println("拓扑排序序列");
        //拓扑序列即为深搜后按完成时间的倒序
        Arrays.sort(vs);
        for(int i=0;i<vs.length;i++){
            System.out.print(str[vs[i].getIndex()] + " ");
        }
        System.out.println();
    }

    public static void dfsVisit(Vertex2 u) {
        System.out.print(str[u.getIndex()] + "-->");
        time++;
        u.setD(time);
        u.setColor("GRAY");
        LinkedList<Vertex2> u_adj = adjacency_list[u.getIndex()];
        for (Iterator iterator = u_adj.iterator(); iterator.hasNext();) {
            Vertex2 v = (Vertex2) iterator.next();
            if (v.getColor().equals("WHITE")) {
                v.setPre(u.getIndex());
                dfsVisit(v);
            }
        }
        time++;
        u.setF(time);
        u.setColor("BLACK");
    }
}

class Vertex2 implements Comparable{
    private String color;// 颜色
    private Integer index;// 索引
    private Integer pre;// 前驱
    private Integer d;// 发现时间
    private Integer f;// 完成时间

    @Override
    public int compareTo(Object obj){
        Vertex2 vertex2=(Vertex2)obj;
        return vertex2.getF()-this.f;
    }

    public Vertex2(Integer index) {
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPre() {
        return pre;
    }

    public void setPre(Integer pre) {
        this.pre = pre;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

}
