package array.dp;

import java.util.ArrayList;
import java.util.List;

public class generateTriangle {
    public static  List<List<Integer>> generate(int numRows) {
        // 1. dp[numRows] list<Interger>
        // 2.   numrows =1 1 [1]     numrows =2 [1,1],  numrows =3 [1,2, 1],numrows =4 [1,3,2, 3,1]
        //  2.1 numsrows 数组中的相邻两个数相加得到的结果，并且插入到这相邻两个数的中间
        // 动态规划五要素：问题、性质、公式、初始化、答案
        //问题：f[i][j]代表第i层第j个数的数值，其中i和j均从1开始。
        //性质：数值
        //公式：f[i][j] = f[i - 1][j - 1] + f[i - 1][j]
        //初始化：f[1][1] = 1
        //答案：返回列表
        //TIPS：由于f[i][j]只与前序的上一层数值有关系，可以用滚动数组+逆遍历即可将公式简化为f[i] = f[i - 1] + f[i]
        //
        //作者：Orlando
        //链接：https://leetcode.cn/problems/pascals-triangle/solutions/3809951/yi-wei-shu-zu-dong-tai-gui-hua-jie-yang-b810z/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        // 复杂度
        //时间复杂度: O(N2)
        //空间复杂度: O(N)

        List<List<Integer>> result = new ArrayList<>();
        // 每一层的元素个数等于 numrows+1
        int f[] = new int[numRows + 1];
        // 第一层就是一个元素1
        f[1] = 1;

        // 遍历 numsrow 层
        for (int i = 1; i <= numRows; i++) {
            // 每一层都有一个计算好的tmp array
            List<Integer> temp = new ArrayList<>();
            //每一层的每个元素就可以用dp
            for (int j = i; j >= 1; j--) {
                f[j] = f[j - 1] + f[j];
                temp.add(f[j]);
            }
            result.add(temp);

        }
        return result;
    }
    public static void main(String[] args) {
        // add test case for generate
        List<List<Integer>>  fjds= generate(2);

    }
}
