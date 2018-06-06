/**
 * //  * This code is part of the lab exercises for the Compilers course at Harokopio
 * //  * University of Athens, Dept. of Informatics and Telematics.
 * // */
package ast.visitors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import threeaddr.*;
import ast.interfaces.*;
import ast.visitors.*;
import symbol.*;
import ast.specifics.*;
import symbol.SymTable;

import org.apache.commons.lang3.StringEscapeUtils;

public class IntermediateCodeASTVisitor implements ASTVisitor {

    private final Program program;
    private final Deque<String> stack;
    private int temp;

    public IntermediateCodeASTVisitor() {
        program = new Program();
        stack = new ArrayDeque<String>();
        temp = 0;
    }

    private String createTemp() {
        return "t" + Integer.toString(temp++);
    }

    public Program getProgram() {
        return program;
    }

    @Override
    public void visit(CompUnit node) throws ASTVisitorException {
       /*
        Statement s = null, ps;
        Iterator<Statement> it = node.getStatements().iterator();
        while (it.hasNext()) {
            ps = s;
            s = it.next();

            if (ps != null && !ASTUtils.getNextList(ps).isEmpty()) {
                Program.backpatch(ASTUtils.getNextList(ps), program.addNewLabel());
            }

            s.accept(this);

            if (!ASTUtils.getBreakList(s).isEmpty()) {
                ASTUtils.error(s, "Break detected without a loop.");
            }

            if (!ASTUtils.getContinueList(s).isEmpty()) {
                ASTUtils.error(s, "Continue detected without a loop.");
            }
        }
        if (s != null && !ASTUtils.getNextList(s).isEmpty()) {
            Program.backpatch(ASTUtils.getNextList(s), program.addNewLabel());
        }
        */
       node.getFunVarList().accept(this);
    }

    @Override
    public void visit(AssignmentStatement node) throws ASTVisitorException {
        node.getExpression().accept(this); 
        String t = stack.pop();
        program.add(new AssignInstr(t, node.getIdentifier()));
    }

    @Override
    public void visit(PrintStatement node) throws ASTVisitorException {
        node.getExpression().accept(this);
        String tmp = stack.pop();
        program.add(new PrintInstr(tmp));
        // run recursively for expression
        // acquire temporary name from stack 
        // add PrintInstr into the program
    }

    @Override
    public void visit(BinaryExpression node) throws ASTVisitorException {
        node.getExpression1().accept(this);
        String t1 = stack.pop();
        node.getExpression2().accept(this);
        String t2 = stack.pop();

        if (ASTUtils.isBooleanExpression(node)) {
            if (!node.getOperator().isRelational()) {
                ASTUtils.error(node, "A not boolean expression used as boolean.");
            }
            

            CondJumpInstr condJumpInstr = new CondJumpInstr(node.getOperator(), t1, t2);
            GotoInstr gotoInstr = new GotoInstr();
            program.add(condJumpInstr);
            program.add(gotoInstr);
            ASTUtils.getTrueList(node).add(condJumpInstr);
            ASTUtils.getFalseList(node).add(gotoInstr);

            // create new CondJumpInstr with null target
            // create new GotoInstr with null target
            // add both to program
            // add first instruction into truelist of node
            // add second instruction into falselist of node
        } else {
            String tmp = createTemp();
            program.add(new BinaryOpInstr(node.getOperator(), t1, t2, tmp));
            stack.push(tmp);
            // create new temporary
            // add binary operation instruction
            // add new temporary in stack
        }
    }

    @Override
    public void visit(UnaryExpression node) throws ASTVisitorException {
        node.getExpression().accept(this);
        String t1 = stack.pop();
        String t = createTemp();
        stack.push(t);
        program.add(new UnaryOpInstr(node.getOperator(), t1, t));
    }

    @Override
    public void visit(IdentifierExpression node) throws ASTVisitorException {
        stack.push(node.getIdentifier());
    }

    @Override
    public void visit(FloatLiteralExpression node) throws ASTVisitorException {
        if (ASTUtils.isBooleanExpression(node)) {
            if (node.getLiteral() != 0d) {
                GotoInstr i = new GotoInstr();
                program.add(i);
                ASTUtils.getTrueList(node).add(i);
            } else {
                GotoInstr i = new GotoInstr();
                program.add(i);
                ASTUtils.getFalseList(node).add(i);
            }
        } else {
            String t = createTemp();
            stack.push(t);
            program.add(new AssignInstr(node.getLiteral().toString(), t));
        }
    }

