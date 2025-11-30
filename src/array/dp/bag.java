package array.dp;

public class bag {
    // 尝试函数- 暴力-》傻缓存-》动态规划
    // 当前考虑到了index h号货物， 自由选择， 选择或不选择， 背包容量为c
    // 边界有效解和无效解， if set
    // 找出可变参数有哪些， 并且确定范围
    // dp【】【】
    // return 什么确定
    // 设计递归
    // 找出依赖

    // maxvalue int[] w, int[], int bag
    public static int process(int[] w, int[] v, int c){
        // 边界条件
        if (v.length == 0 || w.length!=v.length || c <= 0){
            return 0;
        }


        return tryToPut(w, v, 0, c);
    }

    public static int tryToPut(int[] w, int[] v, int index, int c){
        if (index < 0 || c <= 0){
            return -1;
        }
        // 不选择
        int p1 = tryToPut(w, v, index+1, c);
        // 选择
        int p2 = 0;
        int next = tryToPut(w, v, index+1, c-w[index]);
        if (next!=-1){
            p2 = v[index] + tryToPut(w, v, index+1, c-w[index]);
        }
        return Math.max(p1, p2);
    }

    // 使用dp
    // index 0-n
    // rest 负数-bag c
    public static int dp(int[] w, int[] v, int c){
        if (v.length == 0 || w.length!=v.length || c <= 0){
            return 0;
        }

        int[][] dp = new int[w.length+1][c+1];
        for (int i = 0; i <= w.length; i++) {
            for (int j = 0; j <= c; j++) {
                // 不要i 货
                int p1 = dp[i+1][j];
                int p2 =0;
                // 要i货
                int next = j-w[i]>=0?dp[i+1][j-w[i]]:-1;
                if (next!=-1){
                    p2 = v[i]+next;

                }
                dp[i][j] = Math.max(p1,p2);
            }
        }


        return dp[0][c];



    }
    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(dp(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
