package com.raylew.algorithm.other;

/*
标题: 振兴中华
    小明参加了学校的趣味运动会，其中的一个项目是：跳格子。
    地上画着一些格子，每个格子里写一个字，如下所示：

从我做起振
我做起振兴
做起振兴中
起振兴中华

    比赛时，先站在左上角的写着“从”字的格子里，可以横向或纵向跳到相邻的格子里，但不能跳到对角的格子或其它位置。一直要跳到“华”字结束。
    要求跳过的路线刚好构成“从我做起振兴中华”这句话。
    请你帮助小明算一算他一共有多少种可能的跳跃路线呢？
 */

/*
算法要点：最直接的直接用组合数学计算出来，编程方面用dfs枚举所有的路径，注意剪枝，统计满足条件的路径数目
 */
public class 振兴中华 {
    public static String[][] arr = {{"从", "我", "做", "起", "振"},
            {"我", "做", "起", "振", "兴"}, {"做", "起", "振", "兴", "中"},
            {"起", "振", "兴", "中", "华"}};
    public static int count = 1;
    public static int sum = 0;

    public static void main(String[] args) {
        dfs(0, 0);
        System.out.println(sum);
    }

    public static void dfs(int i, int j) {
        if (count == 8) {
            if (arr[i][j].equals("��")) {
                sum++;
            }
            return;
        }
        if (count > 8) {
            return;
        }
        // 枚举所有情况
        count++;
        // up
        if (i > 0) {
            dfs(i - 1, j);
        }
        // down
        if (i < 3) {
            dfs(i + 1, j);
        }
        // left
        if (j > 0) {
            dfs(i, j - 1);
        }
        // right
        if (j < 4) {
            dfs(i, j + 1);
        }
        count--;
    }
}
