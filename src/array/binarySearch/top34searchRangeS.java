package array.binarySearch;

/***
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums = [5,7,7,8,8,10],和一个目标值 target=8,请你找出给定目标值在数组中的开始位置和结束位置。
 * 输出：[3,4]
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class top34searchRangeS {
    public static int[] searchRange(int[] nums, int target) {
        if (nums==null ||nums.length==0){
            return new int[]{-1,-1};
        }
        int start=-1,end=-1;
        int l=0,r=nums.length-1;

        while (l<=r){
            int mid = l+((r-l)>>1);
            //这里查找是否重复值，如果有重复值那么就要看
            if (nums[mid]==target){
                //[5,7,7,8,8,10], target = 8, 这里在第倒数第二个位置找到了8，那这个时候就需要从8的那个位置向左看看有多少重复的，
                // 向右看看有多少重复的
                start = mid;
                end=mid; //先假设只有这一个数
                //[l...mid...r] 查看l...mid有多少重复的8，
                // [l,mid] 中查找start
                for(int i = mid;i>=l;i--){
                    // num[i]<num[i+1]=target=num[i+2]..nums[mid]的位置,找到这个i+1
                    if(nums[i]==target){ start = i;}
                }

                // [mid,r]查找end
                for (int i = mid; i <=r ; i++) {
                    if (nums[i]==target){
                        end=i;
                    }
                }
            break;
            }else if (nums[mid]<target){
                l=mid+1;
            }else {
                r=mid-1;
            }
        }


        return new int[]{start,end};
    }
    public static void main(String[] args) {

        //System.out.println(searchRange(new int[]{5,7,7,8,8,10},8));
        int[] res=(searchRange(new int[]{2,2},2));
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+",");
        } //这个就是下中点也是=target怎么解决

    }
}
