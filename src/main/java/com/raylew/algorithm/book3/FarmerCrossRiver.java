package com.raylew.algorithm.book3;

import java.util.*;

/**
 * Created by Raymond on 2016/11/3.
 * 农夫0、狼1、羊2和菜3过河
 */
public class FarmerCrossRiver {
    //深搜路径
    private static List<Integer> path;
    private static boolean[] visited;

    public static void main(String[] args) {
        path = new LinkedList<Integer>();
        visited = new boolean[16];
        int[] status = {0, 0, 0, 0};
        int statusCode = status2Code(status);
        visited[statusCode] = true;
        path.add(statusCode);
        across(status, 0);
    }

    /**
     * dfs实现过河
     * @param status
     * @param direction
     */
    public static void across(int[] status, int direction) {
        if (isSucceed(status)) {
            //System.out.println("ok");
            System.out.println("共经过"+(path.size()-1)+"步");
            printStatus(path);
            return;
        } else if (isDangerous(status)) {
            //System.out.println("failed");
            return;
        } else {
            if (direction == 0) {
                //aSide->bSide
                //农夫单独过河
                status[0] = 1;
                int statusCode = status2Code(status);
                if (!visited[statusCode]) {
                    visited[statusCode] = true;
                    path.add(statusCode);
                    across(status, 1 - direction);
                    visited[statusCode] = false;
                    path.remove((Integer) statusCode);
                }
                status[0] = 0;
                //农夫携带某一物品过河
                for (int j = 1; j < status.length; j++) {
                    status[0] = 1;
                    if (status[j] == 0) {
                        status[j] = 1;
                        statusCode = status2Code(status);
                        if (!visited[statusCode]) {
                            visited[statusCode] = true;
                            path.add(statusCode);
                            across(status, 1 - direction);
                            visited[statusCode] = false;
                            path.remove((Integer) statusCode);
                        }
                        status[j] = 0;
                    }
                    status[0] = 0;
                }
            } else {
                //bSide->aSide
                //农夫单独过河
                status[0] = 0;
                int statusCode = status2Code(status);
                if (!visited[statusCode]) {
                    visited[statusCode] = true;
                    path.add(statusCode);
                    across(status, 1 - direction);
                    visited[statusCode] = false;
                    path.remove((Integer) statusCode);
                }
                status[0] = 1;
                //农夫携带某一物品过河
                for (int j = 1; j < status.length; j++) {
                    status[0] = 0;
                    if (status[j] == 1) {
                        status[j] = 0;
                        statusCode = status2Code(status);
                        if (!visited[statusCode]) {
                            visited[statusCode] = true;
                            path.add(statusCode);
                            across(status, 1 - direction);
                            visited[statusCode] = false;
                            path.remove((Integer) statusCode);
                        }
                        status[j] = 1;
                    }
                    status[0] = 1;
                }
            }
        }
    }

    /**
     * 成功
     *
     * @param status
     * @return
     */
    public static boolean isSucceed(int[] status) {
        int index = 0;
        for (; index < status.length; index++) {
            if (status[index] == 0) {
                break;
            }
        }
        return index == status.length;
    }

    /**
     * 失败
     *
     * @param status 状态
     * @return
     */
    public static boolean isDangerous(int[] status) {
        if ((status[0] == 1 && status[1] == 0 && status[2] == 0)
                || (status[0] == 1 && status[2] == 0 && status[3] == 0)
                || (status[0] == 0 && status[1] == 1 && status[2] == 1)
                || (status[0] == 0 && status[2] == 1 && status[3] == 1)) {
            return true;
        }
        return false;
    }

    /**
     * 状态编码
     * @param status
     * @return
     */
    public static int status2Code(int[] status) {
        int res = 0;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 1) {
                int p = 1;
                for (int j = 0; j < (status.length - 1 - i); j++) {
                    p = p * 2;
                }
                res = res + p;
            }
        }
        return res;
    }

    /**
     * 解码到状态
     * @param code
     * @return
     */
    public static int[] code2Status(int code) {
        int[] res = new int[4];
        int index = 3;
        while (code > 0) {
            if (code % 2 == 0) {
                res[index] = 0;
            } else {
                res[index] = 1;
            }
            code = code / 2;
            index--;
        }
        return res;
    }

    /**
     * 打印路径
     * @param path
     */
    public static void printStatus(List<Integer> path) {
        Iterator<Integer> iterator = path.iterator();
        while (iterator.hasNext()) {
            int code = iterator.next();
            int[] status = code2Status(code);
            for (int i = 0; i < status.length; i++) {
                System.out.print(status[i]);
            }
            System.out.println();
        }
    }
}