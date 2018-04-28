/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

public class KeywordExpression extends Expression {

    private int arraysSize;
    private TypeSpecifierExpression identifier;

    public KeywordExpression(TypeSpecifierExpression identifier,int arraysSize) {
        this.arraysSize = arraysSize;
        this.identifier = identifier;
    }

    
    public TypeSpecifierExpression getIdentifier() {
        return identifier;
    }

    public void setIdentifier(TypeSpecifierExpression identifier) {
        this.identifier = identifier;
    }


    public int getExpression() {
        return arraysSize;
    }

    public void setExpression(int arraysSize) {
        this.arraysSize = arraysSize;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
