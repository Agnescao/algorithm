package windows;

import commons.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

// 这种解法会超时 O(N*K)
public class maxSlidingWindowS {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int lengthOfArr = nums.length;
        List<Integer> maxValueList = new ArrayList<>();
        if (nums==null){
            return new int[0];
        }
        int maxValue = nums[0]; //有可能是负数，所以这个0 不行,暂时用第一个元素代替
        if (lengthOfArr<k){
           for (int i =0;i<lengthOfArr;i++){
               maxValue = Math.max(maxValue,nums[i]);
           }
           return new int[]{maxValue};
        }

        // length > k

        for(int i=0;i<=lengthOfArr-k;i++){
            int currWindowMax = nums[i];
            int j = 0; // 记录循环几次， 三次就跳出
            while (true) {
                if (j >= k) {
                    break;
                }
              currWindowMax = Math.max(currWindowMax, nums[i+j]);
                j++;
            }
            maxValueList.add(currWindowMax);


        }

        return maxValueList.stream().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) {
        /**
         * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
         * 输出：[3,3,5,5,6,7]
         */

        int[] nums = {1,3,-1,-3,5,3,6,7};
        ArrayUtils arrayUtils = new ArrayUtils();
        arrayUtils.printFixedArray(maxSlidingWindow(nums,3));
    }
}
