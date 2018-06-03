/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import ast.interfaces.*;

import org.objectweb.asm.Type;

public class TypeSpecifierExpression extends Expression {

    private String typeSpecifier;
    private Type type;

    public TypeSpecifierExpression(String typeSpecifier) {
        this.typeSpecifier = typeSpecifier;
        System.out.println("TypeSpecifierExpression Reached here with " + typeSpecifier);
        if (typeSpecifier == "char"){
            type = Type.CHAR_TYPE;
        }else if (typeSpecifier == "float"){
            type = Type.FLOAT_TYPE;
        }else if (typeSpecifier == "int"){
            type = Type.INT_TYPE;
        }else if (typeSpecifier == "void"){
            type = Type.VOID_TYPE;
        }else if (typeSpecifier == "String"){
            type = Type.getType(String.class);
        }else{
        
        }

    }

    public Type getType(){
        return type;
    }
    public String getTypeSpecifier() {
        return typeSpecifier;
    }

    public void setTypeSpecifier(String typeSpecifier) {
        this.typeSpecifier = typeSpecifier;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

    @Override 
    public String toString(){
        return "TypeSpecifierExpression with type : " + type;
    }
}
