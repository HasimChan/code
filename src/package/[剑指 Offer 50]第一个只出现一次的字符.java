//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。 
//
// 示例: 
//
// s = "abaccdeff"
//返回 "b"
//
//s = "" 
//返回 " "
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 哈希表 
// 👍 106 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public char firstUniqChar(String s) {
//        LinkedHashMap<Character, Boolean> map = new LinkedHashMap<Character, Boolean>();
//
//        for (char ch : s.toCharArray()) {
//            if (map.get(ch) == null) {
//                map.put(ch, false);
//            } else {
//                map.put(ch, true);
//            }
//        }
//
//        Iterator<Map.Entry<Character, Boolean>> iterator = map.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<Character, Boolean> next = iterator.next();
//            if (next.getValue() == false) {
//                return next.getKey();
//            }
//        }
//
//        return ' ';
        ArrayList<Character> list = new ArrayList<Character>();
        boolean[] vals = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = list.indexOf(ch);
            if (index == -1) {
                list.add(ch);
                vals[i] = false;
            } else {
                vals[index] = true;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (vals[i] == false) {
                return list.get(i);
            }
        }

        return ' ';
    }
}
//leetcode submit region end(Prohibit modification and deletion)
