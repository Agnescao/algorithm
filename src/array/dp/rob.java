package array.dp;

public class rob {
    /**输入
     nums =
     [2,1,1,2]

     添加到测试用例
     输出
     3
     预期结果
     4
     **/
    public static  int robf(int[] nums) {

        int n = nums.length;

        // index 为偶数的和 compare 为奇数的和
        // 偶数的和
        int evenSum = 0;
        int oddSum = 0;
        for (int i = 0; i < n; i++) {
            if(i%2==0)
            {
                evenSum += nums[i];
            }
            else
            {
                oddSum += nums[i];
            }
        }

        return evenSum>oddSum?evenSum:oddSum;
    }

    public  static  int robf2(int[] nums) {
        //[2,1,1,2]
        // dp[i] 表示 i 位置的数的rob 的最大值
        // i 位置的最大值 可以从i-2 位置roB 的最大值+i 位置的值
        // dp[i]= Math.max(dp[i-2]+nums[i],dp[i-1]);
        //

        int n = nums.length;
        if(n<=1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for(int i = 2; i<n;i++){
            dp[i]= Math.max(dp[i-2]+nums[i],dp[i-1]);
        }

        return dp[n];
    }
    public static void main(String[] args) {

    }
}
