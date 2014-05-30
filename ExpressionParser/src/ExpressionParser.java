


import java.util.ArrayDeque;
import java.util.Stack;

/**
 * This class contains different methods to allow a mathematical expression
 * to be parsed and evaluated. This class parses the original
 * String InFix expression into a PostFix (Reverse-Polish) using the 
 * {@link #parse(String)}. Next the user can, if they so wish, to use the 
 * {@link #evaluate(ArrayDeque)} method to evaluate the parsed method. This
 * process is streamlined in the {@link #parseAndEval(String)} method which
 * parses and evaluates the expression in one method using the previously 
 * mentioned methods.
 *
 * @author David Boivin
 */
public class ExpressionParser {
	
// Constants ----------------------------------------------------------------------------------- //
	
	public static final String ADDITION = "+";
	public static final String SUBTRACTION = "-";
	public static final String MULTIPLICATION = "*";
	public static final String DIVISION = "/";
	public static final String EXPONENT = "^";
	
	public static final String[] OPERATORS = 	{ADDITION, 
							SUBTRACTION, 
							MULTIPLICATION, 
							DIVISION, 
							EXPONENT};
	
	public static final String DECIMAL = ".";
	public static final String OPEN_BRACKET = "(";
	public static final String CLOSE_BRACKET = ")";

// Public Methods ------------------------------------------------------------------------------ //
	
	/**
	 * Takes a mathematical expression in PostFix (Reverse-Polish) notation
	 * held in a queue and evaluates it using a stack method.
	 * 
	 * @param expression The expression in PostFix notation.
	 * 
	 * @return The result of the expression.
	 */
	public static double evaluatePostfix(ArrayDeque<String> expression){
		
		/* 			***NOTE***				*/
		/* All numbers held within the stack are in reverse order. 	*/
		/*								*/
		/* e.i.queue = [2,3,-] --> stack = [2,3] --> stack.pop() #1 = 3 */
		/* 					 --> stack.pop() #2 = 2 */
		/*								*/
		
		Stack<Double> stack = new Stack<Double>();
		
		//iterate through the expression
		while(!expression.isEmpty()){
			
			//checks if the next token is an operator
			if(ExpressionParser.isOperator(expression.peek())){
				
				//calculates the result of the operator and adds it to the stack
				stack.push(ExpressionParser.calculate(expression.remove(),
									stack.pop(), 
									stack.pop()));
				continue;
			}
			
			stack.push(Double.parseDouble(expression.remove()));	//TODO add error handle
		}
		
		//TODO add error check to make sure stack only has one element
		
		//return result
		return stack.pop();
	}
	
