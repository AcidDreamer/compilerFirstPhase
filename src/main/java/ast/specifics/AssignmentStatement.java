/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

public class AssignmentStatement extends Statement {

    private String identifier;
    private Expression expression;
    private Boolean isTable;
    private Expression tablePosition;

    public AssignmentStatement(String identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
        this.isTable = false;
        this.tablePosition = null;
    }
    public AssignmentStatement(String identifier, Expression expression,Expression tablePosition) {
        this.identifier = identifier;
        this.expression = expression;
        this.isTable = true;
        this.tablePosition = tablePosition;
    }


    public boolean getIsTable() {
        return isTable;
    }

    public void setIsTable(Boolean isTable) {
        this.isTable = isTable;
    }

    public Expression getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(Expression tablePosition) {
        this.tablePosition = tablePosition;
    }




    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
