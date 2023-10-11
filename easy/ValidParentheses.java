package easy;

import java.util.Stack;

// 20
public class ValidParentheses {
    public static void main(String[] args) {
        String s = "()";
        String s1 = "[)";


        System.out.println(isValid(s1));
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char character : s.toCharArray()) {
            switch(character) {
                case '(':
                case '{':
                case '[':
                    stack.push(character);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
