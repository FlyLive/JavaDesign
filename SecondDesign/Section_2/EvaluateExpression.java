package Section_2;

import java.util.Stack;
import java.util.StringTokenizer;

public class EvaluateExpression {
	/** 为四个数查找解决方法 */
	public static String findSolution(int a, int b, int c, int d) {
		String solution = "";
		String[] operators = {"+", "-", "*", "/"};

		int[][] allCombinations = {
				{ a, b, c, d }, { a, b, d, c },
				{ a, c, b, d }, { a, c, d, b },
				{ a, d, b, c }, { a, d, c, b },
				
				{ b, a, c, d }, { b, a, d, c },
				{ b, c, a, d }, { b, c, d, a },
				{ b, d, a, c }, { b, d, c, a },
				
				{ c, a, b, d }, { c, a, d, b },
				{ c, b, a, d }, { c, b, d, a },
				{ c, d, a, b }, { c, d, b, a },
				
				{ d, a, b, c }, { d, a, c, b },
				{ d, b, a, c }, { d, b, c, a },
				{ d, c, a, b }, { d, c, b, a }
			};

		for (String firstOp : operators){
			
			for (String secondOp : operators){
				
				for (String thirdOp : operators){
					
					for (int[] cardNums : allCombinations){
						
						for (int i = 0; i < 3; i++){
							
							for (int j = 0; j < 5; j++) {
								if (i == 0) {
									if (j == 0) {
										solution = cardNums[0] + firstOp
												+ cardNums[1] + secondOp
												+ cardNums[2] + thirdOp
												+ cardNums[3];
									}
									else if (j == 1) {
										solution = "(" + cardNums[0] + firstOp
												+ cardNums[1] + ")" + secondOp
												+ cardNums[2] + thirdOp
												+ cardNums[3];
									}
									else if (j == 2) {
										solution = cardNums[0] + firstOp + "("
												+ cardNums[1] + secondOp
												+ cardNums[2] + ")" + thirdOp
												+ cardNums[3];
									}
									else if (j == 3) {
										solution = cardNums[0] + firstOp
												+ cardNums[1] + secondOp + "("
												+ cardNums[2] + thirdOp
												+ cardNums[3] + ")";
									}
									else if (j == 4) {
										solution = "(" + cardNums[0] + firstOp
												+ cardNums[1] + ")" + secondOp
												+ "(" + cardNums[2] + thirdOp
												+ cardNums[3] + ")";
									}
								}
								else if (i == 1) {
									if (j == 0) {
										solution = "(" + cardNums[0] + firstOp
												+ cardNums[1] + secondOp
												+ cardNums[2] + ")" + thirdOp
												+ cardNums[3];
									}
									else if (j == 1) {
										solution = "((" + cardNums[0]
												+ firstOp + cardNums[1] + ")"
												+ secondOp + cardNums[2]
												+ ")" + thirdOp
												+ cardNums[3];
									}
									else if (j == 2) {
										solution = "(" + cardNums[0]
												+ firstOp + "(" + cardNums[1]
												+ secondOp
												+ cardNums[2] + "))"
												+ thirdOp + cardNums[3];
									}
								}
								else if (i == 2) {
									if (j == 0) {
										solution = cardNums[0] + firstOp
												+ "("
												+ cardNums[1] + secondOp
												+ cardNums[2] + thirdOp
												+ cardNums[3] + ")";
									}
									else if (j == 1) {
										solution = cardNums[0] + firstOp
												+ "(("
												+ cardNums[1] + secondOp
												+ cardNums[2] + ")" + thirdOp
												+ cardNums[3] + ")";
									}
									else if (j == 2) {
										solution = cardNums[0] + firstOp + "("
												+ cardNums[1] + secondOp + "("
												+ cardNums[2] + thirdOp
												+ cardNums[3] + "))";
									}
								}
								if (EvaluateExpression
										.evaluateExpression(solution) == 24){
									return solution;
								}
							}
						}
					}
				}
			}
		}
		return "No solution";
	}
	/** 验证输入算式是否正确 */
	public static double evaluateExpression(String expression) {
		// 新建数字存储栈
		Stack<Double> operandStack = new Stack<Double>();
		// 新建操作符存储栈
		Stack<Character> operatorStack = new Stack<Character>();
		// 分拆操作符和操作数
		StringTokenizer tokens = new StringTokenizer(
				expression, "()+-/*", true);
		// 扫描并筛选操作符和操作数
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken().trim();
			if (token.length() == 0){
				continue;
			}
			else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
				while (!operatorStack.isEmpty()
						&& (operatorStack.peek().equals('+')
								|| operatorStack.peek().equals('-')
								|| operatorStack.peek().equals('*') || operatorStack
								.peek().equals('/'))) {
					processAnOperator(operandStack, operatorStack);
				}

				operatorStack.push(new Character(token.charAt(0)));
			} else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
				while (!operatorStack.isEmpty()
						&& (operatorStack.peek().equals('*') || operatorStack
								.peek().equals('/'))) {
					processAnOperator(operandStack, operatorStack);
				}

				operatorStack.push(new Character(token.charAt(0)));
			} else if (token.trim().charAt(0) == '(') {
				operatorStack.push(new Character('('));
			} else if (token.trim().charAt(0) == ')') {
				while (!operatorStack.peek().equals('(')) {
					processAnOperator(operandStack, operatorStack);
				}

				operatorStack.pop();
			} else {
				operandStack.push(new Double(token));
			}
		}
		// 运用完所有操作符
		while (!operatorStack.isEmpty()) {
			processAnOperator(operandStack, operatorStack);
		}
		//返回计算结果
		return ((Double) (operandStack.pop())).doubleValue();
	}
	/**	根据操作符和数来计算算式 */
	public static void processAnOperator(Stack<Double> operandStack,
			Stack<Character> operatorStack) {
		if (operatorStack.peek().equals('+')) {
			operatorStack.pop();
			double op1 = ((Double) (operandStack.pop())).doubleValue();
			double op2 = ((Double) (operandStack.pop())).doubleValue();
			operandStack.push(new Double(op2 + op1));
		} else if (operatorStack.peek().equals('-')) {
			operatorStack.pop();
			double op1 = ((Double) (operandStack.pop())).doubleValue();
			double op2 = ((Double) (operandStack.pop())).doubleValue();
			operandStack.push(new Double(op2 - op1));
		} else if (operatorStack.peek().equals('*')) {
			operatorStack.pop();
			double op1 = ((Double) (operandStack.pop())).doubleValue();
			double op2 = ((Double) (operandStack.pop())).doubleValue();
			operandStack.push(new Double(op2 * op1));
		} else if (operatorStack.peek().equals('/')) {
			operatorStack.pop();
			double op1 = ((Double) (operandStack.pop())).doubleValue();
			double op2 = ((Double) (operandStack.pop())).doubleValue();
			operandStack.push(new Double(op2 / op1));
		}
	}
}