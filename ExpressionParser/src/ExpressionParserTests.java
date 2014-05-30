


/**
 * Class created to test {@link ExpressionParser}. Throws a battery of tests cases
 * at the different methods in {@link ExpressionParser} to make sure that it outputs
 * the desired result.
 *
 * @author David Boivin
 */
public class ExpressionParserTests {
	
	public static void test(){
		
		String e = new String();	//expected
		String r = new String();	//result
		
		System.out.println("Testing ExpressionParser class\n\n");
		
		System.out.println("Testing parse() method\n");
		
		//testing basic operations
		System.out.println("Basic Operations\n");
		
		System.out.println("1. 5+2");
		e = "[5.0, 2.0, +]";
		r = ExpressionParser.parse("5+2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("2. 5-2");
		e = "[5.0, 2.0, -]";
		r = ExpressionParser.parse("5-2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("3. 5*2");
		e = "[5.0, 2.0, *]";
		r = ExpressionParser.parse("5*2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("4. 5/2");
		e = "[5.0, 2.0, /]";
		r = ExpressionParser.parse("5/2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("5. 5^2");
		e = "[5.0, 2.0, ^]";
		r = ExpressionParser.parse("5^2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing whitespace
		System.out.println("6. |  5 +   2  |");
		e = "[5.0, 2.0, +]";
		r = ExpressionParser.parse("  5 +   2  ").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing decimals
		System.out.println("7. 2.456+1.233");
		e = "[2.456, 1.233, +]";
		r = ExpressionParser.parse("2.456+1.233").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing single number input TODO
		System.out.println("8. 3");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		//testing negatives	TODO
		System.out.println("9. 2+-2");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("10. -2+2");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("11. -2+-2");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		//testing multiple operator expressions
		System.out.println("12. 2+4+5+6");
		e = "[2.0, 4.0, +, 5.0, +, 6.0, +]";
		r = ExpressionParser.parse("2+4+5+6").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("13. 64/2/16");
		e = "[64.0, 2.0, /, 16.0, /]";
		r = ExpressionParser.parse("64/2/16").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing operator priority
		System.out.println("14. 64/2-12");
		e = "[64.0, 2.0, /, 12.0, -]";
		r = ExpressionParser.parse("64/2-12").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("15. 12-64/2");
		e = "[12.0, 64.0, 2.0, /, -]";
		r = ExpressionParser.parse("12-64/2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("16. 12/4-64/2");		//TODO need to fix the sub expression stack logic (might need to use queue)
		e = "[12.0, 4.0, /, 64.0, 2.0, /, -]";
		r = ExpressionParser.parse("12/4-64/2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("17. 2^4/2");
		e = "[2.0, 4.0, ^, 2.0, /]";
		r = ExpressionParser.parse("2^4/2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("18. 2^4/2-3");
		e = "[2.0, 4.0, ^, 2.0, /, 3.0, -]";
		r = ExpressionParser.parse("2^4/2-3").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("19. 3-2/4^2");
		e = "[3.0, 2.0, 4.0, 2.0, ^, /, -]";
		r = ExpressionParser.parse("3-2/4^2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing brackets within the expression
		System.out.println("20. (6-2)/4");
		e = "[6.0, 2.0, -, 4.0, /]";
		r = ExpressionParser.parse("(6-2)/4").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("20. 4/(6-2)");
		e = "[4.0, 6.0, 2.0, -, /]";
		r = ExpressionParser.parse("4/(6-2)").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("21. (6+6)/(6-2)");
		e = "[6.0, 6.0, +, 6.0, 2.0, -, /]";
		r = ExpressionParser.parse("(6+6)/(6-2)").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("22. ((6-2))");
		e = "[6.0, 2.0, -]";
		r = ExpressionParser.parse("((6-2))").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing general long expressions
		System.out.println("23. 2+3*32/64^(1/2)-18*2");
		e = "[2.0, 3.0, 32.0, 64.0, 1.0, 2.0, /, ^, /, +, 18.0, 2.0, *, -]";
		r = ExpressionParser.parse("2+3*32/64^(1/2)-18*2").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		System.out.println("24. ((3*(12+2-8))/((24+6)/10))^(1/3)");
		e = "[3.0, 12.0, 2.0, +, 8.0, -, *, 24.0, 6.0, +, 10.0, /, /, 1.0, 3.0, /, ^]";
		r = ExpressionParser.parse("(3*(12+2-8))/((24+6)/10)^(1/3)").toString();
		System.out.println("Expected: " + e);
		System.out.println("Result: " + r);
		System.out.println("\t\t\t\t\tSuccess: " + e.equals(r));
		System.out.println("\n");
		
		//testing error handling TODO
		System.out.println("25. 2 2+8");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("26. 2+8+");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("27. (2+4");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("28. 2+4)");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("29. 2 2+");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		System.out.println("30. 2++4");
		System.out.println("Not implemented yet");
		System.out.println("\n");
		
		//System.out.println("Testing evaluate() method");
	}
}
