//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。


public class Main {
    public static  int[] twoSum(int[]
                                        nums, int target) {
        int[] result = new int[2];
        int sizeOfNums = nums.length;

        for(int i=0;i<sizeOfNums;i++){
            for(int j=1;j<sizeOfNums;j++)
            {
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    break;
                }
            }
            if(result.length>0){
                break;
            }
        }

        return result;

    }
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] nums2 = {3,3};
        int[] nums3 = {3,2,6};
        int target3 =6;
        int traget2 = 6;
        int target = 9;
       /* int[] result = twoSum(nums, target);
        int[] res = twoSum(nums2,traget2);*/
        int[] res3 = twoSum(nums3,target3);
        /*System.out.println("Indices: " + result[0] + ", " + result[1]);
        System.out.println("Indices: " + res[0] + ", " + res[1]);*/
        System.out.println("Indices: " + res3[0] + ", " + res3[1]);

    }

}