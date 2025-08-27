package 递归;

public class getMaxinArray {
    //暴力写法
    public static int getMax(int[] nums){
        // to do 边界check
        // java 中整形的最小值 怎么写
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
          max = Math.max(nums[i], max);
        }

        return max;
    }

    // 递归写法
    //找到左边【l..r]的最大值 也就是l-middle
    // 找到右边的【mid+1,r]的最大值
    //然后取左边的最大值和右边最大值的最大值
    public static int getMaxRecursion(int[] nums, int l, int r){
        if (l == r) {
            return nums[l];
        }
        int mid = (l + r) / 2;
        int leftMax = getMaxRecursion(nums, l, mid);
        int rightMax = getMaxRecursion(nums, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }
    public static void main(String[] args) {

    }
}
