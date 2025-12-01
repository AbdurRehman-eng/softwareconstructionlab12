/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   Partition for Expression variants:
    //     - Num: integer, decimal with trailing zeros
    //     - Variable: different names, different case
    //     - Plus / Times: simple binary, nested (different groupings)
    //
    //   For toString():
    //     - Num: "1", "1.5", "0"
    //     - Variable: "x"
    //     - Plus/Times: check fully parenthesized, no spaces, recursive form
    //
    //   For equals() / hashCode():
    //     - same object
    //     - different objects, same structure -> equal, same hash
    //     - different structure -> not equal
    //     - numbers with trailing zeros equal (1 vs 1.0 vs 1.000)
    //     - variable name case sensitivity
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // Tests for Expression

    @Test
    public void testNumToStringCanonical() {
        assertEquals("1", new Num(new java.math.BigDecimal("1.0")).toString());
        assertEquals("1.5", new Num(new java.math.BigDecimal("1.50")).toString());
    }

    @Test
    public void testVariableToString() {
        assertEquals("x", new Variable("x").toString());
    }

    @Test
    public void testPlusTimesToStringFullyParenthesized() {
        Expression x = new Variable("x");
        Expression one = new Num(new java.math.BigDecimal("1"));
        Expression y = new Variable("y");

        Expression sum = new Plus(one, x);          // (1+x)
        Expression product = new Times(sum, y);     // ((1+x)*y)

        assertEquals("(1+x)", sum.toString());
        assertEquals("((1+x)*y)", product.toString());
    }

    @Test
    public void testNumEqualityAndHashCodeWithTrailingZeros() {
        Expression n1 = new Num(new java.math.BigDecimal("1"));
        Expression n2 = new Num(new java.math.BigDecimal("1.0"));
        Expression n3 = new Num(new java.math.BigDecimal("1.000"));

        assertEquals(n1, n2);
        assertEquals(n2, n3);
        assertEquals(n1, n3);

        assertEquals(n1.hashCode(), n2.hashCode());
        assertEquals(n2.hashCode(), n3.hashCode());
    }

    @Test
    public void testVariableEqualityCaseSensitive() {
        Expression xLower = new Variable("x");
        Expression xUpper = new Variable("X");

        assertNotEquals(xLower, xUpper);
    }

    @Test
    public void testStructuralEqualityPlusTimes() {
        Expression x = new Variable("x");
        Expression one = new Num(new java.math.BigDecimal("1"));
        Expression two = new Num(new java.math.BigDecimal("2"));

        Expression xPlusOne = new Plus(x, one);
        Expression xPlusOneCopy = new Plus(new Variable("x"), new Num(new java.math.BigDecimal("1")));
        Expression onePlusX = new Plus(one, x);

        assertEquals(xPlusOne, xPlusOneCopy);
        assertEquals(xPlusOne.hashCode(), xPlusOneCopy.hashCode());

        assertNotEquals(xPlusOne, onePlusX); // order matters

        Expression nested1 = new Plus(new Plus(one, two), x);   // (1+2)+x
        Expression nested2 = new Plus(one, new Plus(two, x));   // 1+(2+x)
        assertNotEquals(nested1, nested2); // grouping matters for structural equality
    }

    @Test
    public void testEqualsDifferentVariantsNotEqual() {
        Expression n = new Num(new java.math.BigDecimal("1"));
        Expression v = new Variable("x");
        Expression p = new Plus(n, v);
        Expression t = new Times(n, v);

        assertNotEquals(n, v);
        assertNotEquals(n, p);
        assertNotEquals(v, p);
        assertNotEquals(p, t);
    }
    
}
