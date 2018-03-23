package com.raylew.algorithm.leetcode;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Raymond on 2017/8/13.
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int maxLength=0;
        for(int i=0;i<s.length();i++){
            HashSet hashSet=new HashSet();
            int j=i;
            for(;j<s.length();j++){
                if(!hashSet.contains(s.charAt(j))){
                    hashSet.add(s.charAt(j));
                }else{
                    break;
                }
            }
            int length=j-i;
            if(maxLength<length){
                maxLength=length;
            }
        }
        return maxLength;
    }
}
