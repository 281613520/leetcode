package stack;

import java.util.Stack;

/**
 * leetcode 20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class IsValid {
    public boolean isValid(String s) {
        if (s.equals(""))
            return true;
        while (true) {
            int length = s.length();
            s = s.replaceAll("\\(\\)", "");
            s = s.replaceAll("\\[\\]", "");
            s = s.replaceAll("\\{\\}", "");
            if (length == s.length())
                break;
        }
        return s.length() == 0;
    }
}
