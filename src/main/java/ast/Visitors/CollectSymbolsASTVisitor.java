/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.visitors;

import ast.*;
/**
 * Collect all symbols such as variables, methods, etc in symbol table.
 */
public class CollectSymbolsASTVisitor  {

    // public CollectSymbolsASTVisitor() {
    // }

    // @Override
    // public void visit(CompUnit node) throws ASTVisitorException {
    //     for (Statement s : node.getStatements()) {
    //         s.accept(this);
    //     }
    // }

    // @Override
    // public void visit(AssignmentStatement node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    // }

    // @Override
    // public void visit(PrintStatement node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    // }

    // @Override
    // public void visit(CompoundStatement node) throws ASTVisitorException {
    //     for (Statement st : node.getStatements()) {
    //         st.accept(this);
    //     }
    // }

    // @Override
    // public void visit(BinaryExpression node) throws ASTVisitorException {
    //     node.getExpression1().accept(this);
    //     node.getExpression2().accept(this);
    // }

    // @Override
    // public void visit(UnaryExpression node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    // }

    // @Override
    // public void visit(IdentifierExpression node) throws ASTVisitorException {
    //     // nothing
    // }

    // @Override
    // public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
    //     // nothing        
    // }

    // @Override
    // public void visit(DoubleLiteralExpression node) throws ASTVisitorException {
    //     // nothing
    // }

    // @Override
    // public void visit(StringLiteralExpression node) throws ASTVisitorException {
    //     // nothing
    // }

    // @Override
    // public void visit(ParenthesisExpression node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    // }

    // @Override
    // public void visit(DoWhileStatement node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    //     node.getStatement().accept(this);
    // }

    // @Override
    // public void visit(WhileStatement node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    //     node.getStatement().accept(this);
    // }

    // @Override
    // public void visit(VarDeclarationStatement node) throws ASTVisitorException {
    //     SymTable<SymTableEntry> env = ASTUtils.getEnv(node);
    //     SymTableEntry entry = env.lookupOnlyInTop(node.getIdentifier());
    //     if ( entry != null ){
    //         ASTUtils.error(node, "ERROR : Variable " + node.getIdentifier() + " has been redeclared");
    //     }else{
    //         env.put(node.getIdentifier(), new SymTableEntry(node.getIdentifier(),node.getType() ) );
    //     }
    

    // }

    // @Override
    // public void visit(BreakStatement node) throws ASTVisitorException {
    //     // nothing
    // }

    // @Override
    // public void visit(ContinueStatement node) throws ASTVisitorException {
    //     // nothing
    // }

    // @Override
    // public void visit(IfElseStatement node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    //     node.getStatement1().accept(this);
    //     node.getStatement2().accept(this);
    // }

    // @Override
    // public void visit(IfStatement node) throws ASTVisitorException {
    //     node.getExpression().accept(this);
    //     node.getStatement().accept(this);
    // }

}
