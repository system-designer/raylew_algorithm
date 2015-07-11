package com.raylew.algorithm.lanqiaocup;

/*
    奇怪的比赛
    某电视台举办了低碳生活大奖赛。题目的计分规则相当奇怪：
    每位选手需要回答10个问题（其编号为1到10），越后面越有难度。答对的，当前分数翻倍；答错了则扣掉与题号相同的分数（选手必须回答问题，不回答按错误处理）。
    每位选手都有一个起步的分数为10分。
    某获胜选手最终得分刚好是100分，如果不让你看比赛过程，你能推断出他（她）哪个题目答对了，哪个题目答错了吗？
    如果把答对的记为1，答错的记为0，则10个题目的回答情况可以用仅含有1和0的串来表示。例如：0010110011 就是可能的情况。
    你的任务是算出所有可能情况。每个答案占一行。
 */

/*
算法要点：
将所有题目的回答情况用10位二进制表示，用dfs来枚举所有的情况
 */
public class LanQiao12_2 {
    public static int N = 10;
    //答题情况
    public static boolean[] res = new boolean[N];

    public static void main(String[] args) {
        dfs(0);
    }

    /**
     * 深度优先：枚举所有的答题情况，并计算得分，输出总分为100分的答题情况
     *
     * @param cur 第cur道题
     */
    public static void dfs(int cur) {
        if (cur == 10) {
            int sum = 10;
            for (int i = 0; i < N; i++) {
                if (res[i]) {
                    sum = sum * 2;
                } else {
                    sum = sum - (i + 1);
                }
            }
            if (sum == 100) {
                for (int j = 0; j < N; j++) {
                    System.out.print(res[j] ? 1 : 0);
                }
                System.out.println();
            }
            return;
        }
        res[cur] = true;
        dfs(cur + 1);
        res[cur] = false;
        dfs(cur + 1);
    }
}
