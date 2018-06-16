// package ast.visitors;

// /**
//  * This code is part of the lab exercises for the Compilers course at Harokopio
//  * University of Athens, Dept. of Informatics and Telematics.
//  */
// import java.util.ArrayDeque;
// import java.util.Deque;

// import ast.interfaces.*;
// import ast.specifics.*;
// import ch.qos.logback.core.joran.conditional.ElseAction;
// import symbol.SymTable;
// /**
//  * Build LocalIndexPool for each node of the AST.
//  */
// public class LocalIndexBuilderASTVisitor implements ASTVisitor {

//     private final Deque<LocalIndexPool> env;

//     public LocalIndexBuilderASTVisitor() {
//         env = new ArrayDeque<LocalIndexPool>();
//     }

//     @Override
//     public void visit(CompUnit node) throws ASTVisitorException {
//         env.push(new LocalIndexPool()); //push/pop ανά compound statement
//         ASTUtils.setLocalIndexPool(node, env.element());
//         for (Statement s : node.getStatements()) {
//             s.accept(this);
//         }
//         env.pop();
//     }

//     @Override
//     public void visit(AssignmentStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//     }

//     @Override
//     public void visit(PrintStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//     }

//     @Override
//     public void visit(BinaryExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression1().accept(this);
//         node.getExpression2().accept(this);
//     }

//     @Override
//     public void visit(UnaryExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//     }

//     @Override
//     public void visit(VarDeclarationStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(IdentifierExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(DoubleLiteralExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(StringLiteralExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(ParenthesisExpression node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//     }

//     @Override
//     public void visit(WhileStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//         node.getStatement().accept(this);
//     }

//     @Override
//     public void visit(DoWhileStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//         node.getStatement().accept(this);
//     }

//     @Override
//     public void visit(CompoundStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         for (Statement s : node.getStatements()) {
//             s.accept(this);
//         }
//     }

//     @Override
//     public void visit(BreakStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(ContinueStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//     }

//     @Override
//     public void visit(IfElseStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//         node.getStatement1().accept(this);
//         node.getStatement2().accept(this);
//     }

//     @Override
//     public void visit(IfStatement node) throws ASTVisitorException {
//         ASTUtils.setLocalIndexPool(node, env.element());
//         node.getExpression().accept(this);
//         node.getStatement().accept(this);
//     }

// }
