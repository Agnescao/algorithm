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

        //用一个tail 数组存储严格递增子序列， 数字从nums 中取得
        int[] tails = new int[nums.length];
        // res 代表tails 递增子序列中长度
        int res = 0;
        // 核心思想是利用二分法快速找到新元素的位置在哪里，而不是线性的循环从头到尾，这个才是真正优化的点，从上面dp o(n^2) 到 o(nlogn)
        for(int num : nums) {
            //i 是tails 左边界， j 是右边界， 一开始遍历nums i j 都是从头开始也就是0
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2; // 找到tails 有效长度的中点
                if(tails[m] < num) i = m + 1; //判断中点的位置是否比现在nums当前的数字小， 如果是就可以把tails 左边界i 往中点后移一位，
                else j = m; //否则就是把右边界往前移到中点上
            }
            tails[i] = num; // 这个很奇妙， 经过上面一些列的while 对比， i 的位置一定是新add num 的下标，不管是替换还是newly add，
            if(res == j) res++; // 这里如果走了上面的else j 就和res != 那么说明tails[m]>=num,那么 tails 只是替换了j 位置的数，而不是新增
        }
        return res;
    }
    //二分法
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,4,2,1,3,5,4,2,5,1};
        //System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS_binary_search(new int[]{10,9,2,5,3,7,21,18}));

    }
}
