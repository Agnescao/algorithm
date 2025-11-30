package array.dp;

import java.util.Arrays;

/**
 * 乘积最大子数组
 * https://leetcode.cn/problems/maximum-product-subarray/description/?envType=study-plan-v2&envId=top-100-liked
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */
public class maxProductS {

    /**
     * 主要问题分析
     * 算法复杂度高：当前实现使用了嵌套循环，时间复杂度为 O(n²)
     * 逻辑错误：maxmutiple 初始化为 Integer.MIN_VALUE，但应该考虑单个元素的情况
     * 缺少溢出处理：没有处理32位整数溢出的情况
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // dp[i] 代表nums 中以i 位置的连续非空子数组的最大乘积
        // return Arrays.stream(dp).max().getAsInt()
        int n = nums.length;
        int[] dp =new int[n];
       // dp[0]=nums[0];
        for(int i=0;i<n;i++){
            int maxmutiple = Integer.MIN_VALUE;
            int mutiple =nums[i];
            for(int j=i-1;j>=0;j--){
                mutiple *= nums[j];
                maxmutiple= Math.max(maxmutiple,mutiple);
            }
            dp[i]=maxmutiple;
        }

        return Arrays.stream(dp).max().getAsInt();


    }
    // 解决超出32位整数的问题 并且解决逻辑错误
    // 是使用dp , dp[i] 代表nums 中以i 位置的连续非空子数组的最大乘积 取决于dp[i-1]的最大乘积， 乘积的计算结果可能超过32位整数，所以使用long
    // 这个和上面的思路不一样， 上面是两个for loop 有大量的重复计算， 性能不OK，
    // 优化思路：使用一个变量保存最大乘积，最小乘积，然后更新最大乘积，最小乘积，最后返回最大乘积
    // 为什么要使用 最大乘积 和最小乘积呢？
    // [2,-3,5,7,-5] dp[3]位置结尾的最大的乘积是7*5=35， 如果按照dp[i]单纯依赖dp[i-1]的逻辑，那么就会有错误， dp[4]按道理来说最大的乘积=2*-3*5*7*-5
    // 也就是负负得正的原理， 所以需要维护两个乘积， 一个最大乘积， 一个最小乘积
    // maxF 存储当前位置结尾的最大乘积
    // minF 存储当前位置结尾的最小乘积
    //每次都将， nums[i] 和最大的最小的乘一下， 再和nums[i] 本身取最大，或最小， 就是为了防止负负得正， 或者正负得负的情况
    // maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
    //  if(minF<-1<<31){ // minF<-1<<31 代表如果minF
    //                minF=nums[i];
    //            }

    /**
     * 功能说明
     * 溢出检测：minF < -1 << 31 检查 minF 是否小于32位整数的最小值（Integer.MIN_VALUE）
     * 重置操作：当发生下溢出时，将 minF 重置为当前元素值 nums[i]
     * 详细解释
     * 位运算 -1 << 31：
     * -1 的二进制表示为全1：11111111111111111111111111111111
     * 左移31位后变为：10000000000000000000000000000000（即 -2147483648）
     * 判断逻辑：
     * 当 minF 小于32位整数最小值时，说明发生了数值下溢出
     * 此时需要重新开始计算，避免错误的累积结果
     * 重置目的：
     * 从当前位置 nums[i] 重新开始维护最小乘积值
     * 确保后续计算的数值在合法范围内
     * 这是为了保证算法在处理大数值乘积时的数值稳定性。
     * @param nums
     * @return
     */

    public int maxProduct_optimize(int[] nums) {
        long maxF = nums[0], minF = nums[0];
        int ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            long mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            if(minF<-1<<31){ // minF<-1<<31 代表如果minF
                minF=nums[i];
            }
            ans = Math.max((int)maxF, ans);
        }
        return ans;

    }

    public static void main(String[] args) {
      //  System.out.println(new maxProductS().maxProduct(new int[]{2,3,-2,4}));
        System.out.println(new maxProductS().maxProduct_optimize(new int[]{5,6,-3,4,-3}));
    }
}
