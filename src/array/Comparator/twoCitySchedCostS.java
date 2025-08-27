package array.Comparator;

import java.util.Arrays;

/**
 *
 * 1029. 两地调度
 * 公司计划面试 2n 人。给你一个数组 costs ，其中 costs[i] = [aCosti, bCosti] 。第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti 。
 *
 * 返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。
 *
 *
 *
 * 示例 1：
 *
 * 输入：costs = [[10,20],[30,200],[400,50],[30,20]]
 * 输出：110
 * 解释：
 * 第一个人去 a 市，费用为 10。
 * 第二个人去 a 市，费用为 30。
 * 第三个人去 b 市，费用为 50。
 * 第四个人去 b 市，费用为 20。
 *
 * 最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
 * 示例 2：
 *
 * 输入：costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * 输出：1859
 * 示例 3：
 *
 * 输入：costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
 * 输出：3086
 */
public class twoCitySchedCostS {
    public static int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        int[] arr = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i]=costs[i][1]-costs[i][0]; //每一个人从a 城市到b 城市需要补的差价
            sum +=costs[i][0]; //假设所有人都去了a 城市的费用
        }
        Arrays.sort(arr);//补最小的差价，还需要保证一半的人去a 一半的人去b 就排序
        for (int i = 0; i < arr.length/2; i++) {
            sum+=arr[i];//一半的人从a 到b 城市补的差价

        }
        return sum;
    }
    public static void main(String[] args) {

    }
}
