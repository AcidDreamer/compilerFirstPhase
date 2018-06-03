/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;
import java.util.ArrayList;

import org.objectweb.asm.Type;

import ast.interfaces.*;

public class FunctionExpression extends Expression {

    private String identifier;
    private ArrayList<Expression> parameters;
    private Type type; 

    public FunctionExpression(String identifier,ArrayList<Expression> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
        if (this.parameters == null)
            parameters = new ArrayList<Expression>();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public ArrayList<Expression> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Expression> parameters) {
        this.parameters = parameters;
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
