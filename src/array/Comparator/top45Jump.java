package array.Comparator;

/**
 * 局部最优解-贪心
 * 【5,2,1,8,0,4,0,0,0,0,1,1】
 * 0步 跳 0 步
 * 1步 跳0-5 下标
 * 2步 跳多远 也就是第1步 中跳的下标中i+num[i]的最大值
 * 3步 第二步中最大的i+nums[i]
 *
 */
public class top45Jump {
    public static int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {//走到右边界的时候
                end = maxPosition; //记录上次到达的最大位置
                steps++;
            }
        }
        return steps;

    }
    public static int jump2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            if (i < end) {//走到右边界的时候
                end = maxPosition; //记录上次到达的最大位置
                steps++;
            }
            maxPosition = Math.max(maxPosition, i + nums[i]);
        }
        return steps;

    }
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
