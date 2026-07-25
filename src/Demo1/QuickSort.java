package Demo1;

import java.util.Random;

public class QuickSort {
    /**
     * 颜⾊分类 https://leetcode.cn/problems/sort-colors/
     * @param nums
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left = -1 , right = n ;
        for(int i = 0 ;i < right ;){
            if(nums[i] == 0) swap(nums , ++left , i++);
            else if(nums[i] == 1) i++;
            else swap(nums , --right , i);
        }
    }
    public void swap(int[] nums , int a , int b){
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    /**
     * 快速排序  https://leetcode.cn/problems/sort-an-array/
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        qsort(nums , 0 , n - 1);
        return nums;
    }
    public void qsort(int[] nums , int l , int r){
        if(l >= r ) return;
        int left = l - 1 , right = r + 1;
        int i = l;
        int key = nums[new Random().nextInt(r - l + 1) + l];
        while(i < right){
            if(nums[i] < key) swap(nums , ++left , i++);
            else if(nums[i] == key) i++;
            else swap(nums , --right , i);
        }
        qsort(nums , l , left);
        qsort(nums , right ,r);
    }

    /**
     * 快速选择算法 https://leetcode.cn/problems/kth-largest-element-in-an-array/
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return qsort(nums , 0 , nums.length - 1 ,k);
    }
    public int qsort(int[] nums , int l , int r ,int k){
        if(l == r) return nums[l];
        int left = l - 1 , right = r + 1 , i = l;
        int key = nums[new Random().nextInt(r - l + 1) + l];
        while(i < right){
            if(nums[i] < key) swap(nums , ++left ,i++);
            else if(nums[i] == key) i++;
            else swap(nums , --right , i);
        }
        int b = right - left - 1; int c = r - right + 1;
        if(r - right + 1 >= k) return qsort(nums , right , r ,k);
        else if(r - left >= k) return key;
        else return qsort(nums , l , left , k-b-c);
    }


    /**
     * 最⼩的 k 个数  https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
     * @param stock
     * @param cnt
     * @return
     */
    public int[] inventoryManagement(int[] stock, int cnt) {
        qsort1(stock , 0 , stock.length - 1 , cnt);
        int[] ret = new int[cnt];
        for(int i = 0 ;i < cnt ;i++)
            ret[i] = stock[i];
        return ret;
    }
    public void qsort1(int[] nums , int l , int r , int cnt){
        if(l >= r) return ;

        int left = l - 1 , right = r + 1 , i = l;
        int key = nums[new Random().nextInt(r - l + 1) + l];
        while(i < right){
            if(nums[i] < key) swap(nums , ++left , i++);
            else if(nums[i] == key) i++;
            else swap(nums, --right ,i);
        }
        int a = left - l + 1; int b = right - left - 1;
        if(a > cnt) qsort(nums , l , left , cnt);
        else if(a + b >= cnt) return;
        else qsort(nums , right , r , cnt - a - b);
    }


}
