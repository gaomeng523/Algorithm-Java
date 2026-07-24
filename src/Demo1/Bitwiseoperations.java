package Demo1;

public class Bitwiseoperations {
    /**
     * 判断字符是否唯⼀  https://leetcode.cn/problems/is-unique-lcci/description/
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        char[] str = astr.toCharArray();
        int x = 0;
        for(char ch : str){
            int i  = ch - 'a';
            if(((x >> i) & 1 )== 1) return false;
            x = (1 << i) | x;
        }
        return true;
    }


    /**
     * 丢失的数字 https://leetcode.cn/problems/missing-number/
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int x = 0;
        int n = nums.length;
        for(int i : nums)
            x ^= i;
        for(int i =0 ; i <= n ;i++){
            x ^= i;
        }
        return x;
    }
    /**
     *  https://leetcode.cn/problems/sum-of-two-integers/
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while(b != 0){
            int x = a, y = b;
            a = x ^ y;
            b = (x & y) << 1;
        }
        return a;
    }

    /**
     * 只出现⼀次的数字 II https://leetcode.cn/problems/single-number-ii/description/
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for(int i =0 ;i < 32 ;i++){
            int sum = 0;
            for(int x : nums){
                if(((x >> i) & 1) == 1)
                    sum += 1;
            }
            sum %= 3;
            if(sum == 1){
                ret = ret | (1 << i);
            }
        }
        return ret;
    }

    /**
     * 消失的两个数字  https://leetcode.cn/problems/missing-two-lcci/
     * @param nums
     * @return
     */
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        // 第一步：全部异或，得到 a ^ b
        int xor = 0;
        for (int i = 1; i <= n; i++) xor ^= i;
        for (int x : nums) xor ^= x;
        // 找到 xor 中最低的那个 1（a 和 b 在这一位不同）
        int diff = xor & (-xor);
        // 第二步：按这一位分组异或
        int a = 0, b = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & diff) != 0) a ^= i;
            else b ^= i;
        }
        for (int x : nums) {
            if ((x & diff) != 0) a ^= x;
            else b ^= x;
        }
        return new int[]{a, b};
    }
}
