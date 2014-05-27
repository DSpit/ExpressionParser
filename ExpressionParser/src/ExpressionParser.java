


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

// Public Methods --------------------------------------------------------------- //
	
	/**
	 * Takes the initial form of the expression (InFix mathematical expression 
	 * in String form) and parses it. The output is in the form of a queue in 
	 * PostFix (Reverse-Polish) notation.
	 * 
	 * 
	 * @param expresson The String representation of the expression.
	 * 
	 * @return A PostFix representation of the expression in a queue.
	 */
	public static ArrayDeque<String> parse(String expression){
		return null;	//TODO implement
	}
	
	/**
	 * Takes a mathematical expression in PostFix (Reverse-Polish) notation
	 * held in a queue and evaluates it using a stack method.
	 * 
	 * @param expression The expression in PostFix notation.
	 * 
	 * @return The result of the expression.
	 */
	public static double evaluate(ArrayDeque<String> expression){
		return -1;		//TODO implement 
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
	
	
	
}
