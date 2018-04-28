/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.interfaces;
import ast.specifics.*;

/**
 * Abstract syntax tree visitor.
 */
       /**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package ast.interfaces;
import ast.specifics.*;

/**
 * Abstract syntax tree visitor.
 */
public interface ASTVisitor {

    void visit(CompUnit node) throws ASTVisitorException;

    void visit(AssignmentStatement node) throws ASTVisitorException;

    void visit(PrintStatement node) throws ASTVisitorException;

    void visit(CompoundStatement node) throws ASTVisitorException;

    void visit(BinaryExpression node) throws ASTVisitorException;

    void visit(UnaryExpression node) throws ASTVisitorException;

    void visit(IdentifierExpression node) throws ASTVisitorException;

    void visit(DoubleLiteralExpression node) throws ASTVisitorException;

    void visit(IntegerLiteralExpression node) throws ASTVisitorException;

    void visit(StringLiteralExpression node) throws ASTVisitorException;

    void visit(ParenthesisExpression node) throws ASTVisitorException;

    void visit(WhileStatement node) throws ASTVisitorException;

    void visit(DoStatement node) throws ASTVisitorException;

    void visit(IfStatement node) throws ASTVisitorException;

    void visit(IfThenElseStatement node) throws ASTVisitorException;
    
    void visit(FunVarList node) throws ASTVisitorException;
    
    void visit(FunctionDefinition node) throws ASTVisitorException;
    
    void visit(VariableDefinition node) throws ASTVisitorException;
}