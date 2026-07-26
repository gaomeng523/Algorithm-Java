package Demo1;

import java.util.*;

public class Hashtable {

    /**
     * 两数之和  https://leetcode.cn/problems/two-sum/description/
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer , Integer> hash = new HashMap<>();
        int[] ret = new int[2];
        for(int i = 0 ;i < nums.length;i++){
            if(hash.containsKey(target - nums[i])){
                ret[0] = hash.get(target - nums[i]);
                ret[1] = i;
                break;
            }
            hash.put(nums[i], i);
        }
        return ret;
    }

    /**
     * 判断是否互为字符重排 https://leetcode.cn/problems/check-permutation-lcci/
     * @param ss1
     * @param ss2
     * @return
     */
    public boolean CheckPermutation(String ss1, String ss2) {
        int m = ss1.length() , n = ss2.length();
        if(m != n) return false;
        int[] hash = new int[26];
        for(int i = 0;i < m ;i++)
            hash[ss1.charAt(i) - 'a']++;
        for(int i = 0 ;i < n ;i++){
            hash[ss2.charAt(i) - 'a']--;
            if(hash[ss2.charAt(i) - 'a'] < 0)
                return false;
        }
        return true;
    }

    /**
     * 存在重复元素 I  https://leetcode.cn/problems/contains-duplicate/description/
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for(int x : nums){
            if(hash.contains(x))
                return true;
            hash.add(x);
        }
        return false;
    }

    /**
     * 存在重复元素 II  https://leetcode.cn/problems/contains-duplicate-ii/
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer , Integer> hash = new HashMap<>();
        for(int i = 0 ;i < nums.length ;i++){
            if(hash.containsKey(nums[i]) && i - hash.get(nums[i]) <= k)
                return true;
            hash.put(nums[i] , i);
        }
        return false;
    }

    /**
     * 字⺟异位词分组  https://leetcode.cn/problems/group-anagrams/
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String , List<String>> hash = new HashMap<>();
        for(String str : strs){
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String tmp = new String(ch);

            if(!hash.containsKey(tmp)){
                hash.put(tmp , new ArrayList<>());
            }
            hash.get(tmp).add(str);
        }
        return new ArrayList<>(hash.values());
    }
}
