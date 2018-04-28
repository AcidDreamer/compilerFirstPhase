/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.specifics;

import ast.interfaces.*;
import java.util.ArrayList;
import java.util.List;

public class CompUnit extends ASTNode {

    private FunVarList funVarList;

    public CompUnit(FunVarList funVarList) {
        this.funVarList = funVarList;
    }

    public CompUnit() {

    }


    public FunVarList getFunVarList() {
        return funVarList;
    }

    public void setFunVarList(FunVarList funVarList) {
        this.funVarList = funVarList;
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

}
