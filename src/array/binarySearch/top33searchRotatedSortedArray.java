package array.binarySearch;

/**
 * 33. 搜索旋转排序数组
 *
 * 整数数组 nums 按升序排列，数组中的值 互不相同
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 向左旋转 3 次后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 *
 *
 */

public class top33searchRotatedSortedArray {
    //这里就是让你把旋转后的数组恢复到按升序排列的样子再用二分法查找target
    // 那怎么恢复呢
    // 例如， [0,1,2,4,5,6,7] 向左旋转 3 次后可能变为 [4,5,6,7,0,1,2] 。
    // 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    // 那我们向右旋转 3
    // [0,1,2,4,5,6,7,0,1,2,4,5,6,7,0,1,2,4,5,6,7]
    //
    // 这里想到找peak value in an array,然后再一直右移到最末的位置,注意和前面的find peak value index不一样，前面的是 相邻的三个数中的peak,
    // 这里的要求是全局peak
   //复杂度是2n+logn
    public static int searchIndex(int[] nums,int target){
        int minIndex = 0;
        // 有没有可能nums 经历k 轮旋转，它的排序会到一开始的状态，也就是全都是上升状态
        int minValue = nums[0];
        int n = nums.length;
        for (int i = 0; i <n ; i++) {
            if (minValue>nums[i]){
                minIndex=i;
            }
            minValue=Math.min(minValue,nums[i]);

        }
        //已经找到最小数字的index,那么就只需要将数组一直想前移到第一位
        //怎么移，左移，左移的话 第0的数字到n-1的位置， n-1位置的数字到n-2
        for (int i = 0; i < minIndex; i++) {
            //这里把第一位放到最后一位，
            int lastone =nums[n-1]; //用一个变量记一下最后一位是什么
            nums[n-1]=nums[0];//把第一位放到最后一位
            // 这时候int[] nums张这样[4,5,6,7,0,1,4] ，lastone即将替代1的位置


         //          [4,5,6,7,0,1,2]
            //向左一位[5,6,7,0,1,2,4]
            // 其他全部向前移一位
            for (int j = 1; j <n-2 ; j++) {

            }

        }

        return 1;
    }
    //这里不要把他变有序才可以做二分法查询，而是看有多少情况直接进行二分， 主要考察细分各种情况能力
    //[0,1,2,4,5,6,7,0,1,2,4,5,6,7,0,1,2,4,5,6,7]
    // target =0
    public static int searchTargetIndex(int[] nums,int target){
        int n = nums.length;
        int ansIndex = -1;
        int l=0,r=n-1;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        while(l<=r){
            int mid = l+((r-l)>>1);
            if (nums[mid]==target){ansIndex=mid;}
            if (nums[0] <= nums[mid]) {//说明l..m 都是有序的，
                // 找到有序数组边后， 如果target 在l-m中 就二分法之间查左边的
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            //看到下面的各种情况分析，我开始理解，抽象最重要， case by case 就像水一样想的太多没有用
           /* else if (nums[mid]>target){
                //4,5,6,7,0,1,2   target =0
                //l     m     r  这种mid>target, l->m是升序的， m->r也是升序的，那么就有两种情况
                    //if l<m && l>target l-m 递增的，target就不在左边
                if (nums[l]<nums[mid] && nums[l]>target){
                    l=mid+1;
                }
                //这个时候， l来到0的位置继续二分
                // 4,5,6,7,0,1,2   target =0
                       //  l m r
                if (nums[l]<nums[mid] && nums[mid]<nums[r]){ //l m r是递增的
                    r=mid-1;
                }
                //第二种情况 mid>target, l>
                //7,0,1,2,4,5,6, target = 0, mid>target,l>target,  target 在l-m之间， 断点在L-m中间
                if (nums[l]>nums[mid] && nums[mid]<nums[r]) //说明断点在l-m
                {
                    r=mid-1;
                }
                //第三种情况 mid>target
                //6,7,0,1,2,4,5
                //l m r
                //  l r
                if (nums[l]==nums[mid]){
                    l=mid+1;
                }
                // 第四中情况
                //1,2,4,5,6,7,0
                //l     m     r
                //         l m r
                if (nums[l]<nums[mid] && nums[l]>target){//说明target 不在左边只能去右边
                    l=mid+1;
                }
                // 第五种情况
                // 2,4,5,6,7,0,1
                // l     m     r
                //       l m   r
                //         l m r




            }else{
                r=mid-1;
            }*/

        }
        return ansIndex;
    }
    public static void main(String[] args) {
        System.out.println(searchTargetIndex(new int[]{4,5,6,7,0,1,2},0));

    }
}
