package 递归;

public class Robotwalk {
    public static int ways(int N, int start, int aim, int K){
        return process1(start,aim,K,N);
    }
    //

    /**
     *
     * @param curr 机器人来到当前位置curr
     * @param rest 机器人还剩多少步要走
     * @param aim  目标位置
     * @param N    数组的总长度，这个是不变的
     * @return  机器人走的路径不同种的数量
     * 机器人从curr 位置出发， 走过rest步之后， 来到aim 位置， 的总方法数
     */
    private static int process1(int curr, int rest, int aim, int N) {
        //base case 是什么， 一定是我们已经走了aim 位置并且已经走过了rest步
        if (rest==0){
            return curr==aim?1:0;
        }
        //边界条件1：机器人走到第一个位置的时候， 还没有到达aim, 接下来他只能向右走了，并且步数只剩下rest-1
        if (curr==1){
            process1(2,rest-1,aim,N);
        }
        //同理以上
        if (curr==N){
            process1(N-1,rest-1,aim, N);
        }
        //curr 在中间状态的时候，既可以向左边走，也可以向右边走
        return process1(curr-1,rest-1,aim,N)+process1(curr+1,rest-1,aim,N);
    }

    //优化过程
    public static int ways2(int N, int start, int aim, int K){
        int[][] dp = new int[N+1][K+1];
        //初始化一下dp
        for (int i = 0; i < N+1; i++) {
            for (int j=0;j<K+1;j++){
                dp[i][j]=-1;
            }
        }
        return process2(start,aim,K,N,dp);
    }
    /**
     * 你会发现上面的aiM 和n 参数是并没有什么变化的，process 的返回值并没有和他们有很多的关系，
     * 第二个是我们在路径走访上肯定重复出现的路径， 也就是curr rest的path
     * 这时候可以将上面的curr rest 存储到一个dp[curr][rest]表中
     *
     * @param
     */
    public static int process2(int curr, int rest, int aim, int N,int[][] dp){
        if (dp[curr][rest]==-1){
            return dp[curr][rest];
        }

        //没有走过的路径
        int ans =0 ;//记一下当前路径的ans
        if (rest ==0){
            ans=curr==rest?1:0;
        }else if (curr==1){
            ans = process2(2,rest-1,aim,N,dp);
        }else if (curr==N){
            ans = process2(N-1,rest-1,aim, N,dp);

        }else{
            ans =  process2(curr-1,rest-1,aim,N,dp)+process2(curr+1,rest-1,aim,N,dp);
        }

        dp[curr][rest]=ans;// 这个是1 如果走过这个路径就会从初始值-1 到1
        return ans;
    }

    /**
     * 记忆搜索，进一步优化， dp[curr][rest]
     * @param
     */

    public static int ways3(int start, int K, int aim, int N){
        int[][] dp = new int[N+1][K+1];
        dp[aim][0]=1;

        for (int rest = 1; rest <=K; rest++) {
           dp[1][rest]=dp[2][rest-1];
           for(int cur = 2;cur<N;cur++){
               dp[cur][rest]=dp[cur-1][rest-1]+dp[cur+1][rest-1];
           }
           dp[N][rest]=dp[N-1][rest-1];
        }

        return dp[start][K];//?
    }

    public static void main(String[] args) {
       // System.out.println(ways(4,2,4,4));
        System.out.println(ways2(4,2,4,4));
    }
}