    @Override
    public void visit(IntegerLiteralExpression node) throws ASTVisitorException {
        if (ASTUtils.isBooleanExpression(node)) {
            if (node.getLiteral() != 0) {
                GotoInstr i = new GotoInstr();
                program.add(i);
                ASTUtils.getTrueList(node).add(i);
            } else {
                GotoInstr i = new GotoInstr();
                program.add(i);
                ASTUtils.getFalseList(node).add(i);
            }
        } else {

            String tmp = createTemp();
            stack.push(tmp);
            program.add(new AssignInstr(node.getLiteral().toString(), tmp));

            // create new temporary
            // add AssignInstr to program
            // add new temporary in stack
        }
    }

    @Override
    public void visit(StringLiteralExpression node) throws ASTVisitorException {
        if (ASTUtils.isBooleanExpression(node)) {
            ASTUtils.error(node, "Strings cannot be used as boolean expressions");
        } else {
            String t = createTemp();
            stack.push(t);
            program.add(new AssignInstr("\"" + StringEscapeUtils.escapeJava(node.getLiteral()) + "\"", t));
        }
    }

    @Override
    public void visit(ParenthesisExpression node) throws ASTVisitorException {
        for (Expression e : node.getExpressions()) {
            e.accept(this);
            String t1 = stack.pop();
            String t = createTemp();
            stack.push(t);
            program.add(new AssignInstr(t1, t));
        }

    }

    @Override
    public void visit(WhileStatement node) throws ASTVisitorException {
        ASTUtils.setBooleanExpression(node.getExpression(), true);

        LabelInstr beginLabel = program.addNewLabel();
        node.getExpression().accept(this);
        List<GotoInstr> tList = ASTUtils.getTrueList(node.getExpression());
        List<GotoInstr> fList = ASTUtils.getFalseList(node.getExpression());

        LabelInstr beginStmtLabel = program.addNewLabel();
        Program.backpatch(tList, beginStmtLabel);

        Program.backpatch(ASTUtils.getNextList(node.getStatement()), beginLabel);
        Program.backpatch(ASTUtils.getContinueList(node.getStatement()), beginLabel);
        node.getStatement().accept(this);
        program.add(new GotoInstr(beginLabel));

        ASTUtils.getNextList(node).addAll(fList);
        ASTUtils.getNextList(node).addAll(ASTUtils.getBreakList(node.getStatement()));

        // create beginLabel 
        // see Program class for details
        // produce code for expression
        // create beginStmtLabel
        // backpatch truelist of expression with beginStmtLabel
        // produce code for statement
        // backpatch nextlist of statement with beginLabel
        // backpatch continuelist of statement with beginLabel
        // add GotoInstr to beginLabel 
        // append falselist of expression into nextlist of node
        // append breaklist of statement into nextlist of node
    }

    @Override
    public void visit(DoStatement node) throws ASTVisitorException {
        ASTUtils.setBooleanExpression(node.getExpression(), true);
        LabelInstr beginLabel = program.addNewLabel();

        node.getStatement().accept(this);
        ASTUtils.getNextList(node).addAll(ASTUtils.getBreakList(node.getStatement()));
        LabelInstr beginExprLabel = program.addNewLabel();
        Program.backpatch(ASTUtils.getNextList(node.getStatement()), beginExprLabel);
        Program.backpatch(ASTUtils.getContinueList(node.getStatement()), beginExprLabel);

        node.getExpression().accept(this);
        ASTUtils.getNextList(node).addAll(ASTUtils.getFalseList(node.getExpression()));
        Program.backpatch(ASTUtils.getTrueList(node.getExpression()), beginLabel);

    }

    @Override
    public void visit(IfStatement node) throws ASTVisitorException {
        ASTUtils.setBooleanExpression(node.getExpression(), true);

        node.getExpression().accept(this);
        LabelInstr labelInstr = program.addNewLabel();
        node.getStatement().accept(this);
        Program.backpatch(ASTUtils.getTrueList(node.getExpression()), labelInstr);
        // ASTUtils.getFalseList(node.).addAll(ASTUtils.getNextList(node));
        ASTUtils.getNextList(node).addAll(ASTUtils.getNextList(node.getStatement()));
        ASTUtils.getNextList(node).addAll(ASTUtils.getFalseList(node.getExpression()));
        ASTUtils.getContinueList(node).addAll(ASTUtils.getContinueList(node.getStatement()));
        ASTUtils.getBreakList(node).addAll(ASTUtils.getBreakList(node.getStatement()));

    }

