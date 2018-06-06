/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.visitors;

import symbol.*;

import ast.interfaces.*;
import ast.specifics.*;
import symbol.SymTable;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Collect all symbols such as variables, methods, etc in symbol table.
 */
public class CollectSymbolsASTVisitor implements ASTVisitor  {

    public CollectSymbolsASTVisitor() {
    }

    @Override
    public void visit(CompUnit node) throws ASTVisitorException {
        node.getFunVarList().accept(this);
    }
    @Override
    public void visit(FunVarList node) throws ASTVisitorException{
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        for (VariableDefinition s : node.getVariableDefinition()){
            s.accept(this);
        }
        for (FunctionDefinition s : node.getFunctionDefinition()){
            s.accept(this);
        }
    }
    @Override
    public void visit(FunctionDefinition node) throws ASTVisitorException{
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        SymTableEntry entry = env.lookupOnlyInTop(node.getIdentifier());
        if ( entry != null ){
            ASTUtils.error(node, "ERROR : Function " + node.getIdentifier() + " has been redeclared");
        }else{
            System.out.println("Function Definition : " + node.getIdentifier() + " Node : " + node.getTypeSpecifier().toString());
            env.put(node.getIdentifier(), new SymTableEntry(node.getIdentifier(),node.getTypeSpecifier().getType()) );
        }

        if (node.getParameterList() != null ){
            for (ParameterDeclaration s : node.getParameterList()){
                s.accept(this);
            }    
        }
        if (node.getStatementList() != null ){
            for (Statement s : node.getStatementList()){
                s.accept(this);
            }
        }
    }
    @Override
    public void visit(ParameterDeclaration node) throws ASTVisitorException{
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        SymTableEntry entry = env.lookupOnlyInTop(node.getIdentifier());
        if ( entry != null ){
            ASTUtils.error(node, "ERROR : Variable " + node.getIdentifier() + " has been redeclared");
        }else{
            env.put(node.getIdentifier(), new SymTableEntry(node.getIdentifier(),node.getTypeSpecifier().getType()) );
        }

    }
    @Override
    public void visit(FunctionExpression node) throws ASTVisitorException{
        //Nothing
    }    
    @Override
    public void visit(AssignmentStatement node) throws ASTVisitorException {
        if (node.getIsTable())
            node.getTablePosition().accept(this);
        node.getExpression().accept(this);
    }

    @Override
    public void visit(PrintStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(BinaryExpression node) throws ASTVisitorException {
        node.getExpression1().accept(this);
        node.getExpression2().accept(this);
    }

    @Override
    public void visit(UnaryExpression node) throws ASTVisitorException {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(IdentifierExpression node) throws ASTVisitorException {
        //nothing
    }

    @Override
    public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
        //nothing
    }
    
    @Override
    public void visit(StringLiteralExpression node) throws ASTVisitorException {
        //nothing
    }

    @Override
    public void visit(ParenthesisExpression node) throws ASTVisitorException {
        for (Expression e : node.getExpressions()){
            e.accept(this);
        }
    }

    @Override
    public void visit(CompoundStatement node) throws ASTVisitorException {
        node.getStatement().accept(this);
    }

    @Override
    public void visit(WhileStatement node ) throws ASTVisitorException{
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

    @Override
    public void visit(DoStatement node ) throws ASTVisitorException{
        node.getStatement().accept(this);
        node.getExpression().accept(this);
    }

    @Override
    public void visit(IfStatement node) throws ASTVisitorException{
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

    @Override
    public void visit(IfThenElseStatement node) throws ASTVisitorException{
        node.getExpression().accept(this);
        node.getStatement1().accept(this);
        node.getStatement2().accept(this);
    }

    @Override
    public void visit(VariableDefinition node) throws ASTVisitorException{
        SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
        SymTableEntry entry = env.lookupOnlyInTop(node.getIdentifier());
        if ( entry != null ){
            ASTUtils.error(node, "ERROR : Variable " + node.getIdentifier() + " has been redeclared");
        }else{
            env.put(node.getIdentifier(), new SymTableEntry(node.getIdentifier(),node.getTypeSpecifier().getType() ) );
        }

    }

    @Override
    public void visit(FloatLiteralExpression node) throws ASTVisitorException{
        //Nothing
    }
    
    @Override
    public void visit(TypeSpecifierExpression node) throws ASTVisitorException{
        //Nothing
    }

    @Override
    public void visit(CurlyStatement node) throws ASTVisitorException{
        for (Statement s : node.getStatements()){
            s.accept(this);
        }
    }

    @Override
    public void visit(BracketExpression node) throws ASTVisitorException{
        node.getExpression().accept(this);
    }
    
    @Override
    public void visit(KeywordLiteral node) throws ASTVisitorException{
        //Nothing
    }

    @Override
    public void visit(KeywordExpression node) throws ASTVisitorException{
        if (node.getExpression() != null)
            node.getExpression().accept(this);
    }

    @Override
    public void visit(CharacterLiteralExpression node) throws ASTVisitorException{
        //Nothing
    }
    @Override
    public void visit(NewArraySpecifier node) throws ASTVisitorException{
        node.getIdentifier().accept(this);
    }

    @Override
    public void visit(BreakStatement node) {
       //nothing
    }

    @Override
    public void visit(ContinueStatement node) {
        //nothing
}
}
