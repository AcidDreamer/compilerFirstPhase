/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.interfaces;

import org.apache.commons.lang3.StringEscapeUtils;

import ast.specifics.*;

public class PrintASTVisitor implements ASTVisitor {

    @Override
    public void visit(CompUnit node) throws ASTVisitorException {
        node.getFunVarList().accept(this);
    }

    @Override
    public void visit(AssignmentStatement node) throws ASTVisitorException {
        System.out.print(node.getIdentifier());
        System.out.print(" = ");
        node.getExpression().accept(this);
        System.out.println(";");
    }

    @Override
    public void visit(PrintStatement node) throws ASTVisitorException {
        System.out.print("print( ");
        node.getExpression().accept(this);
        System.out.println(" );");
    }

    @Override
    public void visit(BinaryExpression node) throws ASTVisitorException {
        node.getExpression1().accept(this);
        System.out.print(" ");
        System.out.print(node.getOperator());
        System.out.print(" ");
        node.getExpression2().accept(this);
    }

    @Override
    public void visit(UnaryExpression node) throws ASTVisitorException {
        System.out.print(node.getOperator());
        System.out.print(" ");
        node.getExpression().accept(this);
    }

    @Override
    public void visit(IdentifierExpression node) throws ASTVisitorException {
        System.out.print(node.getIdentifier());
    }

    @Override
    public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
        System.out.print(node.getLiteral());
    }
    
    @Override
    public void visit(StringLiteralExpression node) throws ASTVisitorException {
        System.out.print("\"");
        System.out.print(StringEscapeUtils.escapeJava(node.getLiteral()));
        System.out.print("\"");
    }

    @Override
    public void visit(ParenthesisExpression node) throws ASTVisitorException {
        System.out.print("( ");
        if (node.getExpressions().size() == 0 || node.getExpressions().size() == 1 ){
            for ( Expression e : node.getExpressions()){ e.accept(this);}
        }else{
            for (Expression e : node.getExpressions()){
                e.accept(this);
                System.out.print(",");
            }
        }
        System.out.print(" )");
    }

    @Override
    public void visit(CompoundStatement node) throws ASTVisitorException {
        System.out.println(" { ");
        node.getStatement().accept(this);
        System.out.println(" } ");
    }

    @Override
    public void visit(WhileStatement node ) throws ASTVisitorException{
        System.out.print("while(");
        node.getExpression().accept(this);
        System.out.print(")\n");
        node.getStatement().accept(this);
        System.out.println("");
    }

    @Override
    public void visit(DoStatement node ) throws ASTVisitorException{
        System.out.println("do");
        node.getStatement().accept(this);
        System.out.print("while(");
        node.getExpression().accept(this);
        System.out.println(");");
    }

    @Override
    public void visit(IfStatement node) throws ASTVisitorException{
        System.out.print("if(");
        node.getExpression().accept(this);
        System.out.print(")\n");
        node.getStatement().accept(this);
        System.out.println("");
    }

    @Override
    public void visit(IfThenElseStatement node) throws ASTVisitorException{
        System.out.print("if(");
        node.getExpression().accept(this);
        System.out.print(")\n");
        node.getStatement1().accept(this);
        System.out.println("else");
        node.getStatement2().accept(this);
        System.out.println("");
    }

    @Override
    public void visit(FunVarList node) throws ASTVisitorException{
        for (VariableDefinition s : node.getVariableDefinition()){
            s.accept(this);
        }
        System.out.println("\n\n");
        for (FunctionDefinition s : node.getFunctionDefinition()){
            s.accept(this);
        }
    }
    
    @Override
    public void visit(FunctionDefinition node) throws ASTVisitorException{
        node.getTypeSpecifier().accept(this);
        System.out.print(" " + node.getIdentifier());
        for (ParameterDeclaration s : node.getParameterList()){
            s.accept(this);
        }
        System.out.println("{");
        for (Statement s : node.getStatementList()){
            s.accept(this);
        }
        System.out.println("}");

    }
    
    @Override
    public void visit(VariableDefinition node) throws ASTVisitorException{
        node.getTypeSpecifier().accept(this);
        if (node.isTable())
            System.out.println("[] " + node.getIdentifier() + " ;");
        else    
            System.out.println(" " + node.getIdentifier() + " ;");
    }

    @Override
    public void visit(FloatLiteralExpression node) throws ASTVisitorException{
        System.out.println("");
    }
    
    @Override
    public void visit(TypeSpecifierExpression node) throws ASTVisitorException{
        System.out.println("");
    }

    @Override
    public void visit(CurlyStatement node) throws ASTVisitorException{
        System.out.println("");
    }

    @Override
    public void visit(ParameterDeclaration node) throws ASTVisitorException{
        System.out.println("");
    }

    @Override
    public void visit(BracketExpression node) throws ASTVisitorException{
        System.out.println("");
    }
    
    @Override
    public void visit(KeywordLiteral node) throws ASTVisitorException{
        System.out.println("");
    }

    @Override
    public void visit(KeywordExpression node) throws ASTVisitorException{
        System.out.println("");
    }

    @Override
    public void visit(CharacterLiteralExpression node) throws ASTVisitorException{
        System.out.println("");
    }
    @Override
    public void visit(NewArraySpecifier node) throws ASTVisitorException{
        System.out.println("");
    }

}
