package hash;

import java.util.*;
import java.util.stream.Collectors;

public class longestSerial {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));

        int longestLength = 0;
        //  1, 2, 3, 4 , 100, 200
        /**
         *  1 是 头吗， 有0 吗， 没有， 就看2， 3， 4, 有就记数
         *  直到5没有，那就跳出计数
         */
       for (int num: hashSet) {
           int currentLength = 0;

           if (!hashSet.contains(num-1)){
               while (hashSet.contains(num++)){
                   currentLength++;
               }


               longestLength = Math.max(currentLength,currentLength);// 巧妙使用math.max 找最大值，一般都是一个while结束， 这样就可以找到最大值，而不需要为每一个长度存储，减少空间

           }

       }

        return longestLength;


    }
    public static void main(String[] args) {
        /**
         * input not sorted integer arr nums[]
         * output : longest serial numbers
         *
         * note: time complexity O(n)
         *
         * example:
         * input
         * nums[100,4,200,1,3,2]
         * output :4
         * reason: longest serial numbers [1,2,3,4] length of that is 4
         *
         * input
         * [0,3,7,2,5,8,4,6,0,1]
         * output: 9
         * [0,0,1,2,3,4,5,6,7,8]
         *
         * 这个不能排序才能找到最长，所以排序不一定是最长的， 那什么时候不排序就是最长的，有重复值的，
         * 如果没有重复值，就直接排序
         * 那如何找到序列中是否有重复值呢？ 可以使用hashmap<Interger,Integer>
         *     0, i++
         *     1  2
         *     2  1
         *     然后从key 中查找，
         *     这里我可以使用hashset, 因为hashset是唯一值的存在
         * </Interger,Integer>
         * [1,0,1,2]
         * [0,1,1,2]
         * 3
         */


        /**
         * 那如何从hasHset中找到最长的连续值呢？
         * 连续值的特征是什么， 依次递增
         * 那如何找到递增的length
         * 从第一个元素，开始遍历，一直记到不再递增为止， 有个length
         * 第二个元素开始， 开始遍历，     有个length
         * 一直到最后           length,
         *
         * 然后求出最大值
         *
         * 不过这个time complextity 是o(n……2)
         * 不符合要求，需要优化
         *
         * 一般是不是去掉重复值之后
         * 【1,0,1,2】-》【0,1,2】
         */

        int[] nums = {100,4,200,1,3,2};


        // 打印结果
        System.out.println( longestConsecutive(nums));

    }
}
