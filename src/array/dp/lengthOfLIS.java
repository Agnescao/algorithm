package array.dp;

//https://leetcode.cn/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked

import java.util.Arrays;

/**
 *
 300. 最长递增子序列

 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。


 示例 1：

 输入：nums = [10,9,2,5,3,7,101,18]
 输出：4
 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 示例 2：

 输入：nums = [0,1,0,3,2,3]
 输出：4
 示例 3：

 输入：nums = [7,7,7,7,7,7,7]
 输出：1
 */
public class lengthOfLIS {
    public int lengthOfLIS_try(int[] nums) {
        // 倒叙的方式遍历nums , 查看每个位置上， 向前数， 最长有多少数字依次递减
        // dp[i] 记录i位置为止，前面有多长的数字依次递减
        // 最后返回Arrays.max(dp)
        int n= nums.length;
        int[] dp= new int[n];  //+1的原因是 假如nums length=8, 如果还是设置dp[n]，那么dp[n] 就会越界, dp[8]你怎么遍历 你怎么遍历0-7范围， 你怎么取8位置的数字， 这个已经搞过好多次了还在这又犯错


        for(int i= n-1;i>=0;i--){
            int curr = i;
            int nextIndex = i-1;
            int length = 1;
            while(nextIndex>=0 && curr>0){
                //输入：nums = [10,9,2,5,3,7,8,18]
                if( nums[curr]>nums[nextIndex]){ curr=nextIndex; nextIndex--; length++;}else{
                    //输入：nums = [10,9,2,5,3,7,101,18]
                    nextIndex--;
                }
            }
            dp[i]=length;

            // for(int j=nextIndex-1, length = 0;j>=0;j--){
            //    if( nums[i]>nums[j]){ nextIndex=j; length++}  // 遇到nextIndex 每次计算一次会变化的， 下次的j 值不一样的话，可以用while
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public int lengthOfLIS(int[] nums) {
        // 倒叙的方式遍历nums , 查看每个位置上， 向前数， 最长有多少数字依次递减
        // 这种倒叙肯定有重复计算那么我们可以从左往右看，
        // dp[i] 记录i位置为止往左看比它小的值中最大的len+1就是当前位置从左到它的最长有效递增子序列，前面有多长的数字依次递减
        // 最后返回Arrays.max(dp)
        if(nums ==null || nums.length==0){
            return 0;
        }
        int n= nums.length;
        int[] dp= new int[n];
        dp[0]=1; //第一个元素一个人左边没有任何数字了

        for(int i= 1;i<n;i++){
            int maxlen = 0;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){ maxlen=Math.max(dp[j],maxlen);}

            }
            dp[i]=maxlen+1;
        }

        return Arrays.stream(dp).max().getAsInt();

    }
    // 优化的方式o(nlogn)

    public static void main(String[] args) {
        System.out.println(new lengthOfLIS().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(new lengthOfLIS().lengthOfLIS(new int[]{0,1,0,3,2,3}));
    }
}
