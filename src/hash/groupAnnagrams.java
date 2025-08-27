package hash;

import java.util.*;

public class groupAnnagrams {
    public static List<List<String>> groupAnagramsF(String[] strs) {
        Map<String, List<String>> ans= new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for(String str: strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars); // chars.toString vs new String(chars)
            System.out.println("chars to string"+ chars.toString());
            System.out.println("new string"+ key);
            /**
             * chars to string [C@6aaa5eb0
             * new string abt
             *
             * 1. **`chars.toString()`**:
             *     - This method is inherited from the `Object` class.
             *     - It returns a string representation of the `char[]` object, which is typically not what you want for a `char[]` array.
             *     - The result is something like `[C@<hashcode>`, where `<hashcode>` is the hash code of the `char[]` object. This is not a meaningful string for your use case.
             *
             * 2. **`new String(chars)`**:
             *     - This constructor creates a new object from the `char[]` array. `String`
             *     - It converts the `char[]` array into a readable string, which is what you need in this context.
             *     - For example, if `chars` is `['a', 'e', 't']`, `new String(chars)` will produce the string `"aet"`.
             *
             * Given your use case, you should use `new String(chars)` to convert the sorted `char[]` array into a string that can be used as a key in the `ans` map.
             */
            //eat=> aet if aet exist then add "eat" to value of key "aet"
            List<String> temp = ans.getOrDefault(key,new ArrayList<>()); // get key of map for "aet
            temp.add(str);
            ans.putIfAbsent(key,temp);
        }

        // retrieve value of ans hasmap and merge them to res List
       ans.values().forEach(res::add);
        return res;



    }
    public static void main(String[] args) {
        /**
         * input strs = ["eat","tea","tan","ate","nat","bat"]
         * output ans = [["bat"],["nan","tan"],[""ate","eat","tea"]]
         */

        String[] strs = {"eat","tea","tan","nat","bat"};
        System.out.println(groupAnagramsF(strs));



    }
}
