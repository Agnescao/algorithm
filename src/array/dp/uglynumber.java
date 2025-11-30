package array.dp;

public class uglynumber {
    // 丑数
    // *2, *3,*5 的结果中选择小的那个， 然后得到的新的小的丑数和之前某个下标乘以2,3,5的数的结果相同， 那么它就过期了， 不可以再用了，然后就把指针往下移动
    public static int nthUgly(int n) {

        // 定义一个数组，用来保存丑数
        int[] dp = new int[n+1]; //0位置不用的
        dp[1]=1;
        int a,b,c; // 分别记录乘以2,3,5的数得到的结果
        // 创建三个指针，分别指向乘以2,3,5的数 的下标
        for (int i = 1, i2 = 1, i3 = 1,i5=1,newUgly; i < n+1; i++) {
            a = dp[i2]*2;
            b = dp[i3]*3;
            c = dp[i5]*5;
            newUgly = Math.min(a,Math.min(b,c));
            if (newUgly==a){
                i2++;
            }
            if(newUgly==b){
                i3++;
            }
            if (newUgly==c){
                i5++;
            }

            dp[i]=newUgly;

        }

        return dp[n];

    }
   // 好差的代码，好丑
    public boolean isUgly(int n) {

        int[] uglylist =new int[]{2,3,5};

        boolean ans =false;
        int rest =n;
        boolean ismod0= false;
        while(true){
            if(rest==1){ismod0= true; break;}
            if (rest%2==0) { rest = rest/2;}
            if ( rest%5==0) { rest = rest/5;}
            if(rest%3==0)  { rest = rest/3;}
        }
        return ismod0;
    }
    // new version

    /**
     *
     * @param n
     * @return
     *首先，如果 n≤0，不符合题目正整数的要求，返回 false。
     * 去掉 n 中的因子 3，也就是不断把 n 除以 3，直到 n 不是 3 的倍数为止。
     * 去掉 n 中的因子 5，也就是不断把 n 除以 5，直到 n 不是 5 的倍数为止。
     * 最后，n 必须只剩下因子 2，即 n=2
     *
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/ugly-number/solutions/1/li-yong-wei-yun-suan-you-hua-pythonjavac-nlqr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * `(n & (n - 1)) == 0` 是一个经典的位运算技巧，用于**判断一个数是否为2的幂次方**。
     *
     * ## 原理说明
     *
     * - 对于2的幂次方数，其二进制表示中只有一个位为1，其余位都为0
     * - 当对该数减1时，会将该位之后的所有位都变为1
     * - 将原数与其减1后的数进行按位与运算，结果必然为0
     *
     * ## 示例验证
     *
     * ```java
     * n = 8  (二进制: 1000)
     * n-1 = 7 (二进制: 0111)
     * n & (n-1) = 1000 & 0111 = 0000 = 0
     *
     * n = 6  (二进制: 0110)
     * n-1 = 5 (二进制: 0101)
     * n & (n-1) = 0110 & 0101 = 0100 = 4 ≠ 0
     * ```
     *
     *
     * ## 应用场景
     *
     * 这种写法常用于：
     * - 快速判断是否为2的幂次方（比循环除法更高效）
     * - 位运算优化场景
     * - 算法题中的性能优化技巧
     */
    public boolean isUgly2(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return (n & (n - 1)) == 0;


    }
    public static void main(String[] args) {

    }
}