    @Override
    public void visit(IfThenElseStatement node) throws ASTVisitorException {
        ASTUtils.setBooleanExpression(node.getExpression(), true);
        node.getExpression().accept(this);
        LabelInstr trueLabel = program.addNewLabel();
        LabelInstr falseLabel = program.addNewLabel();
        node.getStatement1().accept(this);
        node.getStatement2().accept(this);
        Program.backpatch(ASTUtils.getTrueList(node.getExpression()), trueLabel);
        Program.backpatch(ASTUtils.getFalseList(node.getExpression()), falseLabel);

        ASTUtils.getTrueList(node.getExpression()).addAll(ASTUtils.getNextList(node.getStatement1()));
        ASTUtils.getFalseList(node.getExpression()).addAll(ASTUtils.getNextList(node.getStatement2()));

        ASTUtils.getNextList(node.getStatement1()).addAll(ASTUtils.getNextList(node));
        ASTUtils.getContinueList(node.getStatement1()).addAll(ASTUtils.getContinueList(node));
        ASTUtils.getBreakList(node.getStatement1()).addAll(ASTUtils.getBreakList(node));

        ASTUtils.getNextList(node.getStatement2()).addAll(ASTUtils.getNextList(node));
        ASTUtils.getContinueList(node.getStatement2()).addAll(ASTUtils.getContinueList(node));
        ASTUtils.getBreakList(node.getStatement2()).addAll(ASTUtils.getBreakList(node));

    }

    @Override
    public void visit(BreakStatement node) throws ASTVisitorException {
        GotoInstr gInstr = new GotoInstr();
        program.add(gInstr);
        ASTUtils.getBreakList(node).add(gInstr);
        // add new GotoInstr to program
        // add instruction to breaklist of node

    }

    @Override
    public void visit(ContinueStatement node) throws ASTVisitorException {
        GotoInstr gInstr = new GotoInstr();
        program.add(gInstr);
        ASTUtils.getContinueList(node).add(gInstr);

        // add new GotoInstr to program
        // add instruction to continuelist of node
    }

    @Override
    public void visit(CompoundStatement node) throws ASTVisitorException {
        node.getStatement().accept(this);
        String t1 = stack.pop();
        String t = createTemp();
        stack.push(t);
        program.add(new AssignInstr(t1, t));
    }

    @Override
    public void visit(VariableDefinition varDeclaration) throws ASTVisitorException {
        // nothing really
    }

    @Override
    public void visit(FunVarList node) throws ASTVisitorException {
        for (FunctionDefinition s : node.getFunctionDefinition()) {
            s.accept(this);
        }
        for (VariableDefinition s : node.getVariableDefinition()) {
            s.accept(this);
        }

    }

    @Override
    public void visit(FunctionDefinition node) throws ASTVisitorException {
        node.getTypeSpecifier().accept(this);
        String t = node.getIdentifier();
        FunctionDefinitionInstr f = new FunctionDefinitionInstr(t);
        if (node.getStatementList() != null ){
            for(ParameterDeclaration e : node.getParameterList()){
                e.accept(this);
            }
        }
        if (node.getStatementList() != null ){
            for (Statement s : node.getStatementList()){
                if (s == null){
                    System.out.println("NOT FUCKING AGAIN");
                }
                s.accept(this);
            }
        }

        program.add(f);

    }

    @Override
    public void visit(TypeSpecifierExpression node) throws ASTVisitorException {
        //nothing 
    }

    @Override
    public void visit(CurlyStatement node) throws ASTVisitorException {
        List<GotoInstr> breakList = new ArrayList<GotoInstr>();
        List<GotoInstr> continueList = new ArrayList<GotoInstr>();
        Statement s = null, ps;
        Iterator<Statement> it = node.getStatements().iterator();
        while (it.hasNext()) {
            ps = s;
            s = it.next();
            if (ps != null && !ASTUtils.getNextList(ps).isEmpty()) {
                Program.backpatch(ASTUtils.getNextList(ps), program.addNewLabel());
            }
            s.accept(this);
            breakList.addAll(ASTUtils.getBreakList(s));
            continueList.addAll(ASTUtils.getContinueList(s));
        }
        if (s != null) {
            ASTUtils.setNextList(node, ASTUtils.getNextList(s));
        }
        ASTUtils.setBreakList(node, breakList);
        ASTUtils.setContinueList(node, continueList);
    }

    @Override
    public void visit(ParameterDeclaration node) throws ASTVisitorException {
        //nothing
    }

    @Override
    public void visit(BracketExpression node) throws ASTVisitorException {
        node.getExpression().accept(this);
        String t1 = stack.pop();
        String t = createTemp();
        stack.push(t);
        program.add(new AssignInstr(t1, t));
    }

    @Override
    public void visit(KeywordLiteral node) throws ASTVisitorException {
        //nothing here
    }

    @Override
    public void visit(KeywordExpression node) throws ASTVisitorException {
        node.getExpression().accept(this);

    }

    @Override
    public void visit(NewArraySpecifier node) throws ASTVisitorException {
        //nothing here
    }

    @Override
    public void visit(CharacterLiteralExpression node) throws ASTVisitorException {
        String tmp = createTemp();
        stack.push(tmp);
        program.add(new AssignInstr(node.getLiteral().toString(), tmp));
    }

	@Override
	public void visit(FunctionExpression node) throws ASTVisitorException {
		System.out.println("YOOOOOO");
	}

}
