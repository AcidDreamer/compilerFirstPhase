/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.Type;

public class ParameterDeclaration extends ASTNode {
    private TypeSpecifierExpression typeSpecifier;
    private String identifier;

    public ParameterDeclaration(TypeSpecifierExpression typeSpecifier,String identifier ) {
        this.identifier = identifier;
        this.typeSpecifier = typeSpecifier;
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


    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }


}
