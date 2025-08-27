package windows;

import java.util.HashMap;
import java.util.Map;
import java.lang.String;

/**
 * SUMMARY
 * 一开始右边界从-1开始，向右移动，左边界一直在第一个位置不动
 * 右边界向右移动到满足条件为止，移动过程中，使用hashmap将符合条件的char put进去并记录频次，每装一次，判断一下是否match
 * match了 就记录当下的start ,end, minlength, 并且将左边界右移， 右移过程中继续判断是否match
 * 直到最终右边界到s的最右端
 * 几个重要的变量， start 记录满足条件的起点， end 记录满足条件的终点， minwindlength 最小的满足条件的窗口长度
 */

public class minWindowS {
    public static String minWindow(String s, String t) {

        if(t.length()>s.length() || s==null|| t==null){
            return "";
        }
        //使用hash map 记录t string中每个 char的个数
        Map<Character,Integer> tmap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
             Integer count= tmap.getOrDefault(c,0);
            tmap.put(c,count+1);
        }
        Map<Character,Integer> sMap = new HashMap<>(); //记录 滑动窗口的时候 满足条件的char
        int start = -1; //记录满足条件的起点
        int end = -1; // 记录满足条件的终点
        int minLengthWind = Integer.MAX_VALUE; //一开始区最大是为了和第一次符合条件的子串长度的对比 并取得第一次符合条件的子串长度
        int l = 0; //窗口的起始点
        int r = -1; //窗口的右边界

       while(r<s.length()) { //右边界一直向右滑动到 s string 的最右边
           ++r;
           if (r<s.length() && tmap.containsKey(s.charAt(r))){ // 右边界第一次先移动，一直向右滑动找到一个满足条件的子串(对应后面的while matching 走不下去，就会回到while r< slength 并且一直右滑动窗口)
               Character c = s.charAt(r); // 右滑动窗口的时候就是添加元素to map
               sMap.put(s.charAt(r),sMap.getOrDefault(c,0)+1);
           }
          while (matching(tmap,sMap) && l<=r){ //满足条件就怎样? 左边界右移
              // 1. 使用一个start 记录当前 l 的起点， lengthofwIND 记录当下的长度
              // 2. 对比最小的符合条件的窗口长度
                    // 2.1 如果 lengthofWind < minlength,那么就将 minlength = lengthofwind, start =l, end = l+lengthofWind;
                    // 2.2 如果 lengthofWind >= minlength, minlength = minlength, 其他都不变

              if (r-l+1< minLengthWind){  // 满足了 就将左边界向右滑动，并记录当下的起点和 lengthofWind
                  start= l;
                  minLengthWind = r-l+1; // 这里用不了math.min判断是因为还要对start end 逻辑处理
                  end= l+minLengthWind;
              }
              // 3. 右移左边界,并且将左边界的元素从smap中去除掉
              if(tmap.containsKey(s.charAt(l))){ // 右移左边界， 如果这个元素存在于smap中，就去除
                  sMap.put(s.charAt(l),sMap.getOrDefault(s.charAt(l),0)-1);

              }
              ++l;//右移左边界
          }
        }
        return end==-1?"":s.substring(start,end);

    }

    private static boolean matching(Map<Character, Integer> tmap, Map<Character, Integer> sMap) {
        for (Map.Entry<Character, Integer> entry : tmap.entrySet()) {
            Character key = entry.getKey();
            int requiredCount = entry.getValue();  // tmap 中该字符需要的数量
            int actualCount = sMap.getOrDefault(key, 0);  // smap 中该字符实际的数量

            if (actualCount < requiredCount) {  // 如果 smap 中数量小于 tmap 所需，则返回 false
                return false;
            }
        }
        return true;  // 如果所有字符都满足条件，则返回 true


    }

    public static void main(String[] args) {
        /**
         * 给你一个字符串 s 、一个字符串 t 。
         * 返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
         *
         * 示例 1：
         *
         * 输入：s = "ADOBECODEBANC", t = "ABC"
         * 输出："BANC"
         * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
         * 示例 2：
         *
         * 输入：s = "a", t = "a"
         * 输出："a"
         * 解释：整个字符串 s 是最小覆盖子串。
         * 示例 3:
         *
         * 输入: s = "a", t = "aa"
         * 输出: ""
         * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
         * 因此没有符合条件的子字符串，返回空字符串
         *
         * 注意：
         *
         * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
         * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
         */
         String s = "ADOBECODEBANC";
         String t ="ABC";
         //System.out.println(minWindow(s,t));
         System.out.println(minWindow("cabwefgewcwaefgcf","cae"));
    }
}
