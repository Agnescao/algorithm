package array.dp;

//最长递增子序列
// 解法一：动态规划，complexity O(N……2)
// 解法2： 贪心+二分法 O(N*Logn)
public class lengthOfLISS {
    //动态规划解法
    public static int lengthOfLIS(int[] nums) {

        int longestLength=0;
        int n = nums.length;

        int[] dp = new int[n]; //用于记录以每个位置的数结尾的递增子数组的长度
        for (int i =0;i<n;i++){
            dp[i]=1;// 这个用于记录每个位置的数结尾初始化长度=1
            for (int j = 0; j < i; j++) { //对比i 位置的数字和前面所有的数对比，找到以i位置的数字结尾的 前面是否有比i位置小而且最接近i位置的数字，如果有就更新dp[i]中最大的长度

                if (nums[i]>nums[j]){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }

            }
            longestLength=Math.max(longestLength,dp[i]);

        }
        return longestLength;
    }

    // end[n] 储存当前所有长度为i+1位置的递增子序列 的最小结尾
    public static int lengthOfLIS_binary_search(int[] nums) {

        int longestLength=0;
        int n = nums.length;

        return longestLength;
    }
    //二分法
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,4,2,1,3,5,4,2,5,1};
        System.out.println(lengthOfLIS(nums));

    }
}
