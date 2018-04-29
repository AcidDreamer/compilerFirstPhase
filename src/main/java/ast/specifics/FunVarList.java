/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;

public class FunVarList extends ASTNode {
    private List<VariableDefinition> variableDefinition;
    private List<FunctionDefinition> functionDefinition;
    public FunVarList( List<VariableDefinition>  variableDefinition,List<FunctionDefinition> functionDefinition) {
        this.variableDefinition = variableDefinition;
        this.functionDefinition = functionDefinition;
    }
    public FunVarList( ) {
        this.variableDefinition = new ArrayList<VariableDefinition>();
        this.functionDefinition = new ArrayList<FunctionDefinition>();
    }

    public  List<VariableDefinition>  getVariableDefinition() {
        return variableDefinition;
    }

    public void setVariableDefinition( List<VariableDefinition>  variableDefinition) {
        this.variableDefinition = variableDefinition;
    }

    public List<FunctionDefinition> getFunctionDefinition() {
        return functionDefinition;
    }

    public void setFunctionDefinition(List<FunctionDefinition> functionDefinition) {
        this.functionDefinition = functionDefinition;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
