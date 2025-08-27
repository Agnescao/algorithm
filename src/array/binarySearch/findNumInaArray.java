package array.binarySearch;

public class findNumInaArray {
    public static  boolean exist(int[] nums, int num){
        if (nums ==null || nums.length==0) return false;

       int l=0;
       int r=nums.length-1;
       int m=0 ;
       while(l<=r){
        //   m = (l+(r-l+1))/2;
           m= l+((r-l)>>1); // r-l >>1  代表r-l 这个长度缩短了一半， 位运算 也就是从l 的位置到 r中间的一半
           if (nums[m]==num){
               return true;
           }else if (nums[m]<num){

               //说明 num 比这个有序数组的中的还要大，那么这个数num 只能在这个有序数组的右边（l-r）
               // 更加二分法的左边界到中点
               /// xxxxxxxx 150 xxxxxx m
              // 200
               l = m+1;

           }else{
               //说明这个数在左边界上，并且是闭区间【】
               //更新右边界到中的
               //nums[m]> num
               // .......150 ......
               //  100
               r= m-1;

           }
       }

       return false;

    }
    public static void main(String[] args) {

        int[] nums = new int[]{2,33,55,66,150,170,178,190};
        int num = 46;
        System.out.println( exist(nums,num));
    }
}
