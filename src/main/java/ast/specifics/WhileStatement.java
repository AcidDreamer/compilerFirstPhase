/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

public class WhileStatement extends Statement {

    private Expression expression;
    private Statement statement;

    public WhileStatement( Expression expression , Statement statement) {
        this.statement = statement;
        this.expression = expression;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
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
