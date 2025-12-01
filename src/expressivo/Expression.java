/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   Expression = Num(value: BigDecimal)
    //              + Variable(name: String)
    //              + Plus(left: Expression, right: Expression)
    //              + Times(left: Expression, right: Expression)
    //
    //   All variants are:
    //   - immutable: no mutators, all fields are private and final
    //   - recursive: Plus and Times contain sub‑Expressions
    //
    //   Concrete variant classes are defined in:
    //   - expressivo.Num
    //   - expressivo.Variable
    //   - expressivo.Plus
    //   - expressivo.Times
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * @return a parsable representation of this expression.
     * 
     * The result is a String in the concrete expression syntax described
     * in the problem set handout, with the following additional guarantees:
     * 
     * <ul>
     *   <li>Numbers are written in a canonical decimal form, with no
     *       unnecessary trailing zeros (e.g. {@code 1}, {@code 1.5}).</li>
     *   <li>Variables are written as their case-sensitive name.</li>
     *   <li>Binary {@code +} and {@code *} expressions are fully
     *       parenthesized with no surrounding whitespace, e.g.
     *       {@code (x+1)} or {@code ((x+1)*(y+2))}.</li>
     * </ul>
     * 
     * The representation is observationally equivalent to this expression:
     * for all e:Expression, parsing the result (once {@link #parse} is
     * implemented) produces an expression structurally equal to e:
     * 
     * <pre>
     *   for all e: Expression,
     *       e.equals(Expression.parse(e.toString()))
     * </pre>
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods
    
}
