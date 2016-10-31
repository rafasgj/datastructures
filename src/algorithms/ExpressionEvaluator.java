package algorithms;

import java.util.Locale;
import java.util.Scanner;

import datastructures.Stack;

/**
 * This class provides an arithmetic expression evaluator, using a
 * stack evaluator.
 */
public class ExpressionEvaluator {
	private static String toPostfix(String expression, Locale locale) {
		Scanner sc = new Scanner(expression);
		sc.useLocale(locale);
		String out = "";
		
		Stack<String> opStack = new Stack<String>();
	
		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				out += " " + sc.nextDouble();
			} else {
				String op = sc.next();
				switch (op) {
					case ")":
						while (!opStack.peek().equals("("))
							out += " " + opStack.pop();
						opStack.pop();
						break;
					case "(":
						opStack.push(op);
						break;
					case "*":
					case "/":
						if (!opStack.isEmpty()&&"*/".contains(opStack.peek()))
							out += " " + opStack.pop();
						opStack.push(op);
						break;
					case "+":
					case "-":
						while(!opStack.isEmpty()&&!opStack.peek().equals("("))
							out += " " + opStack.pop();
						opStack.push(op);
						break;
					default:
						break;
				}
			}
		}
		while (!opStack.isEmpty())
			out += " " + opStack.pop();

		sc.close();
		return out;
	}
	
	/**
 	 * Computes the value of a given infix arithmetic expression. The
 	 * expresion is parsed used the number rules for en-US locale.
 	 * @param exp The arithmetic expression.
 	 * @return The result of the expression. 
 	 */
	public static double compute(String exp) {
		return compute(exp,Locale.US);
	}

	/**
 	 * Computes the value of a given infix arithmetic expression. The
 	 * expresion is parsed used the number rules of a given locale.
 	 * @param exp The arithmetic expression.
 	 * @param locale The locale rules to use.
 	 * @return The result of the expression. 
 	 */
	public static Double compute(String exp, Locale locale) {
		String rep = ("0 "+exp).replaceAll("([\\*\\+\\-\\/\\(\\)])"," $1 ");
		String expression = toPostfix(rep,locale);
		Scanner sc = new Scanner(expression);
		sc.useLocale(locale);
		Stack<Double> operands = new Stack<Double>();
		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				operands.push(sc.nextDouble());
			} else {
				double rhs = operands.pop();
				double lhs = operands.pop();
				switch (sc.next()) {
				case "*":
					operands.push(lhs * rhs);
					break;
				case "/":
					operands.push(lhs / rhs);
					break;
				case "-":
					operands.push(lhs - rhs);
					break;
				case "+":
					operands.push(lhs + rhs);
					break;
				}
			}
		}
		sc.close();
		double result = operands.pop();
		if (!operands.isEmpty()) {
			if (operands.pop() != 0 || !operands.isEmpty())
				return null;
		}
		return result;
	}
}
