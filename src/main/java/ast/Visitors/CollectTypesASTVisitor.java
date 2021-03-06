/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */

package ast.visitors;

import ast.interfaces.*;
import ast.specifics.*;
import types.TypeException;
import org.objectweb.asm.Type;
import symbol.SymTable;
import symbol.SymTableEntry;
import types.TypeUtils;


/**
 * Compute possible types for each node.
 */
public class CollectTypesASTVisitor implements ASTVisitor {

    public CollectTypesASTVisitor() {
    }

    @Override
    public void visit(CompUnit node) throws ASTVisitorException {
         node.getFunVarList().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(AssignmentStatement node) throws ASTVisitorException {
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        SymTableEntry entry = env.lookup(node.getIdentifier());
        if (entry == null) {
            ASTUtils.error(node, "Variable has not been declared");
        }

        node.getExpression().accept(this);
        Type type = ASTUtils.getSafeType(node.getExpression());
        if (TypeUtils.isAssignable(entry.getType(), type)){
            ASTUtils.setType(node, Type.VOID_TYPE);
        }else if(TypeUtils.isCasted(entry.getType(), type)){
            ASTUtils.setType(node, Type.VOID_TYPE);
        }else{
            ASTUtils.error(node, "Type missmatch on line: " + node.getLine());
        }        
        if ( node.getIsTable() ){
            node.getTablePosition().accept(this);
            Type typeTablePos = ASTUtils.getSafeType(node.getTablePosition());
            if ( typeTablePos != Type.INT_TYPE){
                ASTUtils.error(node, "Position should be dictated by int on line: " + node.getLine());
            }
        }
    }

    @Override
    public void visit(PrintStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(CompoundStatement node) throws ASTVisitorException {
         node.getStatement().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(BinaryExpression node) throws ASTVisitorException {
        node.getExpression1().accept(this);
        node.getExpression2().accept(this);
        try {
            ASTUtils.setType(node, 
                TypeUtils.applyBinary(node.getOperator(),
                 ASTUtils.getSafeType(node.getExpression1()),ASTUtils.getSafeType(node.getExpression2())));
        }catch (TypeException e) {
            ASTUtils.error(node, e.getMessage());
        }
    }

    @Override
    public void visit(UnaryExpression node) throws ASTVisitorException {
        node.getExpression().accept(this);
        try {
            ASTUtils.setType(node, TypeUtils.applyUnary(node.getOperator(), ASTUtils.getSafeType(node.getExpression())));
        } catch (TypeException e) {
            ASTUtils.error(node, e.getMessage());
        }
    }

    @Override
    public void visit(IdentifierExpression node) throws ASTVisitorException {
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        SymTableEntry entry = env.lookup(node.getIdentifier());
        if (entry == null){
            ASTUtils.error(node, "Undeclared variable");
        }
        ASTUtils.setType(node, entry.getType());
    }

    @Override
    public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.INT_TYPE);
        
    }

    @Override
    public void visit(FunctionExpression node) throws ASTVisitorException {
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        SymTableEntry entry = env.lookup(node.getIdentifier());
        if (entry == null){
            ASTUtils.error(node, "Undeclared function named: " + node.getIdentifier());
        }
        ASTUtils.setType(node, entry.getType());  
        node.setType(entry.getType());      
        if (node.getType() == null)
            ASTUtils.error(node, "Parser error : Function " + node.getIdentifier() + " doesn't have a type.");
    }

    @Override
    public void visit(FloatLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.DOUBLE_TYPE);
    }

    @Override
    public void visit(StringLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.getType(String.class));
    }

    @Override
    public void visit(ParenthesisExpression node) throws ASTVisitorException {
        for (Expression s : node.getExpressions()) {
            s.accept(this);
        }
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(DoStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
        if (!ASTUtils.getSafeType(node.getExpression()).equals(Type.BOOLEAN_TYPE)) {
            ASTUtils.error(node.getExpression(), "Invalid expression, should be boolean");
        }
        node.getStatement().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
        
    }

    @Override
    public void visit(WhileStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
        if (!ASTUtils.getSafeType(node.getExpression()).equals(Type.BOOLEAN_TYPE)) {
            ASTUtils.error(node.getExpression(), "Invalid expression, should be boolean");
        }
        node.getStatement().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(IfThenElseStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
        if (!ASTUtils.getSafeType(node.getExpression()).equals(Type.BOOLEAN_TYPE)) {
            ASTUtils.error(node.getExpression(), "Invalid expression, should be boolean");
        }
        node.getStatement1().accept(this);
        node.getStatement2().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(IfStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
        if (!ASTUtils.getSafeType(node.getExpression()).equals(Type.BOOLEAN_TYPE)) {
            ASTUtils.error(node.getExpression(), "Invalid expression, should be boolean");
        }
        node.getStatement().accept(this);
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    

    @Override
    public void visit(FunVarList node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
        for ( VariableDefinition varDef : node.getVariableDefinition()){
            varDef.accept(this);
        }

        for ( FunctionDefinition funDef : node.getFunctionDefinition()){
            funDef.accept(this);
        }
    }

    @Override
    public void visit(FunctionDefinition node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
        if (node.getStatementList() != null ){
            for (Statement s : node.getStatementList()){
                s.accept(this);
            }
        }
    }

    @Override
    public void visit(VariableDefinition node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(TypeSpecifierExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, node.getType());
    }

    @Override
    public void visit(CurlyStatement node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(ParameterDeclaration node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(BracketExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(KeywordLiteral node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(KeywordExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(NewArraySpecifier node) throws ASTVisitorException {
        ASTUtils.setType(node, node.getIdentifier().getType());
    }

    @Override
    public void visit(CharacterLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setType(node, Type.CHAR_TYPE);
    }

    @Override
    public void visit(BreakStatement node) {
        ASTUtils.setType(node, Type.VOID_TYPE);
    }

    @Override
    public void visit(ContinueStatement node) {
       ASTUtils.setType(node, Type.VOID_TYPE);
    }

}
