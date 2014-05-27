


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
	
// Constants -------------------------------------------------------------------- //
	
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

// Public Methods --------------------------------------------------------------- //
	
	/**
	 * Takes a mathematical expression in PostFix (Reverse-Polish) notation
	 * held in a queue and evaluates it using a stack method.
	 * 
	 * @param expression The expression in PostFix notation.
	 * 
	 * @return The result of the expression.
	 */
	public static double evaluate(ArrayDeque<String> expression){
		
		/* 						***NOTE***								*/
		/* All numbers held within the stack are in reverse order. 		*/
		/*																*/
		/* e.i.queue = [2,3,-] --> stack = [2,3] --> stack.pop() #1 = 3 */
		/* 										 --> stack.pop() #2 = 2 */
		/*																*/
		
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
		return null;	//TODO implement
	}
	
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
		return ExpressionParser.evaluate(ExpressionParser.parse(expression));
	}
	
// Private Methods -------------------------------------------------------------- //
	
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
	private static Double calculate(String operator, double operand2, double operand1){
		
		switch(operator){
			
			case ADDITION:			//case: +
				return operand1 + operand2;
				
			case SUBTRACTION: 		//case: -
				return operand1 - operand2;
			
			case MULTIPLICATION:	//case: *
				return operand1 * operand2;
				
			case DIVISION: 			//case: /
				return operand1 / operand2;
				
			case EXPONENT: 			//case: ^
				return Math.pow(operand1, operand2);
			
			default:				//case: anything else
				return null;
		}
	}
	
	/**
	 * Determines if the given token is considered a valid operator by
	 * this class using this class' list of valid operators.
	 *
	 * @param token The String to be checked
	 * 
	 * @return This method returns <b>true</b> if the token is considered an
	 * 			operator and returns <b>false</b> otherwise.
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
}
