package com.raylew.algorithm.os;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by RayLew on 2015/7/16.
 * QQ:897929321
 */
/*
一种优先级进程调度算法:
最简单最直观的进程调度策略是基于优先级的调度，多数实时系统采用基于优先级的调度，
每个进程根据它重要程度的不同被赋予不同的优先级，调度器在每次调度时，总选择优先级最高的进程开始执行.
算法约定在每次选中某个进程运行后，该进程优先级减1。
 */
public class ProcessSchedule {
    public static void main(String[] args) {
        Linked_PCBQueue queue = new Linked_PCBQueue();
        Object[] pcbs = queue.getPcbQueue().toArray();
        for (int i = 0; i < pcbs.length; i++) {
            PCB pcb = (PCB) pcbs[i];
            System.out.print(pcb.getName() + "的优先级是" + pcb.getPriority()
                    + ",运行时间是" + pcb.getRuntime());
            System.out.println();
        }
        LinkedList<PCB> pcbQueue = queue.getPcbQueue();
        int length = pcbQueue.size();
        while (length > 0) {
            PCB pcb = queue.deQueue();
            if (pcb.getRuntime() == 1) {
                System.out.println("进程" + pcb.getName() + "已经运行完毕");
                System.out.println("==========================");
                System.out.println();
                pcbQueue.remove(pcb);
                length--;
            } else {
                queue.enQueue(pcb);
            }
        }
    }
}

/**
 * 进程控制块
 *
 * @author [Ray Lew]
 */
class PCB implements Comparable<PCB> {
    String name;//进程名
    int runtime;//进程运行时间
    int priority;//进程优先级
    boolean status;//进程状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int compareTo(PCB o) {
        if (o.getPriority() < this.getPriority()) {
            return -1;
        } else if (o.getPriority() > this.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

}

class Linked_PCBQueue {
    LinkedList<PCB> pcbQueue = new LinkedList<PCB>();

    public LinkedList<PCB> getPcbQueue() {
        return pcbQueue;
    }

    public void setPcbQueue(LinkedList<PCB> pcbQueue) {
        this.pcbQueue = pcbQueue;
    }

    /**
     * 模拟进程队列
     */
    public Linked_PCBQueue() {
        PCB[] pcbs = new PCB[5];
        for (int i = 0; i < 5; i++) {
            PCB pcb = new PCB();
            Random random = new Random();
            pcb.setPriority(random.nextInt(10) + 1);
            pcb.setName("P" + (i + 1));
            pcb.setRuntime(random.nextInt(10) + 1);
            pcb.setStatus(false);
            pcbs[i] = pcb;
        }
        Arrays.sort(pcbs);
        for (int i = 0; i < 5; i++) {
            pcbQueue.add(pcbs[i]);
        }
    }

    public void enQueue(PCB pcb) {
        if (pcb.getPriority() > 0) {
            pcb.setPriority(pcb.getPriority() - 1);
        }
        pcb.setRuntime(pcb.getRuntime() - 1);
        System.out.println("," + "进程" + pcb.getName() + "运行结束");
        System.out.println("==========================");
        pcbQueue.add(pcb);
        // 对各进程按优先数大小重新排序
        int size = pcbQueue.size();
        PCB[] pcbs = new PCB[size];
        for (int i = 0; i < size; i++) {
            pcbs[i] = pcbQueue.get(i);
        }
        Arrays.sort(pcbs);
        pcbQueue.clear();
        for (int i = 0; i < size; i++) {
            pcbQueue.add(pcbs[i]);
        }
    }

    public PCB deQueue() {
        System.out.println("当前各进程情况:");
        System.out.println("进程名 优先数 剩余运行时间 状态");
        pcbQueue.get(0).setStatus(true);
        for (int i = 0; i < pcbQueue.size(); i++) {
            System.out.println(pcbQueue.get(i).getName() + "       "
                    + pcbQueue.get(i).getPriority() + "           "
                    + pcbQueue.get(i).getRuntime() + "                  "
                    + pcbQueue.get(i).isStatus());
        }
        pcbQueue.get(0).setStatus(false);
        System.out.println();
        PCB pcb = pcbQueue.removeFirst();
        System.out.print("进程" + pcb.getName() + "正在运行");
        return pcb;
    }
}
