package array.dp;


import java.util.Arrays;
//91. 解码方法

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * "1" -> 'A'
 *
 * "2" -> 'B'
 *
 * ...
 *
 * "25" -> 'Y'
 *
 * "26" -> 'Z'
 *
 * 然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。
 *
 * 例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1, 1, 10, 6)
 * "KJF" ，将消息分组为 (11, 10, 6)
 * 消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
 * 注意，可能存在无法解码的字符串。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 *
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 *
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 */
public class decodeways {
    // Decode Ways

    // 边界条件一般就是那些个特殊情况比如0 单独或者0开头的数字是没办法转换的， 还有第二个条件就是你超出了27也没法转换
    // 暴力递归方法走
    public static int numDecodings(String s) {

        return dfs(s.toCharArray(),0);

    }

    // 递归方法 超时

    private static int dfs(char[] charArray, int i) {
        // 返回当前的一种解码的方法  比如 112 i已经走到charArray[i]最后一个元素后面了，也就是112的末尾了，那么就是一种解码方法， 返回1
        if (i==charArray.length){return  1;}
        // 定义一个ans 变量记录递归的结果， 1 表示一种解码方法，0表示没有一种解码方法
        int ans = 0;

        // 第一种情况是遇到0的时候， 0开头的数字是不行的，所以返回0
        if (charArray[i]=='0'){ans=  0;} else {

            // 第二种情况就是charArray[i] != '0',并且我只取i 这个字符的时候，那么就返回1种解码方法
            // 比如 112 i走到了1，那么我取1的时候，就是一种解码方法，所以返回1种解码方法
            ans = dfs(charArray,i+1);

            // 第三种情况就是我想取 i, i+1两个位置的数字组成2位数，那么就返回1种解码方法
            // 比如 112 i走到了1，那么我取1,2的时候，就是一种解码方法，所以返回1种解码方法
            if(i+1<charArray.length && ((charArray[i]-'0')*10+(charArray[i+1]) )<=26){
                ans =dfs(charArray,i+2);
            }

        }

        return ans;

    }

    // 暴力递归改 挂缓存 递归方法


    public static int numDecodings2(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1); //用来记录递归过程中的ans
        return dfs2(s.toCharArray(),0,dp);
    }

    private static int dfs2(char[] charArray, int i, int[] dp) {
        if (i==charArray.length){return  1;}

        if (dp[i]!=-1){
            return dp[i];
        }
        int ans ;
        // 第一种情况是遇到0的时候， 0开头的数字是不行的，所以返回0
        if (charArray[i]=='0'){ans=  0;} else {

            // 第二种情况就是charArray[i] != '0',并且我只取i 这个字符的时候，那么就返回1种解码方法
            // 比如 112 i走到了1，那么我取1的时候，就是一种解码方法，所以返回1种解码方法
            ans = dfs2(charArray,i+1,dp);

            // 第三种情况就是我想取 i, i+1两个位置的数字组成2位数，那么就返回1种解码方法
            // 比如 112 i走到了1，那么我取1,2的时候，就是一种解码方法，所以返回1种解码方法
            if(i+1<charArray.length && ((charArray[i]-'0')*10+(charArray[i+1]) )<=26){
                ans =dfs2(charArray,i+2,dp);
            }

        }
        dp[i] = ans;  // 记录递归过程中的ans 和56行事配套的， 下次就可以看是否已经计算过了， 计算过了就直接取缓存中的ans
        return ans;

    }

    // 严格动态规划就要 从左到右推动 左边的结果依赖右边的状态

    public static int numDecodings3(String s) {
        int n = s.length();
        int[] dp = new int[s.length()+1];
        dp[n] = 1; //dp[n] 对应的就是 上面54行代码 if (i==charArray.length){return  1;} 的结果，也就是一种解码方法
        for(int i =n-1;i>=0;i--){
          if(s.charAt(i)=='0'){
              dp[i]=0;
          }else{
              dp[i] = dp[i+1]; // dp[i] 对应的就是 i+1位置的解码方法，也就是一种解码方法
              if(i+1<n && ((s.charAt(i)-'0')*10+(s.charAt(i+1)-'0') )<=26){
                  dp[i]+=dp[i+2];
              }
          }
        }
        return dp[0];
    }
    // 观察上面的dp[i]的值是依赖dp[i+1]和dp[i+2], 所以可以进行空间优化， 使用两个变量滚动记录dp[i]的值
    public static int numDecodings4(String s) {
        int n = s.length();
        //dp[n]=1
        int next = 1; // dp[i] 对应的就是 i+1位置的解码方法，也就是一种解码方法
        // 相当于不存在的dp[n+1]
        int nextNext = 1;
        for(int i =n-1,cur;i>=0;i--){

            if(s.charAt(i)=='0'){ cur = 0;} else {
                cur = next;
                if(i+1<n && ((s.charAt(i)-'0')*10+(s.charAt(i+1)-'0') )<=26){
                    cur += nextNext;
                }
            }
            // 滚动变量设置
            nextNext = next;
            next = cur;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(numDecodings3("1102"));
    }
}
