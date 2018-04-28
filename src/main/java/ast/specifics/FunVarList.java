/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;

public class FunVarList extends ASTNode {
    private FunVarList funVarList;
    private VariableDefinition variableDefinition;
    private FunctionDefinition functionDefinition;
    public FunVarList(FunVarList funVarList,VariableDefinition variableDefinition,FunctionDefinition functionDefinition) {
        this.funVarList = funVarList;
        this.variableDefinition = variableDefinition;
        this.functionDefinition = functionDefinition;
    }

    public FunVarList getFunVarList() {
        return funVarList;
    }

    public void setFunVarList(FunVarList funVarList) {
        this.funVarList = funVarList;
    }
    
    public VariableDefinition getVariableDefinition() {
        return variableDefinition;
    }

    public void setVariableDefinition(VariableDefinition variableDefinition) {
        this.variableDefinition = variableDefinition;
    }


    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
