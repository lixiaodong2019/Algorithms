package stack.calculator;

import stack.LinkedListStack;
import stack.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 后缀表达式实现的计算器,分为2步
 * 1.将中缀表达式转成后缀表达式
 * 2.通过后缀表达式进行运算
 */
public class Calculator {

    public static void main(String[] args) {
        String exp = "10+1212-1212+21";
        double calculate;
        try {
            calculate = Calculator.calculate(exp);
            System.out.println(getRightExp(exp.replace(" ", "")) + " = " + calculate);
        } catch (Exception e) {
            System.out.println("表达式不正确~~");
        }
    }


    private static String getRightExp(String exp) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (isNum(ch) || isOperator(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    /**
     * 分为3步:
     * 1.将表达式转成中缀数组
     * 2.将中缀数组转成后缀数组
     * 3.使用后缀数组进行运算,得到结果
     * List<String> infixArrayList = getInfixArrayList(); //得到中缀表达式
     * //后缀数组进行运算得到结果
     * List<String> suffixArrayList = getSuffixArrayList(infixArrayList);
     * //将后缀数组进行计算
     * calculate(getSuffixArrayList(getInfixArrayList()));
     *
     * @return
     */
    public static double calculate(String expression) {
        return calculate(getSuffixArrayList(getInfixArrayList(expression)));
    }


    /**
     * 对后缀表达式进行计算
     * 从左到右扫描, 遇到数字时,将数字压入栈中,遇到运算符,弹出栈顶的两个元素
     * 进行计算,将结果入栈,重复操作
     *
     * @param suffixArrayList
     * @return
     */
    private static double calculate(List<String> suffixArrayList) {
        Stack<String> result = new LinkedListStack<>();
        for (int i = 0; i < suffixArrayList.size(); i++) {
            String ch = suffixArrayList.get(i);
            if (isNum(ch)) {
                result.push(ch);
            } else {
                double num1 = Double.parseDouble(result.pop());
                double num2 = Double.parseDouble(result.pop());
                double res = calculate(num1, num2, ch.charAt(0));
                result.push(String.valueOf(res));
            }
        }
        return Double.parseDouble(result.pop());
    }


    private static double calculate(double num1, double num2, char op) {
        switch (op) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
        }
        return 0;
    }

    /**
     * 将中缀表达式数组转成后缀表达式
     * 过程:
     * 1.初始化两个栈,运算符栈s1和存储中间结果栈s2,
     * 2从左至右扫描中缀表达式
     * 3.遇到操作数时,将其压入s2
     * 4.遇到运算符时,比较s1栈顶的运算符的优先级
     * 4.1,如果s1为空,或者栈顶的运算符为(,直接将该运算符入栈
     * 否则,若优先级比栈顶的高,也将运算符压到s1,
     * 否则,将s1栈顶的运算符弹出压到s2中,再次转到4.1与s1中的新的栈顶运算符做比较
     * 5.遇到括号
     * 如果是(:直接入栈
     * 如果是):依次弹出s1栈顶运算符,压入s2中,直到遇到左括号为止
     * 6.重复2-5,直到表达式的最右边
     * 7.将s1剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中元素,即为中缀表达式对应的后缀表达式
     * 1+((2+1*2)-2*4)-5
     *
     * @param infixArrayList 中缀表达式的数组
     * @return 后缀表达式数组
     */
    private static List<String> getSuffixArrayList(List<String> infixArrayList) {
        Stack<String> s1 = new LinkedListStack<>();
        Stack<String> s2 = new LinkedListStack<>();

        for (int i = 0; i < infixArrayList.size(); i++) {
            String ch = infixArrayList.get(i); //得到每一个元素
            if (isNum(ch)) {
                s2.push(ch);
            } else if (isOperator(ch)) {
                compare(s1, s2, ch);
            } else if (ch.equals("(")) {
                s1.push(ch);
            } else if (ch.equals(")")) {
                while (true) {
                    String temp = s1.pop();
                    if (temp.equals("(")) {
                        break;
                    }
                    s2.push(temp);
                }
            }
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        return getArray(s2);
    }

    /**
     * 核心代码
     *
     * @param s1 栈1
     * @param s2 栈2
     * @param ch 当前的字符串
     */
    private static void compare(Stack<String> s1, Stack<String> s2, String ch) {
        String pop;
        //如果s1为空或者栈顶运算符为(,直接压入
        if (s1.isEmpty() || s1.peek().equals("(")) {
            s1.push(ch);
        } else if (property(ch) > property(s1.peek())) {
            s1.push(ch);
        } else {
            pop = s1.pop();
            s2.push(pop);
            compare(s1, s2, ch);
        }
    }

    /**
     * 栈转数组
     *
     * @param stack
     * @return
     */
    private static List<String> getArray(Stack<String> stack) {
        List<String> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 将中缀表达式变成中缀数组,采用扫描方式
     *
     * @return
     */
    private static List<String> getInfixArrayList(String expression) {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < expression.length(); index++) {
            char ch = expression.charAt(index); //获得每个字符串
            if (isOperator(ch) || ch == '(' || ch == ')') {
                list.add(ch + "");  //如果是运算符号,直接添加
            } else if (isNum(ch)) {
                StringBuilder numStr = new StringBuilder(ch + "");//数字拼接
                while (true) {
                    if (index == expression.length() - 1) {
                        break;
                    }
                    char c = expression.charAt(index + 1);
                    if (isNum(c)) {
                        numStr.append(c);
                        index++;
                    } else if (isOperator(c) || c == '(' || c == ')') {
                        break;
                    } else {
                        index++;
                    }
                }
                String num = numStr.toString();
                list.add(String.valueOf(Double.parseDouble(num)));
            }
        }
        return list;
    }

    private static int property(String ch) {
        switch (ch) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            default:
                return -1;
        }
    }

    /**
     * 判断字符是否为运算符
     *
     * @param ch 符号
     * @return
     */
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean isOperator(String ch) {
        return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
    }

    /**
     * 判断字符是否为数字
     *
     * @param ch 符号
     * @return
     */
    private static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9' || ch == '.';
    }

    private static boolean isNum(String str) {
        return str.matches("^\\d+\\.\\d+$");
    }

}
