import java.util.Stack;

public class validbracket {
    public boolean isValid(char[] brackets) {
        Stack<Character> stack = new Stack<>();

        for (char c : brackets) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        validbracket vp = new validbracket();
        System.out.println(vp.isValid(
                new char[] {'(', ')'})
        );
        System.out.println(vp.isValid(
                new char[] {'(', '[', ')', ']'})
        );

    }
}
