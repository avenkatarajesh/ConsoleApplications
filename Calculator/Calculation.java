package basic_calculator;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Calculation {
	Scanner scan = new Scanner(System.in);

	public String display() {
		System.out.println("Arithmetic Calculator");
		System.out.print("	");
		System.out.println("-------------");
		System.out.print("	");
		System.out.println("| 1 2 3 4 5 |");
		System.out.print("	");
		System.out.println("| 6 7 8 9 0 |");
		System.out.print("	");
		System.out.println("| / * - + = |");
		System.out.print("	");
		System.out.println("-------------");

		System.out.println("Enter the values : ");
		String eq = scan.next();

		return eq;
	}

//	if (eq.charAt(index) == '(') {
//		operand += '(';
//		expression.push(operand);
//	} else if (eq.charAt(index) > 47 && eq.charAt(index) < 57 || eq.charAt(index) == 42
//			|| eq.charAt(index) == 43 || eq.charAt(index) == 45 || eq.charAt(index) == 47) {
//		operand += eq.charAt(index);
//	} else if (eq.charAt(index) == ')') {
//
//		while (expression.pop() != "(") {
//			operand += expression.pop();
//		}
//		operand = evaluating(operand);
//	} else {
//		ans = Double.valueOf(evaluating(operand));
//	}

	public double calculate(String eq) {
		boolean check = isValid(eq);
//		Stack<String> expression = new Stack<>();
		int ans = 0;
		if (check) {
				ans = Integer.valueOf(evaluating(eq ));
		} else {
			throw new IllegalArgumentException();
		}
		return ans;
	}

	public boolean paranthesis(String str) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.push('(');
			} else if (str.charAt(i) == ')') {
				try {
					stack.pop();
				} catch (EmptyStackException e) {
					return false;
				}
			}
		}
		if (stack.empty())
			return true;
		else
			return false;
	}

	public boolean isValid(String eq) {
		boolean para = paranthesis(eq);
		if (para) {
			if (eq.charAt(0) == 42 || eq.charAt(0) == 43 || eq.charAt(0) == 45 || eq.charAt(0) == 47)
				return false;
			for (int i = 0; i < eq.length(); i++) {
				char ch = eq.charAt(i);
				if (ch > 47 && ch < 58 || ch == '(' || ch == ')') {
					continue;
				} else if (ch == 42 || ch == 43 || ch == 45 || ch == 47) {
					char ch1 = eq.charAt(i - 1);
					if (ch == ch1) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public String evaluating(String exp ) {
		int i = 0;
		String result = "";
		int operand1 = 0;
		while (exp.charAt(i) > 47 && exp.charAt(i) < 58 && i < exp.length()) {
			operand1 *= 10;
			operand1 += exp.charAt(i) - 48;
			if (i + 1 < exp.length()) {
				i++;
			} else {
				break;
			}
		}
		char ch = exp.charAt(i++);
		int operand2 = 0;
		while (exp.charAt(i) > 47 && exp.charAt(i) < 58 && i < exp.length()) {
			operand2 *= 10;
			operand2 += exp.charAt(i) - 48;
			if (i + 1 < exp.length()) {
				i++;
			} else {
				break;
			}
		}
		int ans = 0;
		switch (ch) {
		case '+':
			ans = operand1 + operand2;
			break;
		case '-':
			ans = operand1 - operand2;
			break;
		case '*':
			ans = operand1 * operand2;
			break;
		case '/':
			ans = operand1 / operand2;
			break;
		}
		result = Integer.toString(ans);
		if(exp.length()-1 > i ){
			String s = result;
			s += exp.substring(i);
			result = evaluating(s);
		}
		return result;
	}

	public static void main(String[] args) {
		Calculation cal = new Calculation();
		String equation = cal.display();
		if (cal.isValid(equation)) {
			System.out.println(cal.calculate(equation));
		} else {
			System.out.println("Invalid expression");
		}
	}
}
