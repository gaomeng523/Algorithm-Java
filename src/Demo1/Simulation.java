package Demo1;

public class Simulation {

    /**
     * 替换所有的问号 https://leetcode.cn/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
     * @param s
     * @return
     */
    public String modifyString(String s) {
        char[] ret = s.toCharArray();
        int n = ret.length;
        for(int i = 0 ;i < n;i++){
            if(ret[i] == '?'){
                for(char ch = 'a' ;ch <= 'z' ;ch++){
                    if((i == 0 || ch != ret[i - 1]) && (i == n - 1 || ch != ret[i + 1]) ){
                        ret[i] = ch;
                        break;
                    }
                }
            }
        }
        return String.valueOf(ret);
    }

    /**
     * 提莫攻击  https://leetcode.cn/problems/teemo-attacking/description/
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        int sum = 0;
        for(int i = 0 ;i < n - 1 ;i++){
            if(timeSeries[i] + duration <= timeSeries[i + 1]) sum +=duration;
            else sum += timeSeries[i + 1] - timeSeries[i];
        }
        sum +=duration;
        return sum;
    }

    /**
     * N 字形变换  https://leetcode.cn/problems/zigzag-conversion/
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        int n = s.length();
        int d = 2 * numRows - 2;
        StringBuilder ret = new StringBuilder();
        for(int i = 0;i < n ;i += d){
            ret.append(s.charAt(i));
        }
        for(int k = 1 ;k < numRows - 1 ;k++){
            for(int i = k , j = d - k ; i < n || j < n ;i += d ,j += d){
                if(i < n) ret.append(s.charAt(i));
                if(j < n) ret.append(s.charAt(j));
            }
        }
        for(int i = numRows - 1 ; i < n ;i +=d){
            ret.append(s.charAt(i));
        }
        return ret.toString();
    }

    /**
     * 外观数列 https://leetcode.cn/problems/count-and-say/
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String ret = "1";

        while(n > 1){
            StringBuilder tmp = new StringBuilder();
            int left = 0 ,right = 0;
            int len = ret.length();
            while(right < len){
                while(right < len && ret.charAt(left) == ret.charAt(right)) right++;
                tmp.append(Integer.toString(right - left));
                tmp.append(ret.charAt(left));
                left = right;
            }
            ret = tmp.toString();
            n--;
        }
        return ret;
    }

    /**
     * 数⻘蛙  https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int ans = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            if (ch == 'c') {
                if (k > 0) k--;  // 一只叫完的青蛙重新开叫
                c++;
            } else if (ch == 'r') {
                if (c == 0) return -1;
                c--; r++;
            } else if (ch == 'o') {
                if (r == 0) return -1;
                r--; o++;
            } else if (ch == 'a') {
                if (o == 0) return -1;
                o--; a++;
            } else { // 'k'
                if (a == 0) return -1;
                a--; k++;
            }
            ans = Math.max(ans, c + r + o + a); // 同时在叫的青蛙数
        }
        // 最后所有青蛙都应该叫完了
        return (c + r + o + a == 0) ? ans : -1;
    }
}