	/**
	 * Takes the initial form of the expression (InFix mathematical expression 
	 * in String form) and parses it. The output is in the form of a queue in 
	 * PostFix (Reverse-Polish) notation.
	 * 
	 * 
	 * @param expression The String representation of the expression.
	 * 
	 * @return A PostFix representation of the expression in a queue.
	 */
	public static ArrayDeque<String> parse(String expression){
		
		//data storage
		ArrayDeque<ArrayDeque<String>> subEqStack = new ArrayDeque<ArrayDeque<String>>();
		Stack<Double> numStack = new Stack<Double>();
		Stack<String> opStack = new Stack<String>();
		
		//iterate through expression
		for(int i = 0; i < expression.length(); ++i){
			String token = String.valueOf(expression.charAt(i));
			
			//determine what type of token the current token is and take
			//appropriate action
			if(ExpressionParser.isOperator(token)){				//case: operator
				
				//BEDMAS implementation. If operator has a higher priority then put in
				//queue before adding the new operator to stack
				while(!opStack.isEmpty() && ExpressionParser.getOperatorPriority(token) <=
						ExpressionParser.getOperatorPriority(opStack.peek())){
					ExpressionParser.addToQueue(subEqStack, opStack, numStack);
				}
				
				//add operator to stack
				opStack.push(token);
				
			}else if(ExpressionParser.isPartOfNumber(token)){		//case: digit or decimal
				
				//gets the full number within the string expression and updates the index
				token = ExpressionParser.getFullNumber(expression, i);
				i += token.length()-1;
				
				//add the number to the number stack
				numStack.add(Double.parseDouble(token));	//TODO add error handle here in case token is an invalid number (example 2.3434.232)
				
			}else if(token.equals(ExpressionParser.OPEN_BRACKET)){		//case: open bracket
				
				//get the sub expression in the bracket (parenthesis) and updates the index
				String subExpression = ExpressionParser.getSubExpression(expression, i);
				i += subExpression.length()-1;	//index is now at closing bracket
				
				//parse the sub expression and indicate that there is now a new sub expression in the stack
				subEqStack.add(ExpressionParser.parse(
						subExpression.substring(1,subExpression.length()-1)));
				numStack.push(null);
				
			}else if(token.equals(" ")){					//case: space
				//ignore
				
			}else{								//case: everything else
				//TODO throw error with an indicator to what character is invalid
				System.out.println("Invalid token:" + token);
			}
			
		}
		
		//empty the operator stack
		while(!opStack.isEmpty()){
			ExpressionParser.addToQueue(subEqStack, opStack, numStack);
		}
		
		//returns the final post-fix queue only if there is only one queue left in the subEqStack and
		//the final number stack contains the final null placeholder. TODO must make this work for stupidity like "3" <- one number as the expression
		if(numStack.size() == 1 && subEqStack.size() == 1){	
			return subEqStack.remove();
		}
		
		return null;//TODO throw error because not all numbers have been used (problems with the format of the expression)
	}
	
	
	//TODO change this method to be more efficient that using both the parse and evaluate methods but still using stacks
	/**
	 * This method is more of a convenience than anything else, 
	 * but it uses the {@link #parse} and {@link #evaluate} methods held within
	 * this class ({@link ExpressionParser}) and both parses the and evaluates 
	 * the given mathematical expression.
	 *
	 * @param expression The String representation of the expression.
	 * 
	 * @return The result of the expression.
	 */
	public static double parseAndEval(String expression){
		
		//parse and evaluate the expression in one go.
		return ExpressionParser.evaluatePostfix(ExpressionParser.parse(expression));
	}
	
// Private Methods ----------------------------------------------------------------------------- //
	
	/**
	 * This method takes 2 values from the number stack and 1 value from the operator stack
	 * and combines them into a PostFix queue and adds that queue to the queue stack. If one of the
	 * values for the numbers ends up being a <b>null</b>, this method will take one whole
	 * queue and add it to the new queue as if it where a number.
	 *
	 * @param queueStack The Stack of PostFix sub queues.
	 * @param opStack The operator stack to take the operator from.
	 * @param numStack The number stack to take the numbers from.
	 */
	private static void addToQueue(ArrayDeque<ArrayDeque<String>> queueStack, 
			Stack<String> opStack, Stack<Double> numStack){
		
		ArrayDeque<String> queue = new ArrayDeque<String>();
		
		//takes the necessary values from the proper stacks	TODO error could be thrown here as stacks can be empty
		String operator = opStack.pop();
		Double[] operands = {numStack.pop(), numStack.pop()};	//index 0 = operand2 , index 1 = operand1
		
		//add proper operands (or sub expressions) to the queue
		for(int i = 1; i >= 0; --i){
			if(operands[i] == null){
				queue.addAll(queueStack.remove());
			}else{
				queue.add(String.valueOf(operands[i]));
			}
		}
		
		//adds operator to queue
		queue.add(operator);
		
		//indicate that there is a sub expression in the queueStack
		numStack.push(null);
		
		//add queue to queueStack
		queueStack.add(queue);
	}
	
	/**
	 * Calculates the result of the two given operands using the given operator
	 * to do the operation.
	 *
	 * @param operator The symbol of the operation to perform on the two operators.
	 * @param operand1 The first operand.
	 * @param operand2 The second operand
	 * 
	 * @return The result of the calculation.
	 */
	private static double calculate(String operator, double operand2, double operand1){
		
		switch(operator){
			
			case ADDITION:			//case: +
				return operand1 + operand2;
				
			case SUBTRACTION: 		//case: -
				return operand1 - operand2;
			
			case MULTIPLICATION:		//case: *
				return operand1 * operand2;
				
			case DIVISION: 			//case: /
				return operand1 / operand2;
				
			case EXPONENT: 			//case: ^
				return Math.pow(operand1, operand2);
			
			default:			//case: anything else
				return -1;	//TODO change to throw an exception
		}
	}
	
