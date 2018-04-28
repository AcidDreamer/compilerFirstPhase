/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;

public class VariableDefinition extends ASTNode {

    private FunVarList funVarList;

    public VariableDefinition(FunVarList funVarList) {
        this.funVarList = funVarList;
    }

    public VariableDefinition(List<FunVarList> statements) {
        this.funVarList = statements;
    }

    public List<FunVarList> getStatements() {
        return funVarList;
    }

    public void setStatements(List<FunVarList> statements) {
        this.funVarList = statements;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
