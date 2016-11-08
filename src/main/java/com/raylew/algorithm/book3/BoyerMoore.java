package com.raylew.algorithm.book3;

/**
 * Created by Raymond on 2016/11/8.
 */
public class BoyerMoore {
    public static void main(String[] args) {
        String text = "QWPMTYUERADERLN";//"JIM_SAW_ME_IN_A_BARBERSHOP";
        String pattern = "ERADER";//"BARBER";
        BoyerMoore boyerMoore = new BoyerMoore();
        int matchIndex = boyerMoore.boyerMoore(pattern, text);
        System.out.println(matchIndex);
    }

    /**
     * 构造坏字符移动表
     *
     * @param patternChars 模式串
     * @return
     */
    private int[] createBadCharacterTable(char[] patternChars) {
        //所有元素为模式串长度
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
     * Fills in the array of bad characters.
     *
     * @param pattern
     * @param badChars
     */
    private void badCharacters(String pattern, int[] badChars) {
        int m = pattern.length();

        for (int i = 0; i < m - 1; ++i) {
            badChars[pattern.charAt(i)] = m - i - 1;
        }
    }

    /**
     * Calculates the suffixes for a given pattern
     *
     * @param pattern
     * @param suffixes
     */
    private void suffixes(String pattern, int[] suffixes) {
        int m = pattern.length();

        suffixes[m - 1] = m;
        int g = m - 1;
        int f = 0;
        for (int i = m - 2; i >= 0; --i) {
            if (i > g && suffixes[i + m - 1 - f] < i - g) {
                suffixes[i] = suffixes[i + m - 1 - f];
            } else {
                if (i < g) {
                    g = i;
                }
                f = i;

                while (g >= 0 && pattern.charAt(g) == pattern.charAt(g + m - 1 - f)) {
                    g--;
                }
                suffixes[i] = (f - g);
            }
        }
    }

    /**
     * Fills in the array of good suffixes
     *
     * @param pattern
     * @param goodSuffixes
     */
    private void goodSuffixes(String pattern, int[] goodSuffixes) {
        int m = pattern.length();
        int[] suff = new int[pattern.length()];

        suffixes(pattern, suff);

        for (int i = 0; i < m; i++) {
            goodSuffixes[i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            if (suff[i] == i + 1) {
                for (int j = 0; j < m - i - 1; j++) {
                    if (goodSuffixes[j] == m) {
                        goodSuffixes[j] = (m - i - 1);
                    }
                }
            }
        }

        for (int i = 0; i < m - 2; i++) {
            goodSuffixes[m - 1 - suff[i]] = (m - i - 1);
        }
    }

    /**
     * 从右至左扫描模式串，如果匹配失败模式串按照shiftTable移动
     *
     * @param pattern 模式串
     * @param text
     * @return
     */
    private int boyerMoore(String pattern, String text) {
        int n = text.length();
        int m = pattern.length();

        int[] goodSuffixes = new int[pattern.length()];
        int[] badCharacters = new int[pattern.length()];

        goodSuffixes(pattern, goodSuffixes);
        badCharacters(pattern, badCharacters);

        int i, j = 0;
        while (j < n - m) {
            for (i = m - 1; i >= 0 && pattern.charAt(i) == text.charAt(i + j); i--) ;
            if (i < 0) {
                // note that if the substring occurs more
                // than once into the text, the algorithm will
                // print out each position of the substring
                System.out.print(j + " ");
                j += goodSuffixes[0];
            } else {
                j += max(goodSuffixes[i], badCharacters[text.charAt(i + j)] - m + i + 1);
            }
        }
        return -1;
    }

    public int max(int m, int n) {
        if (m > n) {
            return m;
        } else {
            return n;
        }
    }
}
