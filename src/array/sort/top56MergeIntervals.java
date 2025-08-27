package array.sort;

import java.util.Arrays;

public class top56MergeIntervals {
    public static  int[][] merge(int[][] intervals) {
        int length = intervals.length;
        if (length==0 || intervals==null){
            return new int[0][0];
        }

        //排序每个子【1,2】中的第一个数
        //intervals = [[1,3],[7,6],[2,6],[8,10],[15,18]]
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);//谁小谁放前面
        //排序后就变成[[1,3],[2,6],[7,6],[8,10],[15,18]] n row 2 coLumn的二维数组


        //排序后我们开始合并，如何合并呢，就看intervals[i][0]也就是每行第一个数字是否>前面的最后数字intervals[i][1]
        int size = 0;//记录返回的合并区间大小
        int s= intervals[0][0];//初始话一下合并区间的start
        int e = intervals[0][1];// end
        for (int i =1;i<length;i++){
            if (intervals[i][0]>e){
                //说明新的区间需要开辟
                intervals[size][0]=s;
                intervals[size++][1]=e;
                s= intervals[i][0];
                e=intervals[i][1];
            }else{
                e  =Math.max(e,intervals[i][1]);
            }
        }
        //加上最后一组
        intervals[size][0]=s;
        intervals[size][1]=e;
        return Arrays.copyOf(intervals,size);

    }
    public static void main(String[] args) {

    }
}