	/**
	 * Obtains the fully fledged number in the expression starting at the given index within the
	 * expression. This method does <b>NOT</b> assume the starting index is a number, and if it is
	 * not, this method will return an empty string. <br><br><b>Note:</b> This method will return
	 * an empty string if <code> index >= expression.length()</code>.
	 *
	 * @param expression The expression to get the number from
	 * @param index The starting point within the expression.
	 * 
	 * @return The full number.
	 */
	private static String getFullNumber(String expression, int index){
		
		StringBuffer num = new StringBuffer();
		
		//iterates through the expression and checks if the current value of the string is
		//considered part of a number
		while(index < expression.length() &&
				ExpressionParser.isPartOfNumber(String.valueOf(expression.charAt(index)))){
			
			//adds the number part to the return string
			num.append(String.valueOf(expression.charAt(index)));
			
			//increment
			++index;
		}
		
		return num.toString();
	}
	
	/**
	 * Obtains the string between an open and a close bracket. The start index is
	 * included in the returned sub expression and so is the closing bracket
	 *
	 * @param expression The full expression.
	 * @param index The starting index.
	 * 
	 * @return The sub expression represented by the brackets.
	 */
	private static String getSubExpression(String expression, int index){
		
		//makes sure that start index is an open bracket
		if(!String.valueOf(expression.charAt(index)).equals(ExpressionParser.OPEN_BRACKET)){
			//TODO throw error
		}
		
		StringBuffer str = new StringBuffer();
		int openBracketCount = 0;
		
		//iterate through expression
		while(true){
			
			//for easy access
			String temp = String.valueOf(expression.charAt(index));	//TODO add error handle if the index is out of range (no closing brackets found)
			
			if(temp.equals(ExpressionParser.OPEN_BRACKET)){
				str.append(temp);
				++openBracketCount;	//compensates for open brackets found in sub expression
				
			}else if(temp.equals(ExpressionParser.CLOSE_BRACKET)){
				//takes appropriate action based on value of openBracketCount
				if(openBracketCount == 1){
					str.append(temp);
					break;	//the sub expression has been found
					
				}else{
					str.append(temp);
					--openBracketCount;	//update the number of unmatched open brackets
				}
			}else{
				str.append(temp);	//adds the value to the sub expression
			}
			
			//increment index
			++index;	
		}
		
		return str.toString();
	}
	
	/**
	 * Determines if the given token is considered a valid operator by
	 * this class using this class' list of valid operators.
	 *
	 * @param token The String to be checked
	 * 
	 * @return This method returns <b>true</b> if the token is considered an
	 * 		operator and returns <b>false</b> otherwise.
	 */
	private static boolean isOperator(String token){
		
		//checks if the token matches any of the accepted operators
		for(int i = 0; i < OPERATORS.length; ++i){
			if(token.equals(OPERATORS[i])){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determines whether the given token is a number or a decimal, 
	 * hence part of a number.
	 *
	 * @param token The String to be checked.
	 * 
	 * @return This method returns <b>true</b> if the token is considered a 
	 * 		a number or a decimal point and returns <b>false</b> 
	 * 		otherwise.
	 */
	private static boolean isPartOfNumber(String token){
		
		//checks to see if the token is considered a digit or a decimal
		if(Character.isDigit(token.charAt(0)) ||
				token.equals(ExpressionParser.DECIMAL)){
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Checks the given operator against known operators and returns 
	 * the level which it is defined at. Basically this method implements
	 * the mathematical BEDMAS (PEDMAS) rule. The list of defined operators
	 * should be the same as that of accepted operators, which is defined by 
	 * the {@link ExpressionParser#OPERATORS} field.
	 * 
	 * <br><br><b>Priority:</b>
	 * 	<ul>
	 * 		<li>^ = 2
	 * 		<li>* , / = 1
	 * 		<li>+, - = 0
	 * 	</ul>
	 *
	 * @param op Operator to be checked.
	 * 
	 * @return The priority level of the operator.
	 */
	private static int getOperatorPriority(String op){
		
		//checks what operator it is and returns proper priority
		switch(op){
			case EXPONENT:
				return 2;
				
			case MULTIPLICATION:
				return 1;
				
			case DIVISION:
				return 1;
				
			case ADDITION:
				return 0;
				
			case SUBTRACTION:
				return 0;
				
			default:
				return -1;	//TODO change to throw an error
		}
	}
}
