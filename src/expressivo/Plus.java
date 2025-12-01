package expressivo;

import java.util.Objects;

/**
 * An immutable sum of two {@link Expression}s.
 * 
 * <p>Rep invariant:
 * <ul>
 *   <li>left != null</li>
 *   <li>right != null</li>
 * </ul>
 * 
 * <p>Abstraction function:
 * AF(this) = the arithmetic expression (left + right).
 * 
 * <p>Safety from rep exposure:
 * Fields are private and final; subexpressions are themselves immutable
 * {@link Expression} objects.
 */
public final class Plus implements Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Create a sum expression.
     * 
     * @param left left operand, not null
     * @param right right operand, not null
     */
    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }

    private void checkRep() {
        assert left != null;
        assert right != null;
    }

    /**
     * @return the left operand
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * @return the right operand
     */
    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        // fully parenthesized, no whitespace
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Plus)) {
            return false;
        }
        Plus that = (Plus) thatObject;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }

    @Override
    public int hashCode() {
        // recursive via subexpression hash codes, no instanceof
        return Objects.hash("plus", left, right);
    }
}


