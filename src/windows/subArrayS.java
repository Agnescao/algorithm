package windows;

import java.util.LinkedList;

public class subArrayS {
    public static int subarraySum(int[] nums, int sum) {
        if (nums ==null || nums.length==0 || sum<0){
            return 0;
        }

        int R = 0;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            while (R<N){
                while (!maxWindow.isEmpty() && nums[maxWindow.peekLast()]<nums[i]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(i);
                while(!minWindow.isEmpty() && nums[minWindow.peekLast()]>nums[i]){
                     minWindow.pollLast();
                }
                minWindow.addLast(i);
                if (maxWindow.peekFirst()-minWindow.peekLast()>sum){
                    break;
                    //[L...R(初次不达标的时候就停止)
                }else{
                    R++;
                }

            }
            count +=R-i;
            // 如果l向左移那么maxwindow的最左边要被移动到窗口外
            if (maxWindow.peekFirst()==i){
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst()==i){
                minWindow.pollFirst();
            }

        }
        //R 所有的l 公用一个r

            return count;


    }
    public static void main(String[] args) {



    }
}
