package array.dp;

import java.util.ArrayList;
import java.util.List;

public class numSquaresS {
    ///  没有思路的时候，可以先用暴力解法， 不需要执着于优化后的
    public int numSquares(int n) {
  // n-i^2
        // 1, 4, 6 16 中不断
        // dp[i] 表示 i 的最小的平方数
        // dp[i] =
        // given n 看看 n本身是否是一个平方数， 如果是的话，那就直接返回1， 如果不是， 那就看看n-1 是不是一个平方数，
        // 如果是，那记录n-1到一个变量中，并且再加上1的， 如果不是，那就考虑n-2
        // 其实可以看出，我们找平方数和的个数， 可以从n一直到1， 一个一个的去找， （为啥不是从中间， 它要求最少数量的， 那么最大值能尽量找出来就找出来）
        // 然后找到一个放到一个数组中 ，然后用n-掉那个数，得到剩余的数， ，那么剩余的数也是面临同样的问题，走上面一样的路， 最终我们返回的是数组的size 就可以
        // 笑死， 实践结果就是，这种方式不是拿到最少数量的平方数

        // dp[i] 记载的是n.n-1...1中能够组成平方数的数
        // dp[i] = 的定义啥
        List< Integer> dp = new ArrayList<>();
        int  rest = n;
        int  current = n;
        while (rest >0) {
            //判断当前数是否是一个平方数
            if (current % Math.sqrt(current) == 0) { // ok now, you actually wanna check 4 after you find 9 is biggest square number, 这个就是一个存储
                dp.add(current);
                rest = rest-current;
                current = rest;
            }else {
                current--;
            }

        }
        for (int i = 0; i < dp.size(); i++) {
            System.out.println(dp.get(i));
        }

        return  dp.size();
    }
    // 高级写法
    //dp[] 不一定使用
    // 使用几个变量也可以用空间复用：需要位置依赖的变量
    // 有些递归在展开计算时候，总是重复调用同一个子问题的解， 这种重复调用的递归变成动态规划收益很大， 如果每次展开的都是不同的解， 或者重复调用的现象很少， 那么就没有必要该动态规划了
    public int numSquares2(int n) {
        int[] dp = new int[n+1];// 用一个dp表记录组成从1-n完全平方数的最小个数
        // 从1-n 一个一个的计算组成i 的平方数的最小个数是多少并且存储到dP[i]中
        for(int i =1;i<n+1;i++){
            int min = Integer.MAX_VALUE;
            // 那么， 就组成i 的平方数肯定在【1，sqrt(i)】之间， 也就是下面的j 的范围， 一个一个的尝试
            for(int j=1; j*j<=i;j++){
                // j*j 是一个完全平方数, i-j*j 就是剩下的 数，并且求出组成这剩下的数完全平方数的的最小个数dp[i-j*j]
                min = Math.min(min,dp[i-j*j]);
            }
            dp[i]=min+1; // i 的最小个数 = 剩下的数最小个数 +1
        }

        return dp[n];
    }
        // 四平方定理
    // 一个数通过数学验证， 最多可以被4个完全平方数之和， 所以0,1,2,3，4 对应不同的情况
    // 1. 自己就是一个完全平方数， 那么组成这个数的最少的完全平方数就是1
    // 2. x*x+y*y=n完全平方数之和， 那么组成这个数最短的完全平方数就是2
    // 3. 4^k*(8m+7) =n, 也就是n 最少由4个完全平方数之和
    // 4. 否则， 就剩下的由3个完全平方数之和
    public int numSquares3(int n) {
        // 判断是否为完全平方数

        if(isPerfectSquare(n)){
            return 1;
        }

        //判断是否能表示为 4^k*(8m+7)
        while(n%4 == 0){ // 4的倍数， 就一直除4
            n/=4;
        }
        if(n%8==7){
            return 4;
        }

        // 剩下
        for (int i = 1; i*i<=n ; i++) {
            int j = n-i*i;
            if(isPerfectSquare(j)){
                return 2;
            }

        }

        return 3;
    }
    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        numSquaresS ns = new numSquaresS();
        System.out.println(ns.numSquares3(16)); //4, 4, 4
    }
}
