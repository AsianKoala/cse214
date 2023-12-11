import java.util.Stack;

public class BalancedParen {
    public static void main(String[] args) {
        String s = "({A+B}-[C/D])";
        Stack<Character> stack = new Stack<Character>();
        boolean bad = true;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if(c == ')' || c == '}' || c == ']') {
                if(stack.isEmpty()) {
                    bad = false;
                    break;
                } else if(stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else if(stack.peek() == '{' && c == '}') {
                    stack.pop();
                } else if(stack.peek() == '[' && c == ']') {
                    stack.pop();
                } else {
                    bad = false;
                    break;
                }
            }
        }
        if(!stack.isEmpty()) {
            bad = false;
        }
        System.out.println(bad);
    }
}
