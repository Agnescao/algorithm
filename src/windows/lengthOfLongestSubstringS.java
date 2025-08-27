package windows;

import java.util.HashSet;

/**
 * 滑动窗口经常搭配hashset来
 */
public class lengthOfLongestSubstringS {
    public static  int lengthOfLongestSubstring(String s) {
        int lengthOfLongest = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> occ = new HashSet<>();

            int r = 0;
            while( (i+r)<s.length() && !occ.contains(chars[i+r])){ // abc
                occ.add(chars[i+r]);
                r++;

            }

          lengthOfLongest = Math.max(lengthOfLongest, r);
        }
return  lengthOfLongest;
    }
    public static void main(String[] args) {
        /**
         * 输入: s = "abcabcbb"
         * 输出: 3
         * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
         */

      System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("aabc"));

    }
}
