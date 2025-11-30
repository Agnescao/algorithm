package array.dp;

/**
 * https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 *467. 环绕字符串中唯一的子字符串
 *定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串，所以 base 看起来是这样的：
 *
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "a"
 * 输出：1
 * 解释：字符串 s 的子字符串 "a" 在 base 中出现。
 * 示例 2：
 *
 * 输入：s = "cac"
 * 输出：2
 * 解释：字符串 s 有两个子字符串 ("a", "c") 在 base 中出现。
 * 示例 3：
 *
 * 输入：s = "zab"
 * 输出：6
 * 解释：字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
 */
public class findSubstringInWraproundStringS {

    /**
     * 思路：dp[s[i]]记录的是以s[i]的char结尾的 往左走最长match base中的最大字符串长度
     * return sum(dp)中每个地方的长度之和
     * @param args
     */

    public int findSubstringInWraproundString(String s) {
        //将s 字符串转换成char[]
        char[] chars = s.toCharArray();
        int[] sn = new int[chars.length]; //这样每一个位置存储的就是对应的数字， 比如a 字母就是0， b就是1
        // dp[i]代表的是每个以char结尾往左推最长的符合base 中的字串长度
        int[] dp = new int[26];
        //将每一个char 转换成int 类型代表一个字母的索引并且存储到数组中,一共26个字母
        for (int i = 0; i < chars.length; i++) {
            sn[i] = chars[i] - 'a'; // 这样每一个位置存储的就是对应的数字， 比如a 字母就是0， b就是1
        }


        // 核心逻辑
        // for 循环这个s string , 检查当前位置的char 也就是一个数字和前面的数字是否是连续的
        // 连续的分为两种情况， 一种是 za 一种是ab
        // 如果是连续的那么我们用一个变量len来记录变化的长度
        //记录以某个char 结尾的往左走最长的连续长度
        for (int i = 0, len = 1; i < chars.length; i++) { // for loop就可以从第二个位置开始

            if ((i - 1) >=0 && ((sn[i] == 0 && sn[i - 1] == 25) || (sn[i] - 1 == sn[i - 1]))) {
                len++;
            } else {
                len = 1; //往左看没有可以和它连续的char,那么它就是单独的存在作为一个子符串
            }
            dp[sn[i]] = Math.max(len, dp[sn[i]]);
        }

            //累计求得每一个char 结尾的字串的个数，得到结果
            int ans = 0;
            for (int j = 0; j < 26; j++) {
                ans += dp[j];
            }

            return ans;


    }
    public static void main(String[] args) {
        System.out.println(new findSubstringInWraproundStringS().findSubstringInWraproundString("zaba"));
    }
}
