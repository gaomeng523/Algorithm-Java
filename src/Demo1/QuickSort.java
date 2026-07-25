package Demo1;

import java.util.ArrayList;
import java.util.List;
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


    int[] tmp;

    /**
     * 归并排序  https://leetcode.cn/problems/sort-an-array/
     * @param nums
     * @return
     */
    public int[] sortArray1(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums , 0 , nums.length - 1);
        return nums;
    }
    public void mergeSort(int[] nums , int left ,int right){
        if(left >= right) return;
        int mid = left + (right - left)/2;
        mergeSort(nums , left , mid);
        mergeSort(nums , mid + 1 , right);
        int cur1 = left , cur2 = mid + 1 , i =0;
        while(cur1 <= mid && cur2 <= right){
            tmp[i++] = nums[cur1] <= nums[cur2] ? nums[cur1++] : nums[cur2++];
        }
        while(cur1 <= mid) tmp[i++] = nums[cur1++];
        while(cur2 <= right) tmp[i++] = nums[cur2++];
        for(int j = left; j <= right; j++)
            nums[j] = tmp[j - left];
    }

    int ret;
    /**
     * 数组中的逆序对  https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
     * @param record
     * @return
     */
    public int reversePairs(int[] record) {
        tmp = new int[record.length];
        ret = 0;
        mergeSort2(record , 0 , record.length - 1);
        return ret;
    }

    public void mergeSort2(int[] nums , int left ,int right){
        if(left >= right) return ;
        int mid = left + (right - left)/2;

        mergeSort(nums , left , mid);
        mergeSort(nums , mid + 1 , right);

        int cur1 = left , cur2 = mid + 1 , i = 0;
        while(cur1 <= mid && cur2 <= right){
            if(nums[cur1] <= nums[cur2]) tmp[i++] = nums[cur1++];
            else{
                tmp[i++] = nums[cur2++];
                ret += mid - cur1 + 1;
            }
        }
        while(cur1 <= mid) tmp[i++] = nums[cur1++];
        while(cur2 <= right) tmp[i++] = nums[cur2++];

        for (int j = left; j <= right; j++)
            nums[j] = tmp[j - left];

    }
    int[] rett;
    int[] index;
    int[] indextmp;

    /**
     * 计算右侧⼩于当前元素的个数
     * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        rett = new int[n];
        index = new int[n];
        tmp = new int[n];
        indextmp = new int[n];
        for(int i = 0 ;i < n ;i++)
            index[i] = i;
        mergeSort3(nums ,0 ,n -1);
        List<Integer> l = new ArrayList<>();
        for(int x : rett)
            l.add(x);
        return l;
    }

    public void mergeSort3(int[] nums , int left , int right){
        if(left >= right) return ;
        int mid = left + (right - left)/2;
        mergeSort(nums , left , mid);
        mergeSort(nums , mid + 1 , right);
        int cur1 = left , cur2 = mid + 1 ,i = 0;
        while(cur1 <= mid && cur2 <= right){
            if(nums[cur1] <= nums[cur2]){
                tmp[i] = nums[cur2];
                indextmp[i++] = index[cur2++];
            }else{
                tmp[i] = nums[cur1];
                rett[index[cur1]] += right - cur2 + 1;
                indextmp[i++] = index[cur1++];
            }
        }
        while(cur1 <= mid){
            tmp[i] = nums[cur1];
            indextmp[i++] = index[cur1++];
        }
        while(cur2 <= right){
            tmp[i] = nums[cur2];
            indextmp[i++] = index[cur2++];
        }
        for(int j = left; j <= right; j++){
            nums[j] = tmp[j - left];
            index[j] = indextmp[j - left];
        }
    }

    int count;

    /**
     * 翻转对  https://leetcode.cn/problems/reverse-pairs/description/
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        int n = nums.length;
        tmp = new int[n];
        count = 0;
        mergeSort4(nums, 0, n - 1);
        return count;
    }

    public void mergeSort4(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        // 第一步：单独计数（双指针，利用两半各自有序）
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) nums[i] > 2L * nums[j]) j++;
            count += j - (mid + 1);  // j 之前的右边元素都满足 2倍关系
        }

        // 第二步：正常按值合并（和排序一样）
        int cur1 = left, cur2 = mid + 1, i = 0;
        while (cur1 <= mid && cur2 <= right) {
            if (nums[cur1] <= nums[cur2]) tmp[i++] = nums[cur1++];
            else tmp[i++] = nums[cur2++];
        }
        while (cur1 <= mid) tmp[i++] = nums[cur1++];
        while (cur2 <= right) tmp[i++] = nums[cur2++];

        for (int k = left; k <= right; k++)
            nums[k] = tmp[k - left];
    }

}
