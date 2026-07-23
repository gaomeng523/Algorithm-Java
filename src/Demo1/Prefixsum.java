package Demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prefixsum {
    /**
     * 前缀和（模板）
     * @param args
     */
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt() , m = in.nextInt();

        int[] a = new int[n + 1];
        for(int i = 1 ; i <= n ;i++) a[i] = in.nextInt();

        long[] dp = new long[n + 1];
        for(int i = 1 ;i <= n ;i++){
            dp[i] = dp[i - 1] + a[i];
        }
        while(m > 0){
            int l = in.nextInt();
            int r = in.nextInt();
            System.out.println(dp[r] - dp[l - 1]);
            m--;
        }
    }

    /**
     * 二维前缀和
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt() , m = in.nextInt() , q = in.nextInt();
        int[][] arr = new int[n + 1][m + 1];
        long[][] dp = new long[n + 1][m + 1];
        for(int i = 1 ;i <= n ;i++){
            for(int j = 1 ; j <=m ;j++){
                arr[i][j] = in.nextInt();
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + arr[i][j] - dp[i-1][j-1];
            }
        }

        while(q > 0){
            int x1 = in.nextInt(),y1 = in.nextInt() , x2 = in.nextInt() , y2 = in.nextInt();
            long ret = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
            System.out.println(ret);
            q--;
        }
    }

    /**
     * 寻找数组的中⼼下标 https://leetcode.cn/problems/find-pivot-index/description/
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if(n == 1) return 0;
        dp[0] = nums[0];
        for(int i = 1 ;i < n ;i++)
            dp[i] = dp[i - 1] + nums[i];
        for(int i = 0;i < n ;i++){
            if(dp[i] - nums[i] == dp[n - 1] - dp[i])
                return i;
        }
        return -1;
    }

    /**
     * 除⾃⾝以外数组的乘积 https://leetcode.cn/problems/product-of-array-except-self/
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // 可以分别定义两个数组 ，一个记录前缀积 ，一个记录后缀积
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = 1; g[n - 1] = 1;
        for(int i = 1 ;i < n;i++){
            f[i] = f[i - 1] * nums[i - 1];
        }
        for(int i = n - 2 ; i >= 0 ;i--){
            g[i] = g[i + 1] * nums[i + 1];
        }
        int[] ret = new int[n];
        for(int i = 0 ;i < n ;i++)
            ret[i] = f[i] * g[i];

        return ret;
    }


    /**
     * 和为 k 的⼦数组 https://leetcode.cn/problems/subarray-sum-equals-k/
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer , Integer> hash = new HashMap<>();
        hash.put(0 , 1);
        int count = 0;
        for(int x : nums){
            sum += x;
            count += hash.getOrDefault(sum - k , 0);
            hash.put(sum , hash.getOrDefault(sum , 0) + 1);
        }
        return count;
    }

    /**
     * 和可被 K 整除的⼦数组 https://leetcode.cn/problems/subarray-sums-divisible-by-k/description/
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> hash = new HashMap<>();
        int sum = 0 , count = 0;
        hash.put(0 % k , 1);
        for(int x : nums){
            sum += x;
            int r = (sum % k  + k) % k;
            count += hash.getOrDefault(r , 0);
            hash.put(r , hash.getOrDefault(r, 0) + 1);
        }
        return count;
    }

    /**
     * 连续数组 https://leetcode.cn/problems/contiguous-array/
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        Map<Integer , Integer> hash = new HashMap<>();
        int n = nums.length;
        int ret = 0;
        hash.put(0 , -1);
        int sum = 0;
        for(int i = 0 ; i < n;i++){
            sum += (nums[i] == 0 ? -1 : 1);
            if(hash.containsKey(sum)) ret = Math.max(ret , i - hash.get(sum));
            else hash.put(sum , i);
        }
        return ret;
    }

    /**
     * 矩阵区域和 https://leetcode.cn/problems/matrix-block-sum/description/
     * @param mat
     * @param k
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        // 前缀和dp，1起始
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 映射到dp的1起始坐标
                int x1 = Math.max(0, i - k) + 1;
                int y1 = Math.max(0, j - k) + 1;
                int x2 = Math.min(m - 1, i + k) + 1;
                int y2 = Math.min(n - 1, j + k) + 1;
                ret[i][j] = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
            }
        }
        return ret;
    }
}
