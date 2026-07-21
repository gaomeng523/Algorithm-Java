package Demo1;

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

    
}
