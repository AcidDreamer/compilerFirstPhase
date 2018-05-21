/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;


public class FunctionDefinition extends ASTNode {
    private TypeSpecifierExpression typeSpecifier;
    private String identifier;
    private List<Statement> statements;
    private List<ParameterDeclaration> parameters;

    public FunctionDefinition(TypeSpecifierExpression typeSpecifier,String identifier,List<Statement> statements,List<ParameterDeclaration> parameters) {
        this.typeSpecifier = typeSpecifier;
        this.identifier = identifier;
        this.statements = statements;
        this.parameters = parameters;
        
    }


    public TypeSpecifierExpression getTypeSpecifier() {
        return typeSpecifier;
    }

    public void setTypeSpecifier(TypeSpecifierExpression typeSpecifier) {
        this.typeSpecifier = typeSpecifier;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Statement> getStatementList() {
        return statements;
    }

    public void setStatementList(List<Statement> statements) {
        this.statements = statements;
    }
    
    public List<ParameterDeclaration> getParameterList() {
        return parameters;
    }

    public void setParameterList(List<ParameterDeclaration> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
