package expressivo;

import java.util.Objects;

/**
 * An immutable variable in an arithmetic {@link Expression}.
 * 
 * <p>Variables are case-sensitive, nonempty strings of letters.
 * 
 * <p>Rep invariant:
 * <ul>
 *   <li>name != null</li>
 *   <li>name is nonempty</li>
 *   <li>name matches {@code [A-Za-z]+}</li>
 * </ul>
 * 
 * <p>Abstraction function:
 * AF(this) = the variable with the given {@code name}.
 * 
 * <p>Safety from rep exposure:
 * {@code name} is private and final and never exposed directly to clients.
 */
public final class Variable implements Expression {

    private final String name;

    /**
     * Create a variable.
     * 
     * @param name variable name, case-sensitive, nonempty string of letters
     */
    public Variable(String name) {
        this.name = name;
        checkRep();
    }

    private void checkRep() {
        assert name != null;
        assert !name.isEmpty();
        assert name.matches("[A-Za-z]+");
    }

    /**
     * @return this variable's name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Variable)) {
            return false;
        }
        Variable that = (Variable) thatObject;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash("var", name);
    }
}


