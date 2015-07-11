package com.raylew.algorithm.other;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 求一个字符串中出现次数最多的字符
 */
public class 统计字符 {

    public static void main(String[] args) {
        String str = "zdsgzd";
        Map map = new HashMap();
        int maxvalue = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                int total =(Integer) map.get(c);
                total++;
                map.put(c, total);
                if (maxvalue < total) {
                    maxvalue = total;
                }
            }
        }
        System.out.println("maxvalue:" + maxvalue);
        System.out.println(map.toString());
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        int max = 0;
        char maxkey = 0;
        char key = 0;
        while (iterator.hasNext()) {
            key = (Character) iterator.next();
            if (max <= (Integer) map.get(key)) {
                max = (Integer) map.get(key);
                maxkey = key;
            }
        }
        System.out.println(maxkey + ":" + max);
    }
}
