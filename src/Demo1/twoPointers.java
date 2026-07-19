package Demo1;

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


}
