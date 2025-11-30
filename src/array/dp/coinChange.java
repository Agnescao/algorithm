package array.dp;

import java.util.Arrays;

public class coinChange {

    // 自下而上的递归可以通过缓存+一个变量来

    public static int coinChangeS(int[] coins, int amount) {
      // 暴力解法：找到所有的解， 然后返回最小的，那么怎么找到所有的解，
      int rest = amount;
      //输入：coins = [1, 2, 5], amount = 11
        // 终止条件是 rest == 0
       return process(coins, 0, rest);

    }

    public static int process(int[] coins, int index, int rest) {
      if (rest<0) {
          return 0;
      }
      //rest >=0
        if (index==coins.length){
            return rest==0?1:0;
        }
        // 第一种可能性coins i 位置不选择它
        int p1 = process(coins, index+1, rest);
      // 第2种可能性 coins i 位置选择它
        int p2 = process(coins, index, rest-coins[index]);
        return Math.max(p1, p2);
    }
    // dp
    //F(i)	最小硬币数量
    //F(0)	0 //金额为0不能由硬币组成
    //F(1)	1 //F(1)=min(F(1−1),F(1−2),F(1−5))+1=1
    //F(2)	1 //F(2)=min(F(2−1),F(2−2),F(2−5))+1=1
    //F(3)	2 //F(3)=min(F(3−1),F(3−2),F(3−5))+1=2
    //F(4)	2 //F(4)=min(F(4−1),F(4−2),F(4−5))+1=2
    //...	...
    //F(11)	3 //F(11)=min(F(11−1),F(11−2),F(11−5))+1=3
    //我们可以看到问题的答案是通过子问题的最优解得到的。

    //链接：https://leetcode.cn/problems/coin-change/solutions/132979/322-ling-qian-dui-huan-by-leetcode-solution/
    // 注意这里只是把枚举的过程全部列举出来， 并且去掉了重复计算和无效解的逻辑
    // 状态转移方程： dp[i] = min(dp[i-coins[j]]) + 1; 背包，爬楼梯， 都是这种思路
    public static int coinChange_dp(int[] coins, int amount) {
        int n = amount;
       int[] dp = new int[n+1]; // dp[i] 存储的是凑成总和为i 值的最小硬币数, +1是因为要个dp[0]=0 一个位置， 也就是凑成0的硬币数是0
        for (int i = 1; i <=amount; i++) {
            for (int j = 0; j <coins.length; j++) {
                if (coins[j]<=i ){ // 如果coin[j] 都已经大于 i, 那么就不需要考虑了， 加起来肯定无效方式
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }

        return dp[amount]> amount?-1:dp[amount]; //
    }
    // dp s
    public static int coinChange_dp_s(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1]; // dp [ 0, 1, 1, 2, 3, 12, 12, 12, 12, 12, 12, 12 ]
        Arrays.fill(dp, max); // 填充一个较大的数这样到min 中的时候第一次永远都会改变到一个阶段的中间值，
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // dp 转化公式一般根据题目的要求，最少或者最大来，然后就是背包问题加一个min
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount]; // 有时候凑不齐amount

    }
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange_dp_s(coins, 11));

        int[] coins2 = {2};
        System.out.println(coinChange_dp_s(coins2, 3));

        int[] coins3 = {1};
        System.out.println(coinChange_dp_s(coins3, 0));

    }
}
