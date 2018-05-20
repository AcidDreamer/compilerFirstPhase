package ast.visitors;

/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
import java.util.ArrayDeque;
import java.util.Deque;

import symbol.*;

import ast.interfaces.*;
import ast.specifics.*;
import symbol.SymTable;


/**
 * Build symbol tables for each node of the AST.
 */
public class SymTableBuilderASTVisitor implements ASTVisitor {

    private final Deque<SymTable<SymTableEntry>> env;

    public SymTableBuilderASTVisitor() {
        env = new ArrayDeque<SymTable<SymTableEntry>>();
    }

    @Override
    public void visit(CompUnit node) throws ASTVisitorException {
        pushEnvironment();
        ASTUtils.setEnv(node, env.element());
        node.getFunVarList().accept(this);
        popEnvironment();
    }

    @Override
    public void visit(AssignmentStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(PrintStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(BinaryExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression1().accept(this);
        node.getExpression2().accept(this);
    }

    @Override
    public void visit(UnaryExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
    }

  
    @Override
    public void visit(IdentifierExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    @Override
    public void visit(FloatLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    @Override
    public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    @Override
    public void visit(StringLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    @Override
    public void visit(ParenthesisExpression node) throws ASTVisitorException {
        pushEnvironment();
        ASTUtils.setEnv(node, env.element());
        for (Expression s : node.getExpressions()) {
            s.accept(this);
        }
        popEnvironment();
    }

    @Override
    public void visit(WhileStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

    @Override
    public void visit(DoStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

    @Override
    public void visit(CompoundStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getStatement().accept(this);
        
    }


    @Override
    public void visit(IfThenElseStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
        node.getStatement1().accept(this);
        node.getStatement2().accept(this);
    }

    @Override
    public void visit(IfStatement node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

    private void pushEnvironment() {
        SymTable<SymTableEntry> oldSymTable = env.peek();
        SymTable<SymTableEntry> symTable = new HashSymTable<SymTableEntry>(
                oldSymTable);
        env.push(symTable);
    }

    private void popEnvironment() {
        env.pop();
    }
   
    @Override
    public void visit(FunVarList node) throws ASTVisitorException {
         ASTUtils.setEnv(node, env.element());
         
    }

    @Override
    public void visit(FunctionDefinition node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        for (Statement s : node.getStatementList()) {
            s.accept(this);
        }
        node.getTypeSpecifier().accept(this);
        
    }

    @Override
    public void visit(VariableDefinition node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getTypeSpecifier().accept(this);
    }


    @Override
    public void visit(TypeSpecifierExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    @Override
    public void visit(CurlyStatement node) throws ASTVisitorException {
        pushEnvironment();
        ASTUtils.setEnv(node, env.element());
        for (Statement s : node.getStatements()) {
            s.accept(this);
        }
        popEnvironment();
    }

    @Override
    public void visit(ParameterDeclaration node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getTypeSpecifier().accept(this);
    }

    @Override
    public void visit(BracketExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(KeywordLiteral node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    @Override
    public void visit(KeywordExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(NewArraySpecifier node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
        node.getIdentifier().accept(this);
    }

    @Override
    public void visit(CharacterLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setEnv(node, env.element());
    }

    

}