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
一种磁盘访问调度算法-移臂调度算法包括以下四种：
1） 先来先服务算法； (根据访问者提出访问请求的先后次序来决定执行次序。
2） 最短寻找时间优先调度算法；(从等待的访问者中挑选寻找时间最短的那个请求执行，而不管访问者的先后次序。
3） 电梯调度算法；(从移动臂当前位置沿移动方向选择最近的那个柱面的访问者来执行，若该方向上无请求访问时，就改变移动方向再选择。)
4） 单向扫描调度算法。 (从0柱面开始往里单向扫描，扫到哪个执行哪个。
 */
public class ElevatorSchedule {
    public static void main(String[] args) {
        Disk disk = new Disk();
        System.out.println("系统功能菜单:");
        System.out.println("1.接收请求");
        System.out.println("2.驱动调度");
        System.out.println("3.退出系统");
        Scanner scanner = new Scanner(System.in);
        int mark = scanner.nextInt();
        while (mark != 3) {
            switch (mark) {
                case 1:
                    receiveReq(disk);
                    break;
                case 2:
                    driver_scheduling(disk);
                    break;
            }
            System.out.println("请输入指令：");
            mark = scanner.nextInt();
        }
        System.out.println("已退出");
    }

    /**
     * 接收请求
     *
     * @param disk
     */
    public static void receiveReq(Disk disk) {
        LinkedList<IOTableNode> iotable = disk.getIotable();
        System.out.println("请输入进程信息：");
        Scanner scanner = new Scanner(System.in);
        String p_name = scanner.next();
        Integer cylinder = scanner.nextInt();
        Integer track = scanner.nextInt();
        Integer phy_record = scanner.nextInt();
        IOTableNode node = new IOTableNode(p_name, cylinder, track, phy_record);
        iotable.add(node);
        System.out.println("接收请求成功");
    }

    /**
     * 驱动调度
     *
     * @param disk
     */
    public static void driver_scheduling(Disk disk) {
        LinkedList<IOTableNode> iotable = disk.getIotable();
        if (iotable.isEmpty()) {
            System.out.println("没有请求");
            return;
        } else {
            disk.printIOTable();
            disk.schedule();// 驱动调度
        }
    }
}

class IOTableNode {
    private String p_name;// 进程名
    private Integer cylinder;// 柱面
    private Integer track;// 磁道
    private Integer phy_record;// 物理记录

    public IOTableNode() {
    }

    public IOTableNode(String p_name, Integer cylinder, Integer track,
                       Integer phy_record) {
        super();
        this.p_name = p_name;
        this.cylinder = cylinder;
        this.track = track;
        this.phy_record = phy_record;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    public Integer getPhy_record() {
        return phy_record;
    }

    public void setPhy_record(Integer phy_record) {
        this.phy_record = phy_record;
    }
}

class Disk {
    private Integer cur_cylinder;// 当前柱面
    private Integer cur_track;// 当前磁道
    private boolean cur_direction;// 当前移臂臂方向，true表示向里
    private Integer cur_phy_record;// 当前物理记录
    private LinkedList<IOTableNode> iotable;

    public Disk() {
        cur_cylinder = 0;// 柱面号为0
        cur_phy_record = 0;
        cur_track = 0;
        cur_direction = true;// 方向向里
        iotable = new LinkedList<IOTableNode>();
        initIOTable();
    }

    public void initIOTable() {
        IOTableNode n0 = new IOTableNode("0", 0, 0, 0);
        IOTableNode n1 = new IOTableNode("1", 1, 1, 1);
        IOTableNode n2 = new IOTableNode("2", 1, 10, 5);
        IOTableNode n3 = new IOTableNode("3", 1, 5, 4);
        IOTableNode n4 = new IOTableNode("4", 10, 5, 7);
        IOTableNode n5 = new IOTableNode("5", 5, 11, 6);
        IOTableNode n6 = new IOTableNode("6", 6, 11, 6);
        IOTableNode n7 = new IOTableNode("7", 10, 4, 7);
        IOTableNode n8 = new IOTableNode("8", 1, 2, 0);
        iotable.add(n0);
        iotable.add(n1);
        iotable.add(n2);
        iotable.add(n3);
        iotable.add(n4);
        iotable.add(n5);
        iotable.add(n6);
        iotable.add(n7);
        iotable.add(n8);
    }

    /**
     * 电梯调度
     */
    public void schedule() {
        boolean samecylinder = false;
        for (Iterator iterator = iotable.iterator(); iterator.hasNext(); ) {
            IOTableNode node = (IOTableNode) iterator.next();
            if (node.getCylinder() == cur_cylinder) {
                samecylinder = true;
                break;
            }
        }
        if (samecylinder) {
            int index = 0;
            index = selectPhy_record();
            IOTableNode selected = iotable.remove(index);
            System.out.println("当前选中了进程：" + selected.getP_name() + ",柱面："
                    + selected.getCylinder() + ",物理记录号："
                    + selected.getPhy_record() + ",移臂方向：" + "同一柱面没有移臂");
        } else {
            IOTableNode selected = null;
            if (cur_direction) {// 方向向里
                LinkedList<IOTableNode> reslist = judge(1);
                if (reslist.isEmpty()) {// 没有大于当前柱面的请求
                    this.setCur_direction(false);// 修改方向向外
                    selected = selectCylinder(0, iotable);
                } else {
                    selected = selectCylinder(1, reslist);
                }
                iotable.remove(selected);
                this.setCur_cylinder(selected.getCylinder());
                this.setCur_track(selected.getTrack());
                this.setCur_phy_record(selected.getPhy_record());
                String direction = cur_direction ? "向里" : "向外";
                System.out.println("当前选中了进程：" + selected.getP_name() + ",柱面："
                        + selected.getCylinder() + ",物理记录号："
                        + selected.getPhy_record() + ",移臂方向：" + direction);
            } else {// 方向向外
                LinkedList<IOTableNode> reslist = judge(0);
                if (reslist.isEmpty()) {// 没有小于当前柱面的请求
                    selected = selectCylinder(1, iotable);
                    this.setCur_direction(true);// 修改方向向里
                } else {
                    selected = selectCylinder(0, reslist);
                }
                iotable.remove(selected);
                this.setCur_cylinder(selected.getCylinder());
                this.setCur_track(selected.getTrack());
                this.setCur_phy_record(selected.getPhy_record());
                String direction = cur_direction ? "向里" : "向外";
                System.out.println("当前选中了进程：" + selected.getP_name() + ",柱面："
                        + selected.getCylinder() + ",物理记录号："
                        + selected.getPhy_record() + ",移臂方向：" + direction);
            }
        }
    }

    /**
     * 同一柱面选择磁道近的
     *
     * @return
     */
    public int selectPhy_record() {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < iotable.size(); i++) {
            IOTableNode node = (IOTableNode) iotable.get(i);
            if (node.getCylinder() == cur_cylinder) {
                if (Math.abs(node.getPhy_record() - cur_phy_record) < min) {
                    min = Math.abs(node.getPhy_record() - cur_phy_record);
                    index = i;
                }
            }
        }
        return index;
    }

    /**
     * 判断是否有比当前柱面大或者小的请求
     *
     * @param mark 1表示是否有大于
     * @return
     */
    public LinkedList<IOTableNode> judge(int mark) {
        LinkedList<IOTableNode> reslist = new LinkedList<IOTableNode>();
        if (mark == 1) {
            for (int i = 0; i < iotable.size(); i++) {
                IOTableNode node = (IOTableNode) iotable.get(i);
                if (node.getCylinder() > cur_cylinder) {
                    reslist.add(node);
                }
            }
        } else if (mark == 0) {
            for (int i = 0; i < iotable.size(); i++) {
                IOTableNode node = (IOTableNode) iotable.get(i);
                if (node.getCylinder() < cur_cylinder) {
                    reslist.add(node);
                }
            }
        }
        return reslist;
    }

    /**
     * 从大于或者小于的请求中选择最小或者最大的请求
     *
     * @param mark    1表示方向向里
     * @param reslist 大于或小于当前柱面的请求列表
     * @return
     */
    public IOTableNode selectCylinder(int mark, LinkedList<IOTableNode> reslist) {
        int index = 0;
        if (mark == 1) {// 在大于当前柱面的请求列表中选择最小的
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < reslist.size(); i++) {
                IOTableNode node = (IOTableNode) reslist.get(i);
                if (node.getCylinder() < min) {
                    min = node.getCylinder();
                    index = i;
                }
            }
        } else if (mark == 0) {// 在小于当前柱面的请求列表中选择最大的
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < reslist.size(); i++) {
                IOTableNode node = (IOTableNode) reslist.get(i);
                if (node.getCylinder() > max) {
                    max = node.getCylinder();
                    index = i;
                }
            }
        }
        return reslist.get(index);
    }

    public void printIOTable() {
        System.out.println("进程名 柱面号 磁道号 物理记录号");
        for (Iterator iterator = iotable.iterator(); iterator.hasNext(); ) {
            IOTableNode node = (IOTableNode) iterator.next();
            System.out.println(node.getP_name() + "    " + node.getCylinder()
                    + "    " + node.getTrack() + "    " + node.getPhy_record());
        }
    }

    public Integer getCur_cylinder() {
        return cur_cylinder;
    }

    public void setCur_cylinder(Integer cur_cylinder) {
        this.cur_cylinder = cur_cylinder;
    }

    public boolean isCur_direction() {
        return cur_direction;
    }

    public void setCur_direction(boolean cur_direction) {
        this.cur_direction = cur_direction;
    }

    public LinkedList<IOTableNode> getIotable() {
        return iotable;
    }

    public void setIotable(LinkedList<IOTableNode> iotable) {
        this.iotable = iotable;
    }

    public Integer getCur_track() {
        return cur_track;
    }

    public void setCur_track(Integer cur_track) {
        this.cur_track = cur_track;
    }

    public Integer getCur_phy_record() {
        return cur_phy_record;
    }

    public void setCur_phy_record(Integer cur_phy_record) {
        this.cur_phy_record = cur_phy_record;
    }
}
