package array.dp;

/**
 *
 * https://leetcode.cn/problems/nth-magical-number/description/
 *
 * 878. 第 N 个神奇数字
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 *
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1, a = 2, b = 3
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 4, a = 2, b = 3
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= n <= 109
 * 2 <= a, b <= 4 * 104一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 *
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1, a = 2, b = 3
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 4, a = 2, b = 3
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= n <= 109
 * 2 <= a, b <= 4 * 104
 *
 * 在这个 [nthMagicalNumber](file://C:\Users\LeiCao\IdeaProjects\algorithims\src\array\dp\nthMagicalNumber.java#L45-L85) 问题中，使用 `long` 类型的主要原因包括：
 *
 * ## 1. 防止整数溢出
 * - 根据题目提示，`n` 的取值范围可达 `10^9`，而 [a](file://C:\Users\LeiCao\IdeaProjects\algorithims\src\Main.java) 和 `b` 的取值范围可达 `4 * 10^4`
 * - 在计算 `n * Math.min(a, b)` 时，结果可能超过 `int` 类型的最大值 `2147483647`
 *
 * ## 2. 大数值运算需求
 * - 二分查找的右边界 `r` 需要存储较大的数值
 * - 最小公倍数 [lcm](file://C:\Users\LeiCao\IdeaProjects\algorithims\src\array\dp\godAndLen.java#L43-L45) 的计算结果可能超出 `int` 范围
 * - 中间计算过程涉及大量大数运算
 *
 * ## 3. 保证计算精度
 * - 使用 `long` 可以确保在整个计算过程中不会因溢出而导致错误结果
 * - 特别是在执行 `m/a + m/b - m/leastcommonN` 这样的运算时，各项都可能是大数值
 *
 * 因此，代码中将变量声明为 `long` 类型是为了处理大数据量场景下的数值计算，避免整数溢出问题，确保算法的正确性。
 */
public class nthMagicalNumber {

    class Solution {
        public int nthMagicalNumberS(int n, int a, int b) {
            long leastcommonN = lcm(a,b);

            // 使用二分法寻找第n 个神奇的数字， 范围是在1- n*Math.min(a,b)之间，先确定范围
            //但是在这1-n*Math.min(a,b)之间 到底有多少个神奇的数字， 一定有一些数字是既可以/a,也可以/b,那么这些数是重复计算的
            long ans = 0; //记一下第n 个的那个神奇的数字值
            // 但是
            //(n/a+n/b-n/(lcm)) 去掉了1-n*Math.min(a,b)之间是既可以/a,也可以/b,重复计算的也就是最小公倍数的
            for(long l = 0,r=(long)n*Math.min(a,b),m=0; l<=r;)  {
                m = (l+r)>>1;
                // 1-m 上有多少神奇的的数字， 计算公式就是(m/a+m/b-m/(a*b))， 我要刚刚够n 个的那个数字，
                //>=n , 说明从 l-m 之间的神奇数字是够的， 并且还多了， 但是我要的是第n 的那个数字所有， 我要向左二分也就是r=m
                if((m/a+m/b-m/leastcommonN)>=n){
                    ans = m;
                    r=m-1; //
                }else{
                    l=m+1;
                }
            }
            // 好的， 这时候二分结束找到了ans 第n 个数字是什么， 不过这个数字不是最后要的答案
            //返回答案 对 109 + 7 取模 后的值

            return (int)(ans%1000000007);

        }

        public  long lcm(long a,long b){
            return (long) a /gcd(a,b)*b;
        }

        public  long gcd(long a,long b){
            return b==0? a: gcd(b,a%b);
        }
    }
    public static void main(String[] args) {

    }
}
