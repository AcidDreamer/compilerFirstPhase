/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

public class IfThenElseStatement extends Statement {

    private Expression expression;
    private Statement statement1;
    private Statement statement2;

    public IfThenElseStatement( Expression expression , Statement statement1,Statement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.expression = expression;
    }

    public Statement getStatement1() {
        return statement1;
    }

    public void setStatement1(Statement statement) {
        this.statement1 = statement;
    }
    public Statement getStatement2() {
        return statement2;
    }

    public void setStatement2(Statement statement) {
        this.statement2 = statement;
    }
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
