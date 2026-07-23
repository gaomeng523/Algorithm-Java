package Demo1;

public class BinarySearch {

    /**
     * 二分查找  https://leetcode.cn/problems/binary-search/
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0 , right = n-1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 在排序数组中查找元素的第⼀个和最后⼀个位置
     * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[2];
        ret[0] = ret[1] = -1;
        int n = nums.length;
        if(n == 0) return ret;
        int left = 0 , right = n - 1;
        // 得到左端点
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if(nums[left] != target) return ret;
        ret[0] = left;
        right = n - 1;
        //得到右端点
        while(left < right){
            int mid = left + (right - left + 1)/2;
            if(nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        if(nums[right] == target) ret[1] = right;
        return ret;
    }

    /**
     * x的平方根 https://leetcode.cn/problems/sqrtx/
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        long left = 1 , right = x;
        if(x < 1) return 0;
        while(left < right){
            long mid = left + (right - left + 1)/2;
            if(mid * mid <= x) left = mid;
            else right = mid - 1;
        }
        return (int)left;
    }

    /**
     * 搜索插入位置 https://leetcode.cn/problems/search-insert-position/
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0 , right = n - 1;
        int ret = 0;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return nums[right] >= target ? right : right + 1;
    }

    /**
     * ⼭峰数组的峰顶 https://leetcode.cn/problems/peak-index-in-a-mountain-array/
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 0 , right = n - 1;
        while(left < right){
            int mid = left + (right - left) /2;
            if(arr[mid] < arr[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    /**
     * 寻找峰值 https://leetcode.cn/problems/find-peak-element/
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0 , right = n - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    /**
     * 搜索旋转排序数组中的最⼩值
     * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0 ,right = n - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[n - 1]) left = mid + 1;
            else right = mid;
        }
        return nums[right];
    }

    /**
     * 0〜n-1 中缺失的数字  https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
     * @param records
     * @return
     */
    public int takeAttendance(int[] records) {
        int n = records.length;
        int left = 0 , right = n - 1;
        while(left < right){
            int mid = left + (right - left) /2;
            if(mid == records[mid]) left = mid + 1;
            else right = mid;
        }
        return left == records[left] ? left + 1 : left;
    }
}
