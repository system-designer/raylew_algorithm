/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raylew.algorithm.book2;

/**
 *
 * @author Administrator
 */
public class Class_Kruskal {

    /**
     * @param args
     */
    private int[][] List_Graph;//图的邻接矩阵
    private int[] Label;
    private int[] Weight;
    private int[] Index_1;
    private int[] Index_2;
    private String Result;

    public Class_Kruskal()//使用构造函数进行初始化数据
    {
        List_Graph = new int[][]{{0, 1, 4, 5},
            {1, 0, 32768, 2},
            {4, 32768, 0, 3},
            {5, 2, 3, 0}
        };//初始化graphic中点和点的距离，32767表示距离无穷大
        //另外一个测试用例是
    /*
         {0,1,2,32767,32767},
         {1,0,2,4,3},
         {2,2,0,4,4},
         {32767,4,4,0,2},
         {32767,3,4,2,0}*/
        Label = new int[List_Graph.length];
        for (int i = 0; i < Label.length; i++)//初始化标记
        {
            Label[i] = i;
        }
        int j = (int) (Label.length + 1) * Label.length / 2;//这里应该是-1吧,完全图的边数
        Weight = new int[j];//用于存储待排序边的权值，数组长度m＝（n+1）*n*0.5,其中节点个数为n
        Index_1 = new int[j];//用于存储边的两个节点
        Index_2 = new int[j];

        Result = "最小生成树的边是：" + "\n";//记录最小生成树的边
    }

    public String Get_Result()//获得变量Result
    {
        return Result;
    }
    //把边按权排序,graphic是List_Graphic

    public int[] sort() {
        int[] a;
        int index = 0;
        for (int i = 0; i < Label.length; i++) {
            for (int j = i + 1; j < Label.length; j++) {
                if (List_Graph[i][j] < 32767) {
                    Weight[index] = List_Graph[i][j];
                    Index_1[index] = i;
                    Index_2[index] = j;
                    index = index + 1;
                }
            }
        }
        a = new int[index - 1];
        a = Address_Sort(Weight, Weight.length);
        return a;
    }

    public int[] Address_Sort(int[] a, int n)//地址排序
    {
        int[] Res = new int[n];
        for (int i = 0; i < n; i++) {
            Res[i] = i;
        }
        int t;
        int k;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < n - j - 1; i++) {
                if (a[i] >= a[i + 1]) {//冒泡法
                    k = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = k;

                    t = Res[i];
                    Res[i] = Res[i + 1];
                    Res[i + 1] = t;
                }
            }
        }
        return Res;
    }

    public void Min_Tree()//求最小生成树
    {

        int[] tag = new int[Weight.length];
        tag = sort();
        int i = 0;
        while (!Judge(Label))//Judge函数判断标记是否都是0
        {
            if (Label[Index_1[tag[i]]] != Label[Index_2[tag[i]]]) {//两个点不同
                Result = Result + Index_1[tag[i]] + "---" + Index_2[tag[i]] + "\n";
                if (Label[Index_1[tag[i]]] < Label[Index_2[tag[i]]]) {
                    for (int k = 0; k < Label.length; k++) {
                        if (Label[k] == Label[Index_2[tag[i]]]) {
                            Label[k] = Label[Index_1[tag[i]]];
                        }
                    }
                } else {
                    for (int k = 0; k < Label.length; k++) {
                        if (Label[k] == Label[Index_1[tag[i]]]) {
                            Label[k] = Label[Index_2[tag[i]]];
                        }
                    }
                }
            } else {
                i = i + 1;
            }
        }

    }

    public boolean Judge(int[] a)//判断标记是否都是0
    {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) //主函数
    {
        Class_Kruskal CK = new Class_Kruskal();
        CK.Min_Tree();
        System.out.println(CK.Get_Result());

    }
}
