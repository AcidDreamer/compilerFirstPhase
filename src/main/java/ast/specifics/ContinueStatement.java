/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.specifics;

import ast.interfaces.ASTVisitor;
import ast.interfaces.ASTVisitorException;

/**
 *
 * @author mitsos
 */
public class ContinueStatement extends Statement {

    public ContinueStatement() {
    }

    @Override
    public void accept(ASTVisitor visitor) throws ASTVisitorException {
        visitor.visit(this);
    }

   

}
