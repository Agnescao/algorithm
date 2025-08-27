package array.binarySearch;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 *
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 */
public class findPeakValueIndex {
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        if (nums ==null || n==0) return -1;
        if(n==1){
            return 0;
        }
        // [4, 3,,5] 就可以返回 4 or 5
        //如果长度>=2的情况， 并且第一个元素大， 那么认为第一个元素是一个峰值， 可以默认数组外都是小于头尾的数
        if (nums[0]>nums[1]){
            return 0;
        }
        //最后一个元素大
        if (nums[n-1]>nums[n-2]){
            return n-1;
        }

        // 剩下的情况就是 第0位置 和第二位是上升的趋势， 最后一位的数 都是向下的趋势
        //  [0，5........9 4], 那么中间一定有一个peak不管怎么变
        //   l=1, r= n-2之间 [l,r] 一定有一个峰值
        //  那如何找到这个峰值呢 可以使用二分法
        // mid = l+((r-l)>>1);
        // 对比 mid-1 > mid, yes 说明 [0到l上扬....mid-1，mid下降]
        // [l-r]中间有一个峰值，那就是循环上面的一种,继续二分
        // 那到什么时候返回结果呢? l mid-1
        int l = 1,r=n-2,ans=-1;
        while (l<=r){
            int mid = l+((r-l)>>1);
            if (nums[mid-1]>nums[mid]){ //说明从l-mid中间有峰值，可以左移r 在l-mid 中继续找
                r=mid-1;
            }else if (nums[mid]<nums[mid+1]){ //说明 mid-r 之间有峰值， 可以右移l 从mid-r中寻找新的峰值
                l=mid+1;
            }else {// 上面两种情况都不符合的时候 也就是到了mid-1<mid>mid+1，就找到了一个峰值，返回结果
                ans=mid;
                break;
            }

        }


        return ans;





    }
    public static void main(String[] args) {
        /**
         * * 示例 1：
         *  *
         *  * 输入：nums = [1,2,3,1]
         *  * 输出：2
         *  * 解释：3 是峰值元素，你的函数应该返回其索引 2。
         *  * 示例 2：
         */
        System.out.println(findPeakElement(new int[]{1,2,3,1}));

    }
}
