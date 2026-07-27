package Demo1;

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


}
