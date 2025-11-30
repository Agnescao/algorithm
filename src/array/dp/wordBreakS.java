package array.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class wordBreakS {
    // o(n^2) 两个for 循环,怎么优化呢？ 这里的worddict 是固定的，可以check依次 wordDict中是否在s 中找到匹配的字符串
    public boolean wordBreak_low_performance(String s, List<String> wordDict) {
        // 找出worddict 中能够match s 中的字符串， 怎么校验， wordDict 当前的word 是否 contain s中的某段string
        // dp[i] 代表s [i]位置的往左看到上一个是true 的位置是否能够在worddICT中找到对应的word, 如果可以就标志=1 如果不行=0；// 如果上一个位置是true， 那么可以不用往前看， 因为前面已经形成了一个单词， 我需要继续往后看， 当前位置的为结尾的单词是否找到word,
        // 最后返回的是dp[n]位置的值，
        int n = s.length();
        int[] dp = new int[n+1]; // ok 这里设置dp length 不是n 而是n+1的原因是前面null的位置可以作为22行的substring左边界,要不然很难对其substring
       // char[] chars = s.toCharArray();

        for(int i=1;i<=n;i++){ // dp[0]不放东西， 那么i就要走到n位置

            // 以i 位置结尾的往左看到上一个是true 的位置是否能够在worddICT中找到对应的word
            for (int j = 0; j < i; j++) {
                // 一直往左看， 直到j==0 or dp【j】=1也就是上一个位置是true
                // 这里放弃使用substring 改用将string 转换成char[]的原因是
                // substring (0,1) ='l' , char[] 中substring(0,2) ='le'， 0 位置是没有东西的，这样和dp[]不是很匹配不是很好对答案
                // 不过使用char[]虽然下标很好拿，可是不好拿substring ,最后定使用substring
                if((dp[j]==1 || j==0)&& wordDict.contains(s.substring(j,i))){dp[i]=1; break;}
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(dp[i]+" ");
        }
        return dp[n]==1?true:false;

    }

    // 优化思路：
    // 1. 找出worddict 中能够match s 中的字符串， 怎么校验， wordDict 当前的word 是否 contain s中的某段string
    // 2. dp[i] 代表s [i]位置的往左看到上一个是true 的位置是否能够在worddICT中找到对应的word, 如果可以就标志=1 如果不行=0；// 如果上一个位置是true， 那么可以不用往前看， 因为前面已经形成了一个单词， 我需要继续往后看， 当前位置的为结尾的单词是否找到word,
    // 3. 最后返回的是dp[n]位置的值，
    //`j >= Math.max(0, i - maxlen)` 这个条件是用来优化动态规划算法中内层循环的边界条件。让我详细解释：
    //
    //## 作用说明
    //
    //这是一个**剪枝优化**条件，用于减少不必要的循环次数。
    //
    //## 参数含义
    //
    //- `i`: 当前正在处理的位置（目标位置）
    //- `j`: 内层循环的起始位置（尝试分割点）
    //- `maxlen`: 字典中最长单词的长度
    //
    //## 优化原理
    //
    //1. **限制搜索范围**:
    //   - 无需从字符串开头 `0` 位置开始检查
    //   - 只需要检查从 `i - maxlen` 位置开始的子串
    //   - 因为超过 `maxlen` 长度的子串不可能在字典中找到匹配
    //
    //2. **边界保护**:
    //   - `Math.max(0, i - maxlen)` 确保起始位置不小于0
    //   - 防止数组越界访问
    //
    //## 示例说明
    //
    //假设:
    //- `s = "leetcode"`
    //- `maxlen = 4` (字典中最长词长度)
    //- 当 `i = 6` 时，只需检查位置 `Math.max(0, 6-4) = 2` 开始的子串
    //- 避免了检查 `"leetc"`, `"leetco"` 等超过最大长度的无意义子串
    //
    //这种优化将时间复杂度从 O(n²) 降低到 O(n × maxlen)，在字典单词长度远小于字符串长度时效果显著。

    public boolean wordBreak_refactor(String s, List<String> wordDict) {

        Set<String> set=new HashSet<>();
        int maxlen=0;
        for(String a:wordDict){
            set.add(a);
            maxlen=Math.max(maxlen,a.length());
        }
        int n=s.length();
        boolean[]dp=new boolean[n+1];
        dp[0]=true;
        for (int i = 1; i <= n; i++) {
            // j 是i 往左移动的边界 从i-1 一直移动到i-maxlen 边界 或者到达0
            for (int j = i - 1; j >= Math.max(0, i - maxlen); j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    //这一版最符合我最初的想法， 不过人家很聪明的是使用maxlen来计算边界， 而不是像我之前dp[j]==1 or j==0 的方式来判断， 很精妙,这样避免了很多重复不需要的判断
    // 节约时间
    // 这次思路很快就有了，而且都是对的， 不过问题是substring用的有点少没想到 substring (0,1) ='l' , char[] 中substring(0,2) ='le'，
    // 取值规则
    //左闭右开区间：
    //包含 beginIndex 位置的字符
    //不包含 endIndex 位置的字符
    //实际取值范围：[beginIndex, endIndex)
    //示例说明：
    //   String s = "leetcode";
    //   s.substring(0, 4);  // 返回 "leet" (索引0,1,2,3)
    //   s.substring(4, 8);  // 返回 "code" (索引4,5,6,7)
    //   s.substring(2, 5);  // 返回 "etc" (索引2,3,4)
    //

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set=new HashSet<>();

        int maxlen=0;
        for(String a:wordDict){
            set.add(a);
            maxlen=Math.max(maxlen,a.length());
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            // Math.max(0,i-maxlen) 使用max 的原因是i-maxlen有可能越界到-1,注意这里的边界， j 会走到0 ， 每次写循环的时候，考虑一下下面的内容是否是否会走到那个边界，边界边界，不要在那瞎写， > he >=是不一样的
            for (int j =i-1; j >= Math.max(0,i-maxlen); j--) {
                if((dp[j]==1)&& wordDict.contains(s.substring(j,i))){dp[i]=1; break;}
            }
        }

        return dp[n]==1?true:false;
    }

    public boolean wordBreak_originial(String s, List<String> wordDict) {
        // 找出worddict 中能够match s 中的字符串， 怎么校验， wordDict 当前的word 是否 contain s中的某段string
        // dp[i] 代表s [i]位置的往左看到上一个是true 的位置是否能够在worddICT中找到对应的word, 如果可以就标志=1 如果不行=0；// 如果上一个位置是true， 那么可以不用往前看， 因为前面已经形成了一个单词， 我需要继续往后看， 当前位置的为结尾的单词是否找到word,
        // 最后返回的是dp[n]位置的值，
        int n = s.length();
        int[] dp = new int[n];
        // char[] chars = s.toCharArray();
        // 初始化都是0
        if(wordDict.contains((String.valueOf(s.charAt(0))))){
            dp[0]=1;
        }
        for(int i=1;i<n;i++){ // 从第一个位置开始是为了左边界 溢出

            // 以i 位置结尾的往左看到上一个是true 的位置是否能够在worddICT中找到对应的word
            // 一开始是边界怎么找， 要么l==0 or dp[l]=1

            int l=i ;
            // 一直往左移动左边界，直到到达开头或者找到了上一个组成一个
            while(l!=0 && dp[l]!=1){
                l--;
            }
            //这里放弃使用substring 改用将string 转换成char[]的原因是
            // substring (0,1) ='l' , char[] 中substring(0,2) ='le'， 0 位置是没有东西的，这样和dp[]不是很匹配不是很好对答案
            // 不过使用char[]虽然下标很好拿，可是不好拿substring ,最后定使用substring
            String midS= s.substring(l==0?0:l+1,i+1);
            if(wordDict.contains(midS)){dp[i]=1;}
        }

        for (int i = 0; i < n; i++) {
            System.out.print(dp[i]+" ");
        }
        return dp[n-1]==1?true:false;

    }
    public static void main(String[] args) {
        /**
         * 示例 1：
         *
         * 输入: s = "leetcode", wordDict = ["leet", "code"]
         * 输出: true
         * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
         * 示例 2：
         *
         * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
         * 输出: true
         * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
         *      注意，你可以重复使用字典中的单词。
         * 示例 3：
         *
         * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
         * 输出: false
         */
        System.out.println(new wordBreakS().wordBreak("leetcode",List.of("leet","code")));
        System.out.println(new wordBreakS().wordBreak("applepenapple",List.of("apple","pen")));
        System.out.println(new wordBreakS().wordBreak("catsansdog",List.of("cats","dog","sand","and")));
      //  System.out.println(new wordBreakS().wordBreak("a",List.of("a")));
        System.out.println(new wordBreakS().wordBreak("ab",List.of("a","b")));
    }
}
