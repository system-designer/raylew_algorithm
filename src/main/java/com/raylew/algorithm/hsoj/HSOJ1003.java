/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raylew.algorithm.hsoj;

import java.util.LinkedList;
import java.util.Scanner;

/*
Redraiment的老家住在工业区，日耗电量非常大。
是政府的眼中钉肉中刺，但又没办法，这里头住的可都是纳税大户呀。
今年7月，又传来了不幸的消息，政府要在7、8月对该区进行拉闸限电。
但迫于压力，限电制度规则不会太抠门，政府决定从7月1日停电，
然后隔一天到7月3日再停电，再隔两天到7月6日停电，一次下去，每次都比上一次晚一天。
Redraiment可是软件专业的学生，怎么离得开计算机。如果停电，就“英雄无用武之地”了。
呵呵。 所以他开始盘算起自己回家的日子了，他想知道自己到家后到底要经历多少天倒霉的停电。你能帮他算一算吗？
输入格式（Input）
输入包括多组数据。
每组数据包括一行:redraiment到家的日期。
输入以0/0结束。
输出格式（Output）
对应每个输入包括一个输出。
为redraiment回家后停电的天数(包括到家那天)。
输入样例（Sample Input）
8/31
0/0
输出样例（Sample Output）
0
 */
public class HSOJ1003 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.next();
        String[] str = inputStr.split("/");
        int a1, a2;
        a1 = Integer.parseInt(str[0]) > 7 ? 31 : 0;
        a2 = Integer.parseInt(str[1]);
        LinkedList<Integer> list = getPowerOffDate();
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >= (a1 + a2)) {
                j++;
            }
        }
        System.out.println("总数" + j);
    }

    public static LinkedList<Integer> getPowerOffDate() {
        LinkedList<Integer> list = new LinkedList();
        int j = 1;
        int i = 2;
        while (j <= 62) {
            list.add(j);
            j = j + i;
            i++;
        }
        for (i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        return list;
    }
}
