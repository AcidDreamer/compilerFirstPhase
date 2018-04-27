/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.interfaces;
import ast.interfaces.*;

public enum Operator {

    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    EQUAL("=="),
    NOT_EQUAL("!="),
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
