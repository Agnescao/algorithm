package array.dp;

import java.util.Arrays;

public class minCostS {
    // 动态规划dp[i] 存储的就是第i个位置的最小花费，也就是每次递归返回的最小值
    // 严重依赖的动态规划
    // 从底到顶的循环， +动态规划转移方程
    //火车票有 三种不同的销售方式 ：
    //
    //一张 为期一天 的通行证售价为 costs[0] 美元；
    //一张 为期七天 的通行证售价为 costs[1] 美元；
    //一张 为期三十天 的通行证售价为 costs[2] 美元。
//输入：days = [1,4,6,7,8,20], costs = [2,7,15]
//输出：11
    public int minCost(int[] costs,int[] days ) {
        int n= days.length;
        int[] dp = new int[n+1];

        int[] sales = new int[]{1,7,30};
        Arrays.fill(dp,0,n+1, Integer.MAX_VALUE);
        dp[n] = 0;
        // 从底到顶的循环， 要求完成在days 列表中每一天的旅行所需要的最低消费， 那么day[i]位置的最小消费依赖的是day[i-1]/days[i+1]位置最小的消费（看你从顶到底，还是从底到顶）
        for (int i = days.length-1; i >=0 ; i--) {
            // 下面就是dp转移方程
            for (int k = 0,j=i; k < 3; k++) { // 枚举3种方案 1,7,30 选择不同的方案后续的方案， 才能得到最小的消费
                while( j< n && sales[k]+days[i]>days[j] ){
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k]+dp[j]); //真他妈的烦， cost[k]=2 + dp[j](2147483647 =Integer.Max_value) = -2147483647 (为啥，超出最大数范围 溢出了，变成负数，所以还是乖乖的给dp[n]=0)

            }

            }
        return dp[0];
    }
    public static void main(String[] args) {
            int [] costs = {2,7,15};
            int [] days = {1,4,6,7,8,20};
            System.out.println(new minCostS().minCost(costs, days));
    }
}
