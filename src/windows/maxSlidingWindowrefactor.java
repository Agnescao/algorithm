package windows;

import commons.ArrayUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 这种解法会超时,里面做了很多重复的对比，这里可以用双端队列来优化， 刷到这的时候，感觉我有思路可是不知道用什么数据结构来改善时间复杂度
// 双端队列的 pollLast and peekLast都是o(1)的时间复杂度，大大弱化上面的问题
//如果窗口缩小的话， 哪些值会成为最大值


public class maxSlidingWindowrefactor {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 结果会是 lengthofArr-k+1 哥max
        int lengthOfArr = nums.length;
        int[] res = new int[lengthOfArr-k+1];
        int index =0;
        //这里放可能最大值的下标
        LinkedList<Integer> possibleMaxValue = new LinkedList<>();


        for (int R = 0; R < lengthOfArr; R++) {
            //
            while (!possibleMaxValue.isEmpty() && nums[possibleMaxValue.peekLast()]<nums[R]){//Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
                possibleMaxValue.pollLast();
            }
            possibleMaxValue.addLast(R);

            //l 边界如何去除， 是否过期，过期就弹出，
            if (possibleMaxValue.peekFirst() == (R-k)){
                possibleMaxValue.pollFirst(); // 如果双端队列的第一位元素 index == r-k, 那么说这个元素在当前的窗口是过期的，那么就要把它从双端队列中去掉
                //为什么？

            }
            ///窗口形成的条件
            if (R-k>=-1){
              //窗口形成后就可以加入res 中先窗口中最大的值，也就是双端队列最最左边的值
              res[index++]=nums[possibleMaxValue.peekFirst()];
            }



        }

        return res;

    }
    public static void main(String[] args) {
        /**
         * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
         * 输出：[3,3,5,5,6,7]
         */

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] nums2 = {4,3,5,4,3,3,6,7};

        ArrayUtils arrayUtils = new ArrayUtils();
        //arrayUtils.printFixedArray(maxSlidingWindow(nums,3));
        arrayUtils.printFixedArray(maxSlidingWindow(nums2,3));
    }
}
