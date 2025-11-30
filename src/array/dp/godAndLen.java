package array.dp;


// 最大公约数

/**
 * ## 最大公约数 (GCD - Greatest Common Divisor)
 *
 * - **定义**：两个或多个整数共有约数中最大的一个
 * - **示例**：`gcd(12, 18) = 6`，因为12和18的公约数有1, 2, 3, 6，其中6是最大的
 * - **用途**：常用于分数化简、数论计算等场景
 * - **计算方法**：通常使用欧几里得算法（辗转相除法），如代码中的 `gcd` 函数实现
 *
 * ## 最小公倍数 (LCM - Least Common Multiple)
 *
 * - **定义**：两个或多个整数公有倍数中最小的一个
 * - **示例**：`lcm(4, 6) = 12`，因为4和6的公倍数有12, 24, 36...，其中12是最小的
 * - **用途**：常用于分数运算、周期性问题求解等
 * - **计算方法**：可通过公式 `lcm(a,b) = (a * b) / gcd(a, b)` 计算
 *
 * ## 两者关系
 *
 * 最大公约数和最小公倍数满足以下重要关系：
 * ```
 * a * b = gcd(a, b) * lcm(a, b)
 * ```
 *
 *
 * 这在代码中可以通过已实现的 `gcd` 方法来计算最小公倍数。
 */


public class godAndLen {


    // 最大公约数
    public static int gcd(int a, int b) {
        if(b==0){return  a;}

        return gcd(b, a%b);
    }

    //最小公倍数
    public static int lcm(int a, int b) {
        return a /gcd(a,b) *b;
    }

    // 求 ((a+b)*(c-d)+(a*c-b*d))%mod 的非负结果 ,
    public static int mod( long a, long b, long c, long d, long mod) {
        int o1= (int) (a% mod); // a% mod
        int o2= (int) (b% mod);
        int o3= (int) (c% mod);
        int o4= (int) (d% mod);
        int o5 = (int) ((o1+o2)% mod); // (a+b)
        int o6 = (int) ((o3-o4+mod)% mod); // (c-d)
        int o7 = (int) (((long)o1*o3)%mod); //a *c
        //b*d
        int o8 = (int) (((long)o2*o4)%mod);
        //(a+b)*(c-d)
        int o9 = (int)(((long)o5*o6)%mod);
        //(a*c-b*d)
        int o10 = (int) ((o7-o8+mod)%mod);
       // ((a+b)*(c-d)+(a*c-b*d))%mod
        int ans = (int) ((o9+o10)% mod);
        return ans;
    }
    public static void main(String[] args) {
        //计算 ((a+b)*(c-d)+(a*c-b*d))%mod 的非负结果

    }
}
