package Demo1;

public class string {

    /**
     * 最⻓公共前缀  https://leetcode.cn/problems/longest-common-prefix/
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        for(int i = 0 ;i < strs[0].length() ;i++){
            char ch = strs[0].charAt(i);

            for(int j = 1 ; j < strs.length ;j++){
                // char tmp = strs[j].charAt(i);
                if(i == strs[j].length() || strs[j].charAt(i) != ch){
                    return strs[0].substring(0 , i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 最⻓回⽂⼦串 https://leetcode.cn/problems/longest-palindromic-substring/
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] ch = s.toCharArray();
        int ret = 0;
        int begin = 0;
        for(int i = 0 ; i < ch.length ;i++){
            int right = i , left = i - 1;
            while(left >= 0 && right < ch.length && ch[left] == ch[right]){
                left--; right++;
            }
            if(ret < right - left - 1){
                ret  = right - left - 1;
                begin = left + 1;
            }
            right = i ; left = i;
            while(left >= 0 && right < ch.length && ch[left] == ch[right]){
                left--; right++;
            }
            if(ret < right - left - 1){
                ret  = right - left - 1;
                begin = left + 1;
            }
        }
        return s.substring(begin , begin + ret);
    }

    /**
     * ⼆进制求和 https://leetcode.cn/problems/add-binary/
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuffer ret = new StringBuffer();

        int m = a.length() - 1 , n = b.length() - 1, t = 0;
        while(m >= 0 || n >= 0 || t != 0){
            if(m >= 0) t += a.charAt(m--) - '0';
            if(n >= 0) t += b.charAt(n--) - '0';
            ret.append((char)('0' + (char)(t % 2)));
            t /= 2;
        }
        return ret.reverse().toString();
    }

    /**
     * 字符串相乘  https://leetcode.cn/problems/multiply-strings/
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        int m = num1.length();
        int n = num2.length();
        String s1 = new StringBuilder(num1).reverse().toString();
        String s2 = new StringBuilder(num2).reverse().toString();

        // 二维数组：m行，每行错开i位
        int[][] table = new int[m][m + n];
        for(int i = 0; i < m; i++){
            int a = s1.charAt(i)-'0';
            for(int j = 0; j < n; j++){
                int b = s2.charAt(j)-'0';
                table[i][i+j] = a*b; // 每行起始偏移i
            }
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        // 逐列求和
        for(int col = 0; col < m+n; col++){
            int sum = carry;
            for(int row = 0; row < m; row++){
                sum += table[row][col];
            }
            sb.append(sum%10);
            carry = sum/10;
        }
        // 去除末尾0
        while(sb.length()>0 && sb.charAt(sb.length()-1)=='0'){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.reverse().toString();
    }


}
