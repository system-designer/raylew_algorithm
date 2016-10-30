package com.raylew.algorithm.book3;

import java.util.LinkedList;

/**
 * 图的广度优先搜索
 */
public class BFS {
    //邻接链表
    private static LinkedList[] adjacency_list;
    //点集
    private static Vertex[] vs;
    //点的名称
    private static String[] str = {"r", "s", "t", "u", "v", "w", "x", "y"};
    private static int time = 0;

    public static void main(String[] args) {
        adjacency_list = new LinkedList[8];
        vs = new Vertex[8];
        for (int i = 0; i < adjacency_list.length; i++) {
            Vertex temp = new Vertex(i);
            vs[i] = temp;
        }
        // 初始化邻接表
        LinkedList<Vertex> list0 = new LinkedList<Vertex>();
        list0.add(vs[1]);
        list0.add(vs[4]);
        adjacency_list[0] = list0;

        LinkedList<Vertex> list1 = new LinkedList<Vertex>();
        list1.add(vs[0]);
        list1.add(vs[5]);
        adjacency_list[1] = list1;

        LinkedList<Vertex> list2 = new LinkedList<Vertex>();
        list2.add(vs[3]);
        list2.add(vs[5]);
        list2.add(vs[6]);
        adjacency_list[2] = list2;

        LinkedList<Vertex> list3 = new LinkedList<Vertex>();
        list3.add(vs[2]);
        list3.add(vs[6]);
        list3.add(vs[7]);
        adjacency_list[3] = list3;

        LinkedList<Vertex> list4 = new LinkedList<Vertex>();
        list4.add(vs[0]);
        adjacency_list[4] = list4;

        LinkedList<Vertex> list5 = new LinkedList<Vertex>();
        list5.add(vs[1]);
        list5.add(vs[2]);
        list5.add(vs[6]);
        adjacency_list[5] = list5;

        LinkedList<Vertex> list6 = new LinkedList<Vertex>();
        list6.add(vs[2]);
        list6.add(vs[3]);
        list6.add(vs[5]);
        list6.add(vs[7]);
        adjacency_list[6] = list6;

        LinkedList<Vertex> list7 = new LinkedList<Vertex>();
        list7.add(vs[3]);
        list7.add(vs[6]);
        adjacency_list[7] = list7;
        // BFS
        bfsGraph();
        // 打印广度优先树
        for (int i = 0; i < vs.length; i++) {
            System.out.print("s到" + str[i] + "的距离是" + vs[i].getD() + ",路径是:");
            printPath(vs[i]);
            System.out.println();
        }
    }

    private static void printPath(Vertex v) {
        if (v.getIndex() == vs[1].getIndex()) {
            System.out.print(str[vs[1].getIndex()]);
        } else if (v.getPre() == null) {
            System.out.println("no path from " + str[vs[1].getIndex()] + " to "
                    + str[v.getIndex()]);
        } else {
            printPath(vs[v.getPre()]);
            System.out.print(str[v.getIndex()]);
        }
    }

    public static void bfsGraph() {
        for (int i = 0; i < vs.length; i++) {
            vs[i].setColor("WHITE");
            vs[i].setD(0);
            vs[i].setPre(null);
        }
        vs[1].setColor("GRAY");
        LinkedList queue = new LinkedList();
        queue.add(vs[1]);
        while (!queue.isEmpty()) {
            Vertex u = (Vertex) queue.removeFirst();
            LinkedList u_adj = adjacency_list[u.getIndex()];
            for (int i = 0; i < u_adj.size(); i++) {
                Vertex v = (Vertex) u_adj.get(i);
                if (v.getColor().equals("WHITE")) {
                    v.setColor("GRAY");
                    v.setD(u.getD() + 1);
                    v.setPre(u.getIndex());
                    queue.addLast(v);
                }
            }
            u.setColor("BLACK");
        }
    }

}

/**
 * 顶点
 */
class Vertex {
    private String color;
    private Integer d;
    private Integer index;
    private Integer pre;

    public Vertex(Integer index) {
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
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

}