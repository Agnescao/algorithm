package array.Comparator;

import java.util.Arrays;

public class top179largestNumber {
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (y + x).compareTo(x + y));
        if (strs[0].equals("0"))
            return "0";
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();
    }
    public static void main(String[] args) {
        int[] nums = new int[]{10,2};
        int[] nums2 = new int[]{3,30,34,5,9};
        System.out.println(largestNumber(nums2));
    }
}
