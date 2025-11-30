package apple;

import java.util.ArrayList;
import java.util.List;

public class Fancy {


        private List<long[]> list; //为什么使用long[]?

        private int len, mod = (int) 1e9 + 7; //

        public Fancy() {
            this.list = new ArrayList<>();
            this.len = 0;
        }

        public void append(int val) {
            list.add(new long[]{val, 1, 0, len});
            len++;
        }

        public void addAll(int inc) {
            if (len == 0) return;
            list.get(len-1)[2] = (list.get(len-1)[2] + inc) % mod;
        }

        public void multAll(int m) {
            if (len == 0) return;
            list.get(len-1)[1] = (m * list.get(len-1)[1]) % mod;
            list.get(len-1)[2] = (m * list.get(len-1)[2]) % mod;
        }

        public int getIndex(int idx) {
            if (idx >= len) return -1;
            long[] ints = dfs(list.get(idx));
            long[] end = idx == len-1 ? new long[]{0, 1, 0} : list.get(len - 1);
            long a = (ints[1]*end[1]) % mod;
            long b = (((end[1]*ints[2]) % mod) + end[2]) % mod;
            return (int)(((ints[0]*a) % mod) + b) % mod;
        }

        private long[] dfs(long[] val) {
            int preInx = (int) val[3] + 1;
            if (preInx >= len-1) return val;
            long[] pre = dfs(list.get(preInx));
            val[1] = (val[1]*pre[1]) % mod;
            val[2] = (((val[2]*pre[1]) % mod) + pre[2]) % mod;
            val[3] = pre[3];
            return val;
        }

    public static void main(String[] args) {
       // System.out.println(Integer.MAX_VALUE);
        //2147483647
        //1000000
        //["Fancy","append","addAll","append","multAll","getIndex","addAll","append","multAll","getIndex","getIndex","getIndex"]
        //[[],[2],[3],[7],[2],[0],[3],[10],[2],[0],[1],[2]]
        Fancy f = new Fancy();
        f.append(2);
        f.addAll(3);
        f.append(7);
        f.multAll(2);
        System.out.println(f.getIndex(0));
        f.addAll(3);
        f.append(10);
        f.multAll(2);
        System.out.println(f.getIndex(0));
        System.out.println(f.getIndex(1));
        System.out.println(f.getIndex(2));


    }
}
