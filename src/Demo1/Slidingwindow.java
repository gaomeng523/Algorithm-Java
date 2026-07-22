package Demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slidingwindow {

    /**
     * ⻓度最⼩的⼦数组  https://leetcode.cn/problems/minimum-size-subarray-sum/description/
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0 , right = 0;
        int ret = Integer.MAX_VALUE;
        int sum = 0;
        for(; right < n ; right++){
            //进窗口
            sum += nums[right];
            while(sum >= target){
                ret = Math.min(ret , right-left + 1);
                sum -= nums[left];
                left++;

            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }

    /**
     * ⽆重复字符的最⻓⼦串   https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length;
        int left = 0 , right = 0;
        int ret = 0;
        int[] hash = new int[128];
        while(right < n){
            // 进窗口
            hash[ch[right]]++;
            //判断
            while(hash[ch[right]] > 1){
                //出窗口
                hash[ch[left++]]--;
            }
            ret = Math.max(ret , right - left + 1);
            right++;
        }
        return ret;
    }

    /**
     * 最⼤连续 1 的个数 III   https://leetcode.cn/problems/max-consecutive-ones-iii/
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int zero = 0;
        int n = nums.length;
        int left = 0 ,right = 0;
        int ret = 0;
        while(right < n){
            if(nums[right] == 0) zero++;
            while(zero > k){
                if(nums[left++] == 0) zero--;
            }
            ret = Math.max(ret , right - left + 1);
            right++;
        }
        return ret;
    }

    /**
     * 将 x 减到 0 的最⼩操作数 https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int sum = 0 , n = nums.length;
        for(int i = 0 ;i < n ;i++){
            sum += nums[i];
        }
        int target = sum - x;
        if(target < 0) return -1;
        if(target == 0) return n;

        int right = 0, left = 0;
        int ssum = 0;
        int ret = -1;
        while(right < n){
            ssum += nums[right];
            while(ssum > target){
                ssum -= nums[left++];
            }
            if(ssum == target)
                ret = Math.max(ret , right - left + 1);
            right++;
        }

        return ret == -1 ? -1 : n - ret;
    }

    /**
     * 水果成篮 https://leetcode.cn/problems/fruit-into-baskets/description/
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int[] hash = new int[100001];
        int n = fruits.length;
        int left = 0 ,right = 0;
        int kinds = 0;
        int ret = 0;
        while(right < n){
            if(hash[fruits[right]] == 0) kinds++;
            hash[fruits[right]]++;
            while(kinds > 2){
                hash[fruits[left]]--;
                if(hash[fruits[left]] == 0)
                    kinds--;
                left++;
            }
            ret = Math.max(ret , right - left + 1);
            right++;
        }
        return ret;
    }

    /**
     * https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/
     * @param ss
     * @param pp
     * @return
     */
    public List<Integer> findAnagrams(String ss, String pp) {
        List<Integer> ret = new ArrayList<>();
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        int[] hash1 = new int[26];
        for(char ch : p) hash1[ch - 'a']++;
        int[] hash2 = new int[26];

        int left = 0 ,right = 0 , m = p.length;
        int n = s.length , count =0;
        while(right < n){
            char in = s[right];
            if(++hash2[in - 'a'] <= hash1[in - 'a']) count++;
            if(right - left + 1 > m){
                char out = s[left++];
                if(hash2[out - 'a']-- <= hash1[out - 'a']) count--;
            }
            if(count == m){
                ret.add(left);
            }
            right++;
        }
        return ret;
    }

    /**
     * 串联所有单词的子串   https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        Map<String,Integer> hash1 = new HashMap<>();
        for(String str : words) hash1.put(str , hash1.getOrDefault(str , 0) + 1);
        int n = s.length();
        int m = words.length;
        String str = words[0];
        int len = str.length();
        //外层循环
        for(int i = 0 ; i < len ;i++){
            int right = i , left = i; int count = 0;
            Map<String,Integer> hash2 = new HashMap<>();
            while(right <= n - len){
                String in = s.substring(right , right + len);
                hash2.put(in , hash2.getOrDefault(in , 0) + 1);
                if(hash2.get(in) <= hash1.getOrDefault(in, 0)) count++;
                if(right - left + 1 > m * len){
                    String out = s.substring(left , left + len);
                    if(hash2.get(out) <= hash1.getOrDefault(out , 0)) count--;
                    hash2.put(out , hash2.get(out) - 1);
                    left += len;
                }
                if(count == m) ret.add(left);
                right += len;
            }
        }
        return ret;
    }

    /**
     * 最⼩覆盖⼦串 https://leetcode.cn/problems/minimum-window-substring/
     * @param ss
     * @param tt
     * @return
     */
    public String minWindow(String ss, String tt) {
        char[] s = ss.toCharArray();
        char[] t = tt.toCharArray();
        int[] hash1 = new int[128];
        int kinds = 0;
        for(char ch : t)
            if(hash1[ch]++ == 0) kinds++;
        int[] hash2 = new int[128];
        int n = s.length;
        int left = 0 , right = 0 , count = 0;
        int min = Integer.MAX_VALUE , minbegin = -1;
        while(right < n){
            //进窗口
            char in = s[right];
            if(++hash2[in] == hash1[in]) count++; //进窗口 + 维护count
            //判断
            while(count == kinds){
                //更新结果
                if(right - left + 1 < min){
                    minbegin = left;
                    min = right - left + 1;
                }
                char out = s[left++];
                if(hash2[out]-- == hash1[out]) count--; //出窗口 + 维护count
            }
            right++;
        }
        return  minbegin == -1 ? new String():ss.substring(minbegin , minbegin + min);
    }
}
