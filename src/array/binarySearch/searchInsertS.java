package array.binarySearch;

public class searchInsertS {
    //写到这我也是气笑了， 我刚刚学完二分法的方式，总结后就立马过来写leetcode 上最简单的的题， 结果还是死了， 然后我去看左神的算法课，发现他刚刚讲的后面就是这个题
    // 还有为什么只是题目稍微改了一下我就不知道了， 我想还是写的太少了
    public static int searchInsert(int[] nums, int target) {
        if (nums ==null || nums.length==0){
            return 0;
        }

        int n = nums.length;
        int l = 0;
        int r = n-1;

        int ans = n;// 之所以设置为n 是为了没有找到的情况下，并且target>num[n]中所有的数字的时候，放在最右边，所以这个n不是随便设置的


        while(l<=r){
           int m = (l+r)/2;
            if (nums[m]>=target){
                //3,6,6,7,9,13,15,19
                //0,1,2,3,4, 5, 6,7
                // 5

                ans = m; //为啥不能这么写呢 3>5  target就要放在右边, 这是上一次>target的时候留的m , 如果==，那么就替代上次m 的位置，因为这个比三大比6小， 那么就取代6的位置
                //为什么这因为这个只会发生两次，要么大于 要么=，并且只会每一个情况都只有一次，
                // >=num的最右边数
                r= m-1;
            }else{
                //{1,2,3,4,5,6},5
                // 第一次m =3, 那target在m 的右边， 那么右移l
               // ans = m; 为啥不能这么写呢
                l=m+1;
            }

        }
        return ans;

    }
    // O(Log2^n)的复杂度
    public static int searchInsert_最左_zuiyou(int[] nums, int target) {
        if (nums ==null || nums.length==0){
            return 0;
        }

        int n = nums.length;
        int l = 0;
        int r = n-1;

        int ans = -1;// 之所以设置为n 是为了没有找到的情况下，并且target>num[n]中所有的数字的时候，放在最右边，所以这个n不是随便设置的
        // ans =-1
         // mid>=num ans=mid, r=m-1;
        //mid < l=m+1
        while(l<=r){
            int m = (l+r)/2;
            if (nums[m]>=target){
                //>=target 最左边位置，
                //3,6,6,7,9,13,15,19
                //0,1,2,3,4, 5, 6,7
                // 5
                // >=5 的最左边的数字是6， 也就是位置1， 也就是从位置1后面向右所有的数字都比target 大
                // 如果是<= target 最右边的位置
                //这里的答案就是 3，第一位数，本质上是看这个数字target 能放在哪个位置上如果数组里没有找到的话

                ans = m; //为啥不能这么写呢 3>5  target就要放在右边, 这是上一次>target的时候留的m , 如果==，那么就替代上次m 的位置，因为这个比三大比6小， 那么就取代6的位置
                //为什么这因为这个只会发生两次，要么大于 要么=，并且只会每一个情况都只有一次，
                //
                r= m-1;
            }else{
                //{1,2,3,4,5,6},5
                // 第一次m =3, 那target在m 的右边， 那么右移l
                // ans = m; 为啥不能这么写呢
                l=m+1;
            }

        }
        return ans;

    }
    public static void main(String[] args) {
       /* 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

        请必须使用时间复杂度为 O(log n) 的算法。



        示例 1:

        输入: nums = [1,3,5,6], target = 5
        输出: 2
        示例 2:

        输入: nums = [1,3,5,6], target = 2
        输出: 1
        示例 3:

        输入: nums = [1,3,5,6], target = 7
        输出: 4*/

      //  System.out.println(searchInsert(new int[]{1,3,5,6},7));
       // System.out.println(searchInsert(new int[]{1,3,5,6},2));
       //System.out.println(searchInsert(new int[]{1,2,3,4,5,6},5));
       // System.out.println(searchInsert(new int[]{1,3,5,6},0));
      // System.out.println(searchInsert(new int[]{1},2));
       System.out.println(searchInsert_最左_zuiyou(new int[]{3,6,6,7,9,13,15,19},5));



    }
}
