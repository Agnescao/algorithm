package array.Comparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 763. 划分字母区间
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 *
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 *
 * 返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * 示例 2：
 *
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 *
 */
public class partitionLabelsS {
    public static List<Integer> partitionLabels(String s) {
        //每个字母在其他子串中需要互斥的
        //按顺序分
        /**
         * 那就是每个位置校验一下前面的是否含有相同的字符，如果没有可以加入，如果有就放到下一个去
         * ababcbacadefegdehijhklij
         * a
         * b
         * a
         * ababcbaca 中的a是重复最多的， 那就是将遇到和前面重复的char, 就将前面的所有的char 放到同一个子串
         *
         */
        int length = s.length();
        int start = 0,end=0;
        int[] last = new int[26];
        //这就是记录了每个char 出现的最后的位置
        for (int i = 0; i < length; i++) {
          last[s.charAt(i)-'a']=i;
        }


        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            //使用end维护当前片段的最后一个位置是否到了，
            end = Math.max(end,last[s.charAt(i)-'a']); //对比是否是最大的end
            if (i==end){//如果当前i到了最大边界的时候就更新array并且新的起点从end右边开始
                ans.add(end-start+1);
                start = end +1;

            }

        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
}
