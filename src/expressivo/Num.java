package expressivo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An immutable numeric constant in an arithmetic {@link Expression}.
 * 
 * <p>Rep invariant:
 * <ul>
 *   <li>value != null</li>
 *   <li>value has no unnecessary trailing zeros
 *       (i.e. is in canonical form: {@code value.equals(value.stripTrailingZeros())})</li>
 * </ul>
 * 
 * <p>Abstraction function:
 * AF(this) = the numeric constant represented by {@code value}.
 * 
 * <p>Safety from rep exposure:
 * {@code value} is private and final, and never shared with or exposed to clients.
 */
public final class Num implements Expression {

    private final BigDecimal value;

    /**
     * Create a numeric constant.
     * 
     * @param value numeric value, not null
     */
    public Num(BigDecimal value) {
        this.value = value.stripTrailingZeros();
        checkRep();
    }

    private void checkRep() {
        assert value != null;
        assert value.equals(value.stripTrailingZeros());
    }

    /**
     * @return the numeric value of this constant
     */
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        // canonical decimal representation with no unnecessary trailing zeros
        return value.toPlainString();
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Num)) {
            return false;
        }
        Num that = (Num) thatObject;
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        // recursive only in the sense that BigDecimal.hashCode depends on its value;
        // does not use instanceof, as required.
        return Objects.hash("num", value);
    }
}


