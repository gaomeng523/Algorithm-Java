package Demo1;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack {

    /**
     * 删除字符中的所有相邻重复项
     * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        StringBuffer ret = new StringBuffer();
        char[] ss = s.toCharArray();

        for(char ch : ss){
            if(!ret.isEmpty() && ch == ret.charAt(ret.length() - 1)){
                ret.deleteCharAt(ret.length() - 1);
            } else {
                ret.append(ch);
            }
        }
        return ret.toString();
    }


    /**
     * ⽐较含退格的字符串 https://leetcode.cn/problems/backspace-string-compare/description/
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        StringBuffer str1 = new StringBuffer();
        StringBuffer str2 = new StringBuffer();
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        for(char ch : s1){
            if(ch == '#'){
                if(!str1.isEmpty())
                    str1.deleteCharAt(str1.length() - 1);
            }else{
                str1.append(ch);
            }
        }
        for(char ch : t1){
            if(ch == '#'){
                if(!str2.isEmpty())
                    str2.deleteCharAt(str2.length() - 1);
            }else{
                str2.append(ch);
            }
        }
        return str1.toString().equals(str2.toString());
    }

    /**
     * 基本计算器 II  https://leetcode.cn/problems/basic-calculator-ii/
     * @param ss
     * @return
     */
    public int calculate(String ss) {
        char[] s =ss.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();

        int n = s.length;
        char op = '+';
        int i = 0;
        while(i < n){
            if(s[i] == ' ') i++;
            else if(s[i] >= '0' && s[i] <= '9'){
                int tmp = 0;
                while(i < n && s[i] >='0' && s[i] <='9'){
                    tmp = tmp * 10 + s[i] - '0';
                    i++;
                }
                if(op == '+') stack.push(tmp);
                else if(op == '-') stack.push(-tmp);
                else if(op == '*') stack.push(stack.poll() * tmp);
                else if(op == '/') stack.push(stack.poll() / tmp);
            }else{
                op = s[i++];
            }
        }
        int ret = 0;
        while(!stack.isEmpty()){
            ret += stack.poll();
        }
        return ret;
    }

    /**
     * 字符串解码  https://leetcode.cn/problems/decode-string/
     * @param ss
     * @return
     */
    public String decodeString(String ss) {
        Deque<Integer> st1 = new ArrayDeque<>();
        Deque<StringBuffer> st2 = new ArrayDeque<>();
        st2.push(new StringBuffer());
        char[] s = ss.toCharArray();
        int n = ss.length() , i =0;

        while(i < n){
            if(s[i] <= '9' && s[i] >= '0'){
                int tmp = 0;
                while(i < n && s[i] <= '9' && s[i] >= '0'){
                    tmp = tmp*10 + s[i] - '0';
                    i++;
                }
                st1.push(tmp);
            }else if(s[i] == '['){
                i++;
                StringBuffer str = new StringBuffer();
                while(i < n && s[i] >= 'a' && s[i] <= 'z'){
                    str.append(s[i]);
                    i++;
                }
                st2.push(str);
            }else if(s[i] == ']'){
                int k = st1.pop();
                StringBuffer tmp = st2.pop();
                while(k-- != 0){
                    st2.peek().append(tmp);
                }
                i++;
            }else{
                StringBuffer tmp = new StringBuffer();
                while(i < n && s[i] <= 'z' && s[i] >= 'a'){
                    tmp.append(s[i]);
                    i++;
                }
                st2.peek().append(tmp);
            }
        }
        return st2.peek().toString();
    }

    /**
     * 验证栈序列  https://leetcode.cn/problems/validate-stack-sequences/
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> st = new ArrayDeque<>();
        int j = 0 ,n = pushed.length;
        for(int x : pushed){
            st.push(x);
            while(!st.isEmpty() && st.peek() == popped[j]){
                st.pop();
                j++;
            }
        }
        return j == n;
    }
}
