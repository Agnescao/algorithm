package 位运算;

public class print {

    public static void printValue(int num)
    {
        for (int i = 31; i >=0 ; i--) {
            System.out.print((num & (1<<i))==0?"0":"1");

        }
        System.out.println();
    }
    public static void main(String[] args) {
       // num<< i, 左移i 位置，每移一次相当于乘以2，那移了i 次就是2的i 次幂
       int test = 1;
      // printValue(test<<1);
       // 最高位是符号位，java 中-2……32 -2……32-1位数
       //反码和补码
        int b =1234567;
        int c = ~b;
        printValue(b);
        printValue(c);

        printValue(c|b);
        printValue(c&b);
        printValue(c^b);

    }
}
