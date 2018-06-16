package ast.visitors;

/**
 * This code is part of the lab exercises for the Compilers course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
import java.util.ArrayDeque;
import java.util.Deque;

import ast.interfaces.*;
import ast.specifics.*;
import ch.qos.logback.core.joran.conditional.ElseAction;
import symbol.SymTable;
import symbol.LocalIndexPool;
/**
 * Build LocalIndexPool for each node of the AST.
 */
public class LocalIndexBuilderASTVisitor implements ASTVisitor {

    private final Deque<LocalIndexPool> env;

    public LocalIndexBuilderASTVisitor() {
        env = new ArrayDeque<LocalIndexPool>();
    }

    @Override
    public void visit(CompUnit node) throws ASTVisitorException {
        env.push(new LocalIndexPool()); //push/pop ανά compound statement
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getFunVarList().accept(this);
        env.pop();
    }

    @Override
    public void visit(FunVarList node) throws ASTVisitorException{
        ASTUtils.setLocalIndexPool(node, env.element());

        for (ASTNode stmt : node.getVariableDefinition()){
            stmt.accept(this);
        }
        for(ASTNode stmt : node.getFunctionDefinition()){
            stmt.accept(this);
        }
         
    }
    @Override
    public void visit(AssignmentStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(PrintStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(BinaryExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression1().accept(this);
        node.getExpression2().accept(this);
    }

    @Override
    public void visit(UnaryExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
    }

    @Override
    public void visit(VariableDefinition node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(IdentifierExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(FloatLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(StringLiteralExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(ParenthesisExpression node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        for(Expression e : node.getExpressions()){
            e.accept(this);
        }
    }

    @Override
    public void visit(WhileStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

    @Override
    public void visit(CompoundStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getStatement().accept(this);
    }

    @Override
    public void visit(BreakStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(ContinueStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
    }

    @Override
    public void visit(IfStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
        node.getStatement().accept(this);
    }

	@Override
	public void visit(DoStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
        node.getStatement().accept(this);
	}

	@Override
	public void visit(IfThenElseStatement node) throws ASTVisitorException {
        ASTUtils.setLocalIndexPool(node, env.element());
        node.getExpression().accept(this);
        node.getStatement1().accept(this);
        node.getStatement2().accept(this);
	}

	@Override
	public void visit(FunctionDefinition node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(TypeSpecifierExpression node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(CurlyStatement node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(ParameterDeclaration node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(BracketExpression node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(KeywordLiteral node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(KeywordExpression node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(NewArraySpecifier node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(CharacterLiteralExpression node) throws ASTVisitorException {
		
	}

	@Override
	public void visit(FunctionExpression node) throws ASTVisitorException {
		
	}

}
