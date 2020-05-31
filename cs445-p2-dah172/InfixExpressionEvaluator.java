//DAVID HINTON (dah172) 4136270
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * This class uses two stacks to evaluate an infix arithmetic expression from an
 * InputStream. It should not create a full postfix expression along the way; it
 * should convert and evaluate in a pipelined fashion, in a single pass.
 */
public class InfixExpressionEvaluator {
	// Tokenizer to break up our input into tokens
	StreamTokenizer tokenizer;

	// Stacks for operators (for converting to postfix) and operands (for
	// evaluating)
	StackInterface<Character> operatorStack;
	StackInterface<Double> operandStack;

	/**
	 * Initializes the evaluator to read an infix expression from an input
	 * stream.
	 * @param input the input stream from which to read the expression
	 */
	public InfixExpressionEvaluator(InputStream input) {
		// Initialize the tokenizer to read from the given InputStream
		tokenizer = new StreamTokenizer(new BufferedReader(
						new InputStreamReader(input)));

		// StreamTokenizer likes to consider - and / to have special meaning.
		// Tell it that these are regular characters, so that they can be parsed
		// as operators
		tokenizer.ordinaryChar('-');
		tokenizer.ordinaryChar('/');

		// Allow the tokenizer to recognize end-of-line, which marks the end of
		// the expression
		tokenizer.eolIsSignificant(true);

		// Initialize the stacks
		operatorStack = new ArrayStack<Character>();
		operandStack = new ArrayStack<Double>();
	}

