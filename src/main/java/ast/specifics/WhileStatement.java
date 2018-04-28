/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

public class WhileStatement extends Statement {

    private Expression expression;
    private CurlyStatement statement;

    public WhileStatement( Expression expression , CurlyStatement statement) {
        this.statement = statement;
        this.expression = expression;
    }

    public CurlyStatement getStatement() {
        return statement;
    }

    public void setStatement(CurlyStatement statement) {
        this.statement = statement;
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
