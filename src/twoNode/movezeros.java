package twoNode;

public class movezeros {
    public static void moveZeroes(int[] nums) {
        /**
         * 一个指针记录head, 一个指针指向head.next,
         * if 有 value = 0 ， 就和非0 的值交换位置
         * if 没有0， 两个指针都向下走一步
         * if 两个指针都是0 ， [1,0,0,3,12],这时候 是swap head and head next, 接着指针走向 i = 1, and i =2, 两个都是0 ， 不交换，
         * i=2, i=3 的时候会交换【1,0,3,0,12】-》【1,0,3,12,0】
         * 如果将两个指针放在相邻的位置，你就需要重新再做上面一轮的for loop 交换
         *】
         * 这个解法不太能解决 问题
         * 官方解法是左指针房子尾巴， 另外一个指针在头部
         * 右指针向右移动， 每次遇到非0 ， 则将左右指针对于的数交换， 同时左指针右移
         * 最后
         * 左指针左边都是非0
         * 右指针左边直到 左指针处都是0
         *
         * [0,1,0,3,12]
         * [12,1,0,3,0]
         * [1,12,0,3,0]
         * [
         *

         */

        if (nums==null){
            return;
        }
        int j = 0 ;
        for (int i =0;i<nums.length;i++){
            //当前元素！=0， 就把这个交换到左边， 等于0 的交换到右边
            //  这就是这个解法的妙处， 将不为0 的全部放左边， 怎么放， 用一个j 其实算是记录当前有多少非0 的数的下标， 这样只是非0 的就交换，逆向思维解决问题
            if (nums[i] != 0){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            j++;

            }
        }

        for (int x =0;x<nums.length;x++){
            System.out.println(nums[x]);
        }

    }
    public static void main(String[] args) {
        /**
         * input = [0,1,0,3,12]
         * output=[1,3,12,0,0]
         *
         *
         */

        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
    }
}
