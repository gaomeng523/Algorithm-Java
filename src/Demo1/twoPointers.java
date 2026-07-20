package Demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class twoPointers {
/*
        常⻅的双指针有两种形式，⼀种是对撞指针，⼀种是左右指针。
        对撞指针：⼀般⽤于顺序结构中，也称左右指针。
            • 对撞指针从两端向中间移动。⼀个指针从最左端开始，另⼀个从最右端开始，然后逐渐往中间逼
            近。
            • 对撞指针的终⽌条件⼀般是两个指针相遇或者错开（也可能在循环内部找到结果直接跳出循环），
            也就是：
        ◦ left == right  （两个指针指向同⼀个位置）
        ◦ left > right  （两个指针错开）
        快慢指针：⼜称为⻳兔赛跑算法，其基本思想就是使⽤两个移动速度不同的指针在数组或链表等序列结构上移动。
        这种⽅法对于处理环形链表或数组⾮常有⽤。
        其实不单单是环形链表或者是数组，如果我们要研究的问题出现循环往复的情况时，均可考虑使⽤快慢指针的思想。
        快慢指针的实现⽅式有很多种，最常⽤的⼀种就是：
            • 在⼀次循环中，每次让慢的指针向后移动⼀位，⽽快的指针往后移动两位，实现⼀快⼀慢。
*
* */

    /**
     * 移动零  https://leetcode.cn/problems/move-zeroes/
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int left = 0 ,right = 0; //定义两个指针
        int n = nums.length;
        int tmp;
        while(right < n){
            if(nums[right] != 0){
                tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                right++;
                left++;
            }else{
                right++;
            }
        }
    }

    /**
     * 复写零  https://leetcode.cn/problems/duplicate-zeros/description/
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int cur = 0, dest = -1;
        int n = arr.length;

        // 第一遍：找到 cur 的最终位置
        while (cur < n) {
            if (arr[cur] == 0) dest += 2;
            else dest++;
            if (dest >= n - 1) break;  // 关键：在 cur++ 之前 break
            cur++;
        }

        // 边界处理：dest 刚好等于 n，说明最后一个 0 只能写一个
        if (dest == n) {
            arr[n - 1] = 0;
            cur--;
            dest -= 2;
        }

        // 第二遍：从后往前复写
        while (cur >= 0) {
            if (arr[cur] != 0) {
                arr[dest--] = arr[cur--];
            } else {
                arr[dest--] = 0;
                arr[dest--] = 0;
                cur--;
            }
        }
    }

    /**
     *  快乐数  https://leetcode.cn/problems/happy-number/description/
     */
    class Solution {
        public boolean isHappy(int n) {
            int fast = sq(sq(n));
            int slow = sq(n);
            while(fast != slow){
                fast = sq(fast);
                fast = sq(fast);
                slow = sq(slow);
            }
            if(fast == 1) {return true;}
            else return false;
        }

        public int sq(int num){
            int ret = 0;
            int x = 0;
            while(num > 0){
                x = (num) % 10;
                ret += x * x;
                num = num / 10;
            }
            return ret;
        }
    }

    /**
     * https://leetcode.cn/problems/container-with-most-water/description/
     * @param h
     * @return
     */
        public int maxArea(int[] h) {
            int n = h.length;
            int ret = 0;
            int left = 0 , right = n - 1;
            while(left != right){
                ret = Math.max(ret , Math.min(h[left] , h[right])*(right - left));
                if(h[left] < h[right]) left++;
                else right--;
            }
            return ret;
        }


    /**
     * 有效三角形的个数   https://leetcode.cn/problems/valid-triangle-number/description/
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        if(n == 1 || n == 2) return 0;
        Arrays.sort(nums);
        int ret = 0;
        for(int i = n - 1 ; i >= 2 ;i--){
            int left = 0 , right = i - 1;
            while(left != right){
                if(nums[left] + nums[right] > nums[i]){
                    ret += (right - left);
                    right--;
                }else{
                    left++;
                }
            }
        }
        return ret;
    }

    /**
     * https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/description/
     * @param price
     * @param target
     * @return
     */
    public int[] twoSum(int[] price, int target) {
        int n = price.length;
        int left = 0 ,right = n - 1;

        int[] ret;
        while(left != right){
            int sum = price[left] + price[right];
            if(sum > target){
                right--;
            }else if(sum < target){
                left++;
            }else{
                return new int[]{price[left] , price[right]};
            }
        }
        return new int[0];
    }

    /**
     * https://leetcode.cn/problems/3sum/
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = n - 1; i >= 2;) {
            int left = 0, right = i - 1;
            int target = -nums[i];
            if (target > 0) break;  // ← 见下面说明

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum > target) right--;
                else if (sum < target) left++;
                else {
                    ret.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    left++; right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
            i--;
            while (i >= 2 && nums[i] == nums[i + 1]) i--;
        }
        return ret;
    }

    /**
     * 四数之和 https://leetcode.cn/problems/4sum/description/
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);  // ← 补上排序
        int n = nums.length;

        for (int i = n - 1; i >= 3;) {
            for (int j = i - 1; j >= 2;) {
                long aim = (long) target - nums[i] - nums[j];  // ← 用 long
                int left = 0, right = j - 1;

                while (left < right) {
                    long sum = (long) nums[left] + nums[right];  // ← 用 long
                    if (sum > aim) right--;
                    else if (sum < aim) left++;
                    else {
                        ret.add(Arrays.asList(nums[left], nums[right], nums[j], nums[i]));
                        left++; right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    }
                }
                j--;
                while (j >= 2 && nums[j] == nums[j + 1]) j--;
            }
            i--;
            while (i >= 3 && nums[i] == nums[i + 1]) i--;
        }
        return ret;
    }
}
