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
			for (int index = 0; index < eq.length(); index++) {
				char operator = eq.charAt(index);
				if (operator > 47 && operator < 58 || operator == '(' || operator == ')') {
					continue;
				} else if (operator == 42 || operator == 43 || operator == 45 || operator == 47) {
					char operator2 = eq.charAt(index - 1);
					if (operator == operator2) {
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
		int iterate = 0;
		String result = "";
		int operand1 = 0;
		while (exp.charAt(iterate) > 47 && exp.charAt(iterate) < 58 && iterate < exp.length()) {
			operand1 *= 10;
			operand1 += exp.charAt(iterate) - 48;
			if (iterate + 1 < exp.length()) {
				iterate++;
			} else {
				break;
			}
		}
		char operator = exp.charAt(iterate++);
		int operand2 = 0;
		while (exp.charAt(iterate) > 47 && exp.charAt(iterate) < 58 && iterate < exp.length()) {
			operand2 *= 10;
			operand2 += exp.charAt(iterate) - 48;
			if (iterate + 1 < exp.length()) {
				iterate++;
			} else {
				break;
			}
		}
		int ans = 0;
		switch (operator) {
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
		if(exp.length()-1 > iterate ){
			String pushAns = result;
			pushAns += exp.substring(iterate);
			result = evaluating(pushAns);
		}
		return result;
	}

	public static void main(String[] args) {
		Calculation calculate = new Calculation();
		String equation = calculate.display();
		if (calculate.isValid(equation)) {
			System.out.println(calculate.calculate(equation));
		} else {
			System.out.println("Invalid expression");
		}
	}
}
