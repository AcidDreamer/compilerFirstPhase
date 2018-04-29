/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;


public class VariableDefinition extends ASTNode {
    private TypeSpecifierExpression typeSpecifier;
    private String identifier;
    private Boolean  isTable ;
    public VariableDefinition(TypeSpecifierExpression typeSpecifier,String identifier ) {
        this.identifier = identifier;
        this.typeSpecifier = typeSpecifier;
        this.isTable = false;
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


    public Boolean isTable() {
        return isTable;
    }

    public void setIsTable(Boolean isTable) {
        this.isTable = isTable;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
