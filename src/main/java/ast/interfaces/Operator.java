/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.interfaces;



public enum Operator {

    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    EQUAL("=="),
    NOT_EQUAL("!="),
    OR("||"),
    NOT("!"),
    MOD("%"),
    AND("&&"),
    LESS_EQ("<="),
    LESS("<"),
    GREATER(">"),
    GREATER_EQ(">=");
    
    
    private String type;

    private Operator(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
    public boolean isUnary() {
        return this.equals(Operator.MINUS);
    }

    public boolean isRelational() {
        return this.equals(Operator.EQUAL) || this.equals(Operator.NOT_EQUAL)
                || this.equals(Operator.GREATER) || this.equals(Operator.GREATER_EQ)
                || this.equals(Operator.LESS) || this.equals(Operator.LESS_EQ);
    }


}