	/**
	 * Parses and evaluates the expression read from the provided input stream,
	 * then returns the resulting value
	 * @return the value of the infix expression that was parsed
	 */
	public Double evaluate() throws ExpressionError {
		// Get the first token. If an IO exception occurs, replace it with a
		// runtime exception, causing an immediate crash.
		try {
			tokenizer.nextToken();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Continue processing tokens until we find end-of-line
		while (tokenizer.ttype != StreamTokenizer.TT_EOL) {
			// Consider possible token types
			switch (tokenizer.ttype) {
				case StreamTokenizer.TT_NUMBER:
					// If the token is a number, process it as a double-valued
					// operand
					handleOperand((double)tokenizer.nval);
					break;
				case '+':
				case '-':
				case '*':
				case '/':
				case '^':
					// If the token is any of the above characters, process it
					// is an operator
					handleOperator((char)tokenizer.ttype);
					break;
				case '(':
				case '{':
				case '[':
					// If the token is open bracket, process it as such. Forms
					// of bracket are interchangeable but must nest properly.
					handleOpenBracket((char)tokenizer.ttype);
					break;
				case ')':
				case '}':
				case ']':
					// If the token is close bracket, process it as such. Forms
					// of bracket are interchangeable but must nest properly.
					handleCloseBracket((char)tokenizer.ttype);
					break;
				case StreamTokenizer.TT_WORD:
					// If the token is a name, process it as such.
					handleName(tokenizer.sval.toLowerCase());
					break;
				default:
					// If the token is any other type or value, throw an
					// expression error
					throw new ExpressionError("Unrecognized token: " +
									String.valueOf((char)tokenizer.ttype));
			}

			// Read the next token, again converting any potential IO exception
			try {
				tokenizer.nextToken();
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}

		// Almost done now, but we may have to process remaining operators in
		// the operators stack
		handleRemainingOperators();

		// Return the result of the evaluation

		return operandStack.peek();
	}

	/**
	 * This method is called when the evaluator encounters an operand. It
	 * manipulates operatorStack and/or operandStack to process the operand
	 * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
	 * @param operand the operand token that was encountered
	 */
	void handleOperand(double operand) {
		operandStack.push(operand);
	}

	/**
	 * This method is called when the evaluator encounters a name. This is a
	 * special kind of operand, really. See the project description.
	 * @param name the lowercase name that was encountered
	 */
	void handleName(String name) {
		if(name.equals("pi"))
			handleOperand(Math.PI);
		else if(name.equals("e"))
			handleOperand(Math.E);
		else
			throw new ExpressionError("Unknown name "+name);
	}

	/**
	 * This method is called when the evaluator encounters an operator. It
	 * manipulates operatorStack and/or operandStack to process the operator
	 * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
	 * @param operator the operator token that was encountered
	 */
	void handleOperator(char operator) {
		if(operatorStack.isEmpty())
			operatorStack.push(operator);
		else if(isGreaterPrecedence(operator,operatorStack.peek()))
			operatorStack.push(operator);
		else{
			operandStack.push(eval());
			operatorStack.push(operator);
		}
	}

	/**
	 * This method is called when the evaluator encounters an open bracket. It
	 * manipulates operatorStack and/or operandStack to process the open bracket
	 * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
	 * @param openBracket the open bracket token that was encountered
	 */
	void handleOpenBracket(char openBracket) {
		operatorStack.push(openBracket);
	}

	/**
	 * This method is called when the evaluator encounters a close bracket. It
	 * manipulates operatorStack and/or operandStack to process the close
	 * bracket according to the Infix-to-Postfix and Postfix-evaluation
	 * algorithms.
	 * @param closeBracket the close bracket token that was encountered
	 */
	void handleCloseBracket(char closeBracket) {
		char openBracket;
		char otherBracket1;
		char otherBracket2;

		if(closeBracket == ")".charAt(0)){
			openBracket = "(".charAt(0);
			otherBracket1 = "{".charAt(0);
			otherBracket2 = "[".charAt(0);
		}
		else if(closeBracket == "]".charAt(0)){
			openBracket = "[".charAt(0);
			otherBracket1 = "{".charAt(0);
			otherBracket2 = "(".charAt(0);
		}
		else{
			openBracket = "{".charAt(0);
			otherBracket1 = "(".charAt(0);
			otherBracket2 = "[".charAt(0);
		}
		while(true){
			if(operatorStack.peek() == openBracket){
				operatorStack.pop();
				break;
			}
			else if(operatorStack.peek() == otherBracket1 || operatorStack.peek() == otherBracket2){
				throw new ExpressionError("Bracket Mismatch");
			}
			else
				operandStack.push(eval());
		}
	}

	/**
	 * This method is called when the evaluator encounters the end of an
	 * expression. It manipulates operatorStack and/or operandStack to process
	 * the operators that remain on the stack, according to the Infix-to-Postfix
	 * and Postfix-evaluation algorithms.
	 */
	void handleRemainingOperators() {
		while(!operatorStack.isEmpty()){
			operandStack.push(eval());
		}
	}

	/**
	 * This method is called when the evaluator encounters an operator. It
	 * compares the precedence of the operator in the expression to the operator
	 * on top of the operatorStack and returns true when the operator in the
	 * expression has greater precedence.
	 * @param expressionOperator the operator in the expression to be compared
	 * @param stackOperator the operator in the stack to be compared
	 */
	boolean isGreaterPrecedence(char expressionOperator, char stackOperator){
		if(assignPrecedence(expressionOperator) > assignPrecedence(stackOperator))
			return true;
		else
			return false;
	}

	/**
	 * This methos is called when isGreaterPrecedence needs to evaluate
	 * the precedence of two operators. It assigns a precedence to the operator.
	 * @param item the operator to be assigned a assignPrecedence
	 */
	int assignPrecedence(char item){
		if(item == "^".charAt(0))
			return 3;
		else if(item == "*".charAt(0) || item == "/".charAt(0))
			return 2;
		else if(item == "+".charAt(0) || item == "-".charAt(0))
			return 1;
		else
			return -1;
	}

	/**
	 * evaluates an expression
	 * @param a the double on right side of Expression
	 * @param b the double on the left side of the expression
	 */
	double eval(){
		if(operandStack.isEmpty()){
			throw new ExpressionError("There was an error in the expression; it does not contain any numbers.");
		}
		double a = operandStack.pop();
		if(operandStack.isEmpty()){
			throw new ExpressionError("There was an error in the expression; there are not enough terms.");
		}
		double b = operandStack.pop();
		if(operatorStack.isEmpty()){
			throw new ExpressionError("There was an error in the expression; there is an unbalance in operators.");
		}
		char operation = operatorStack.pop();

		if(operation == "+".charAt(0))
			return b+a;
		else if(operation == "-".charAt(0))
			return b-a;
		else if(operation == "*".charAt(0))
			return b*a;
		else if(operation == "/".charAt(0))
			return b/a;
		else if(operation == "^".charAt(0))
			return Math.pow(b, a);
		else
			return 0;
	}

	/**
	 * Creates an InfixExpressionEvaluator object to read from System.in, then
	 * evaluates its input and prints the result.
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("Infix expression:");
		InfixExpressionEvaluator evaluator =
						new InfixExpressionEvaluator(System.in);
		Double value = null;
		try {
			value = evaluator.evaluate();
		} catch (ExpressionError e) {
			System.out.println("ExpressionError: " + e.getMessage());
		}
		if (value != null) {
			System.out.println(value);
		} else {
			System.out.println("Evaluator returned null");
		}
	}

}
