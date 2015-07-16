package com.raylew.algorithm.os;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by [Ray Lew] on 2015/7/16
 * QQ:897929321
 * site:http://raylew.info
 * mail:aishangzoulu@gmail.com
 */
/*
模拟位示图的主存分配算法
 */
public class MemoryManagement {
    public static void main(String[] args) {
        System.out.println("当前内存情况:");
        Bitmap.print_arr();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入作业的数量：");
        int total = scanner.nextInt();// 输入作业的数量
        Job[] jobs = new Job[total];
        System.out.println("请输入每个作业需要的块数:");
        for (int i = 0; i < jobs.length; i++) {
            Job job = new Job();
            int need = scanner.nextInt();
            job.setNeed(need);
            job.setName("作业" + i);
            jobs[i] = job;
            Bitmap.distribute(jobs[i]);
            if (jobs[i].getSuccess() == true) {
                System.out.println(jobs[i].getName() + "分配后内存的情况：");
                Bitmap.print_arr(jobs[i], "f");
                System.out.println();
            }
        }
        System.out.println("请输入要回收的作业:0--" + (total - 1));
        int job_index = -1;
        while (job_index < 0 || job_index > (total - 1)) {
            job_index = scanner.nextInt();
            if (job_index < 0 || job_index > (total - 1)) {
                System.out.println("输入不合格，请重新输入");
            }
        }
        if (jobs[job_index].getSuccess() == false) {
            System.out.println(jobs[job_index].getName() + "尚未分配,无法回收");
            return;
        }
        Bitmap.callback(jobs[job_index]);
        System.out.println(jobs[job_index].getName() + "回收后内存的情况：");
        Bitmap.print_arr(jobs[job_index], "h");
    }
}

/**
 * 模拟作业
 *
 * @author Administrator
 */
class Job {

    private String name;// 作业名
    private Integer need;// 作业需要的存储区
    private Boolean success;// 作业分配是否成功
    private LinkedList<Integer> page_list;// 页表

    public Job(String name, Integer need, LinkedList<Integer> page_list) {
        super();
        this.name = name;
        this.need = need;
        this.page_list = page_list;
    }

    public Job() {
        page_list = new LinkedList<Integer>();
        success = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LinkedList<Integer> getPage_list() {
        return page_list;
    }

    public void setPage_list(LinkedList<Integer> page_list) {
        this.page_list = page_list;
    }
}

/**
 * 模拟主存存储区
 *
 * @author Administrator
 */
class Bitmap {

    //主存当前存储状态
    private static int[][] arr = {{1, 1, 0, 0, 1, 1, 1, 0,},
            {0, 1, 0, 1, 0, 1, 0, 0,}, {0, 0, 0, 0, 0, 0, 0, 0,},
            {1, 0, 0, 0, 0, 0, 0, 1,}, {0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0,}, {0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0,}};
    // 静态代码初始化count
    private static int count = 0;

    static {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (arr[i][j] == 0) {
                    count++;
                }
            }
        }
    }

    /**
     * 得到表示存储区的数组
     *
     * @return
     */
    public static int[][] getArr() {
        return arr;
    }

    /**
     * 得到闲置的块数
     *
     * @return
     */
    public static int getCount() {
        return count;
    }

    /**
     * 给作业分配存储区
     *
     * @param job 需要分配的作业
     */
    public static void distribute(Job job) {
        if (job.getNeed() > getCount()) {
            System.out.println(job.getName() + "分配不足!");
            return;
        } else {
            job.setSuccess(true);
            System.out.print(job.getName() + "分配成功,");
        }
        int temp = 0;
        LinkedList<Integer> page_list = job.getPage_list();
        boolean isBreak = false;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    temp++;
                    count--;
                    page_list.add(j * 8 + i);// 将块号存入页表
                }
                if (temp == job.getNeed()) {
                    isBreak = true;
                    break;
                }
            }
            if (isBreak) {
                break;
            }
        }
        System.out.println(job.getName() + "的页表如下：");
        LinkedList<Integer> tmp_page_list = job.getPage_list();
        System.out.println("页号  块号");
        for (int i = 0; i < tmp_page_list.size(); i++) {
            System.out.println(i + "         " + tmp_page_list.get(i));
        }
    }

    /**
     * 回收作业使用的存储区
     *
     * @param job
     */
    public static void callback(Job job) {
        LinkedList<Integer> page_list = job.getPage_list();
        for (Iterator iterator = page_list.iterator(); page_list != null
                && iterator.hasNext(); ) {
            Integer integer = (Integer) iterator.next();
            arr[integer % 8][integer / 8] = 0;
        }
        System.out.println(job.getName() + "回收完毕");
    }

    /**
     * 打印数组
     */
    public static void print_arr() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void print_arr(Job job, String mark) {
        LinkedList<Integer> page_list = job.getPage_list();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean exist = false;
                for (Iterator iterator = page_list.iterator(); iterator
                        .hasNext(); ) {
                    Integer integer = (Integer) iterator.next();
                    if (i == integer % 8 && j == integer / 8) {
                        exist = true;
                        break;
                    }
                }
                if (exist) {
                    System.out.print(arr[i][j] + mark + "  ");
                } else {
                    System.out.print(arr[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }
}
