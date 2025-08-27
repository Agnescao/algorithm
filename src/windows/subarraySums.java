package windows;

import java.util.HashMap;
import java.util.Map;

 // hashmap count 省空间和时间复杂度
//
public class subarraySums {
    public static  int subarraySum(int[] nums, int k){
        Map<Integer, Integer> sumCount = new HashMap<>();
        //第一位放到map
       // sumCount.put(nums[0],1);
        sumCount.put(0,1); // [1,1,1] 特殊情况
        //[1,-1,0]
        int pre = 0;
        int count = 0;
        for (int i = 0; i < nums.length; ++i){
            pre +=nums[i];
            if(sumCount.containsKey(pre-k)){
               count+=sumCount.get(pre-k);

            }

            sumCount.put(pre,sumCount.getOrDefault(pre,0)+1);
            //putIfAbsent 是没有值就会放， 有值就不动， 这里不能用， 因为value需要实时更新
        }

        return count;
    }
    public static void main(String[] args) {

        /**
         * input[1,-1,0]
         * output
         */
        int[] nums = new int[]{1,-1,0};
        System.out.println(subarraySum(nums,0));
    }
}
