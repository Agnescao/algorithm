package array.Comparator;

public class maxProfitS {
    public static int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit =0;
        for (int i = 0; i < prices.length; i++) {
           if (prices[i]<minprice){
               minprice=prices[i]; //如果当前的price <minprice就跟新
           }else if (prices[i]-minprice>maxprofit){//否则就计算maxprofit,并且当前计算出来的profit >maxprofit就跟新当前的maxprofit
               maxprofit=prices[i]-minprice;
           }
        }
        return maxprofit;
    }
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
