package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/11/5.
 */
public class Horspool {
    public static void main(String[] args) {
        String text = "JIM_SAW_ME_IN_A_BARBERSHOP";
        String pattern = "BARBER";
        Horspool horspool = new Horspool();
        int matchIndex = horspool.horspool(pattern, text);
        System.out.println(matchIndex);
    }

    /**
     * 构造坏字符移动表
     *
     * @param patternChars 模式串
     * @return
     */
    int[] createBadCharacterTable(char[] patternChars) {
        //let all be pattern.length
        int[] badShiftTable = new int[256];
        for (int i = 0; i < 256; i++) {
            badShiftTable[i] = patternChars.length;
        }
        int last = patternChars.length - 1;
        for (int i = 0; i < last; i++) {
            badShiftTable[(int) patternChars[i]] = last - i;
        }
        return badShiftTable;
    }

    /**
     * 从右至左扫描模式串，如果匹配失败模式串按照shiftTable移动
     *
     * @param pattern  模式串
     * @param text
     * @return
     */
    int horspool(String pattern, String text) {
        char[] patternChars = pattern.toCharArray();
        char[] textChars = text.toCharArray();
        if (patternChars.length > textChars.length) {
            return -1;
        }
        int[] badShiftTable = createBadCharacterTable(patternChars);
        int offset = 0;
        int scan = 0;
        int last = patternChars.length - 1;
        int maxOffset = textChars.length - patternChars.length;
        while (offset <= maxOffset) {
            for (scan = last; patternChars[scan] == textChars[scan + offset]; scan--) {
                //match
                if (scan == 0) {
                    return offset;
                }
            }
            offset += badShiftTable[(int) textChars[offset + last]];
        }
        return -1;
    }
}
