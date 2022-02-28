import java.util.Stack;
public class s1mpleFract {
    private String ExpressionToRPN(String Expr){
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i = 0; i < Expr.length(); i++){
            priority = getP(Expr.charAt(i));
            if (priority == 0) current.append(Expr.charAt(i));
            if (priority == 1) stack.push(Expr.charAt(i));
            if (priority > 1) {
                current.append(' ');
                while (!stack.empty()) {
                    if (getP(stack.peek()) >= priority) current.append(stack.pop());
                    else break;
                }
                stack.push(Expr.charAt(i));
            }
            if (priority == -1) {
                current.append(' ');
                while (getP(stack.peek()) != 1) current.append(stack.pop());
                    stack.pop();
            }
        }
        while (!stack.empty()) current.append(stack.pop());
        return current.toString();
    }
    private double RPNtoAnswer(String rpn) {
        StringBuilder operand = new StringBuilder("");
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;
            if (getP(rpn.charAt(i))== 0) {
                while (rpn.charAt(i) != ' ' && getP(rpn.charAt(i)) == 0) {
                    operand.append(rpn.charAt(i++));
                    if (i == rpn.length()) break;
                }
                    stack.push(Double.parseDouble(operand.toString()));
                    operand = new StringBuilder();
                }
            if (getP(rpn.charAt(i)) > 1) {
                double a = stack.pop(), b = stack.pop();
                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
            }
        }
        return stack.pop();
    }
    private int getP(char token){
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;
    }
    private static final double ratio = 0.01;
    public static String get(double val) {
        for (int i = 1;; i++) {
            double tem = val / (1D / i);
            if (Math.abs(tem - Math.round(tem)) < ratio)
                return Math.round(tem) + "/" + i;
        }
    }
    public double decide(String expression) {
        String rpn = ExpressionToRPN(expression);
        return RPNtoAnswer(rpn);
    }
}
