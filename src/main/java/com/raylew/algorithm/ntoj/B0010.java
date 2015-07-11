package com.raylew.algorithm.ntoj;

/*
小王是通中的学生，打算参加物理竞赛，于是计划在寒假里强化一下物理竞赛习题。
他从图书馆借了一套物理竞赛习题集。他决定在以后的n天里要做完上面的S道习题。
为了能够尽快完成任务，小王强迫自己第i天至少要做a[i]道题。
但是，小王在假期里还有其他事情（例如过春节），每天的题量不能太多，他估计了一下，第i天做的题不能超过b[i]（b[i]>=a[i]）道。
现在小王想知道，究竟能有多少种方案能够在n天里做完这S道物理竞赛习题呢？小王请你写个程序帮他算一算。
具体来说，一个方案就是每天做的题的数目的序列，假设第i天完成x[i]道题（x[i]当然满足a[i]<=i<=b[i]，且x[1]+x[2]+......+x[n]=S）。
那么向量（x[1]，x[2]，...，x[n]）就对应了一个方案。两个方案是指他们对应的向量不同。
一共n+1行，第一行是两个整数n和S，用空格分开，分别表示天数和题目数（1<=n<=20，1<=S<=1000）；
接下来n行每行两个整数，之间用空格隔开，分别表示第i天对做题数量的限制a[i]和b[i]（0<=a[i]<=b[i]<=S）。
一个整数，表示满足条件的方案数T。
样例输入
3 11
2 5
1 6
3 4
样例输出
8
 */
public class B0010 {
    static int[] a = {2, 1, 3};
    static int[] b = {5, 6, 4};
    static int count = 0;
    static int n;
    static int sum = 0;
    static boolean ok = false;

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
		int days = scanner.nextInt();
		int total = scanner.nextInt();
		n = total;
		a = new int[days];
		b = new int[days];
		int[] c = new int[days];
		for (int i = 0; i < days; i++) {
		a[i] = scanner.nextInt();
		b[i] = scanner.nextInt();
		}
		*/
        // 爆破法
        judge(0);
        System.out.println(count);
    }

    public static void judge(int days) {
        if (days == 3) {
            if (sum == 11) {
                count++;
            }
        } else {
            for (int x = a[days]; x <= b[days]; x++) {
                sum = sum + x;
                judge(days + 1);
                sum = sum - x;
            }
        }
    }
}
