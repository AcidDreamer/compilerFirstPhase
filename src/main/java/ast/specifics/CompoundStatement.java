/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class CompoundStatement extends Statement {

    private Statement statement;

    public CompoundStatement(Statement statement) {
        this.statement = statement;
    }

    public CompoundStatement(){
        statement = null;
    }
    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
