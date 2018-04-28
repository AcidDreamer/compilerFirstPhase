/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;


//FunctionDefinition ::= TypeSpecifier IDENTIFIER LPAREN  RPAREN LCURLY StmtList RCURLY
//    | TypeSpecifier   IDENTIFIER LPAREN ParameterList RPAREN LCURLY StmtList RCURLY;

public class FunctionDefinition extends ASTNode {
    private TypeSpecifier typeSpecifier;
    private String identifier;
    private List<Statement> statements;
    private List<Parameter> parameters;

    public FunctionDefinition(TypeSpecifier typeSpecifier,String identifier,List<Statement> statements,List<Parameter> parameters) {
        this.typeSpecifier = typeSpecifier;
        this.identifier = identifier;
        this.statements = statements;
        this.parameters = parameters;
    }


    public TypeSpecifier getTypeSpecifier() {
        return typeSpecifier;
    }

    public void setTypeSpecifier(TypeSpecifier typeSpecifier) {
        this.typeSpecifier = typeSpecifier;
    }
    
    public String getIdentifier() {
        return typeSpecifier;
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
    
    public List<Parameter> getParameterList() {
        return parameters;
    }

    public void setParameterList(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
