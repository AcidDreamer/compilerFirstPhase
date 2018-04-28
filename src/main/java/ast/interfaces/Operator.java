/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.interfaces;
import ast.specifics.*;

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

}
