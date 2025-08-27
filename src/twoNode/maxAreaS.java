package twoNode;

public class maxAreaS {
    public static int maxArea(int[] height) {
        /**
         * 输入：[1,8,6,2,5,4,8,3,7]
         * 输出：49
         * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
         * 示例 2：
         *
         * 输入：height = [1,1]
         * 输出：1
         */

        if (height.length<=1){
            return 0;
        }
        if (height.length==2){
            return Math.min(height[0],height[1]);
        }

        int maxArea = 0;
            //这个解法会timeout,o(n), 需要优化
       /* for (int i = 0; i < height.length-1; i++) { //i =7的时候， j=8< lenght of height 9
            for (int j = i+1; j < height.length; j++) {
               int shortest = Math.min(height[i],height[j]);
               maxArea = Math.max(maxArea,shortest*(j-i));
            }


        }*/
        // 使用双指针
        // 一开始指向左右端，如果左端的值小， 那么我们就移动左指针到右， 是为了寻找更高的height, 同时计算area
        // 那什么时候移动结束能

        int l= 0;
        int r = height.length-1;
        while(l<r){
            int area = Math.min(height[l],height[r])*(r-l);
            maxArea = Math.max(maxArea,area);
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
        }

        return maxArea;

    }
    public static void main(String[] args) {

        /**
         * 输入：[1,8,6,2,5,4,8,3,7]
         * 输出：49
         * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
         * 示例 2：
         *
         * 输入：height = [1,1]
         * 输出：1
         */


        int[] test = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(test));

    }
}
