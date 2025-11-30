package array.dp;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class longestvalidParentheses {

    /**
     * 思路：动态规划dp[i]记录以i为结尾的往左最多推多远的最长有效括号长度
     * example： s = ")()())
     *
     * dp[i]
     * 1. 如果是左括号（，那么长度为0  （这里是往左推，（括号肯定只能向右看） dp[i]=0;
     * 2. 如果是右括号），那么需要判断以i为结尾的往左最多推多远最长有效括号长度，
     *      2.1 如果i-1位置的括号前面也有一个完整的最长有效括号，那么i位置结尾的至少是有效括号长度就是dp[i-1]+2
     *      2.2 那有没有可能找到i-1位置的括号前面一个完整的有效括号的前面比如以p结尾的还有一个完整的有效括号，那么i位置结尾的最长有效括号长度就是dp[i-1]+2+dp[p-1]
     *
     *   example s = "(()(））（））（）（）（）（（））"
     *
     * @param s
     * @return
     */

    public int longestValidParentheses(String s) {
       int ans = 0;
       // 初始化一个dp 动态规划表， 记录string s 每个位置的为结尾的最长有效括号长度
        int[] dp = new int[s.length()];
        // dp[0]=0; 是默认情况， 因为第一个位置从左边开始， 无法构成有效括号，长度就是0
       for (int i = 1; i < s.length(); i++) {
           //for 循环就可以从第二个位置开始查看，
           // 如果是一个左括号，（ 就无法和前面的括号构成有效括号， 所以长度为0
           if (s.charAt(i) == ')'){
               // 如果是一个右括号，）
               //如果是一个右括号，），我们需要判断以他为结尾的最长有效括号长度， 怎么判断呢， 利用动态规划的严重依赖原则， d
               // dp[i]的值是依赖dp[i-1] ，那这里怎么依赖呢，
               // 看下面的例子 假设i 来到3位置， 一看是一个右括号，那么我们要看i-1位置的值，也就是2位置的值，是一个2，
               // 这时候我们需要判断构成以2结尾的有效括号长度，前面的一个位置p是否是左括号，
               // 如果是，那么这个有效括号长度就是dp[i-1]+2，如果不是，那么这个有效括号长度就是0
               //  那么这个p 怎么找到呢， = i-dp[i-1]的长度+1  = 3-2-1=1
               //   （ （ ） ） ）
               // i  0 1 2 3 4
               // i=3      3-2-1=0 =p找到有效i-1位置的括号长度的前面一位是的下下标
               // dp 0  0 2 4 0
               int p = i-dp[i-1]-1;
               if (p>=0 && s.charAt(p)=='('){
                   dp[i]=dp[i-1]+2+(p-1>=0?dp[p-1]:0); //(p-1>=0?dp[p-1]:0)  是什么？ 有没有可能p-1位置前面也有一个有效括号，但是为什么只需要考虑到dp[i-1],
                   // 如果p-1位置前面也有有效括号， 那么已经被考虑过了，被累加到dp[p-1]中了
               }

           }
           ans = Math.max(ans,dp[i]);
       }

       return ans;
    }
    public static void main(String[] args) {

    }
}
