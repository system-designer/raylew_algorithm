package com.raylew.algorithm.ntoj;

import java.math.BigDecimal;
import java.util.Scanner;

/*
有一天，我做了个梦，梦见我很荣幸的接到了猪八戒的邀请，到天宫陪他吃酒。我犹豫了。
天上一日，人间一年啊！当然，我是个闲人，一年之中也没有多少时日是必须在人间的，因此，我希望选一个最长的空闲时间段，使我在天上待的时间尽量长。
记住，今年是4000年。天上一天也是24小时，每小时60分，每分60秒。
输入的第一行是一个非负整数 N，表示4000年中必须呆在人间的天数，以下共N行，每行两个用空格隔开的正整数，即日期（月,日），输入文件保证无错误，日期无重复。
输出一个非负整数，即在天上的时间(四舍五入精确到秒)。
样例输入
2
3 8
12 2
样例输出
63266
 */
public class B0008 {
    static int[] month_days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();
        int[][] array = new int[days][2];
        for (int i = 0; i < days; i++) {
            int month = scanner.nextInt();
            int day = scanner.nextInt();
            array[i][0] = month;
            array[i][1] = day;
        }
        int max_days = 0;
        int index = 0;
        int leave_month, leave_day;
        for (int i = 0; i < days - 1; i++) {
            int temp = getGapDays(array[i][0], array[i][1], array[i + 1][0],
                    array[i + 1][1]);
            if (max_days < temp) {
                max_days = temp;
                index = i;
            }
        }
        leave_month = array[index][0];
        leave_day = array[index][1];
        BigDecimal b = new BigDecimal(max_days * 24 * 3600 / 366.0);
        int res = (int) b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(res);
    }

    public static int getGapDays(int month1, int day1, int month2, int day2) {
        int sum = 0;
        sum += month_days[month1 - 1] - day1;
        for (int i = month1 + 1; i < month2; i++) {
            sum += month_days[i - 1];
        }
        sum += day2 - 1;
        return sum;
    }
}
