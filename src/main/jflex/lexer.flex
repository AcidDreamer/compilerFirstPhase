/**
 *  This code is part of the lab exercises for the Compilers course
 *  at Harokopio University of Athens, Dept. of Informatics and Telematics.
 */

import static java.lang.System.out;

%%

%class Lexer
%unicode
%public
%final
%integer
%line
%column
%cup 

%eofval{
    return createSymbol(sym.EOF);
%eofval}

%{
    private StringBuffer sb = new StringBuffer();

    private Symbol createSymbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol createSymbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private Symbol createSymbol(int type, String forValue  ){
        return new Symbol(Symbol.switchKeywords(forValue), yyline+1, yycolumn+1, value);
    }
    private int switchKeywords(String value){
        switch (forValue){
            case "int"  :
                return  sym.INT_LITERAL;
            case "float":
            return  sym.FLOAT_KEYWORD;
            case "char" :
            return  sym.FLOAT_KEYWORD;
            case "while":
            return  sym.WHILE_KEYWORD;
            case "if"   :
            return  sym.IF_KEYWORD;
            case "else" :
            return  sym.ELSE_KEYWORD;
            case "return":
            return  sym.RETURN_KEYWORD;
            case "break":
            return  sym.BREAK_KEYWORD;
            case "continue":
            return  sym.CONTINUE_KEYWORD;
            case "new" :
            return  sym.NEW_KEYWORD;
            case "delete" :
            return  sym.DELETE_KEYWORD;
            case "void"  :
            return  sym.VOID_KEYWORD;
            case "print" :
            return  sym.PRINT_KEYWORD;
        }
  }
%}

LineTerminator      = \r|\n|\r\n
WhiteSpace          = {LineTerminator} | [ \t\f] 
Comment             = "/*" [^*] ~"*/" | "/*" "*"+ "/" | "//"  ~{LineTerminator}

Identifier          = [:jletter:] [:jletterdigit:]* 
IntegerLiteral      = 0 | [1-9][0-9]*
FloatLiteral        = {IntegerLiteral} \. {IntegerLiteral}
CharacterAcceptable = [a-zA-Z0-9] | "\\n" | "\\t" | "\\0"
CharacterLiteral    = "'" {CharacterAcceptable}   "'" 
KeywordsFirstPart   = "int" | "float" | "char" | "while" | "if" | "else" 
KeywordsSecondPart  = "return" | "break" | "continue" |"new" |"delete" |"void" | "print"


%state STRING

%%
<YYINITIAL> {
    /* reserved keywords */
    {KeywordsFirstPart}                     { out.println("Keyword:" + yytext());}
    {KeywordsSecondPart}                    { out.println("Keyword:" + yytext());}
    
    /* identifiers */ 
    {Identifier}                   { return createSymbol(sym.IDENTIFIER, yytext()); }
    /* literals */
    {IntegerLiteral}               { if(yytext().length() <= 10 ) {
                                        return createSymbol(sym.INTEGER_LITERAL, Integer.valueOf(yytext()));
                                    }else{
                                        throw new RuntimeException("IntegerSizeException " + (yyline+1) + ":" + (yycolumn+1) + ": integers should not be longer than 10 digits");
                                    } }
    {CharacterLiteral}             {  return createSymbol(sym.CHARACTER_LITERAL, Character.valueOf(yytext())); }

    {FloatLiteral}                 { return createSymbol(sym.DOUBLE_LITERAL, Double.valueOf(yytext())); }

    \"                             { sb.setLength(0); yybegin(STRING); }

    /* operators */
    "="                            { return createSymbol(sym.ASSIGN); } //FIX
    "+"                            { return createSymbol(sym.PLUS); }
    ";"                            { return createSymbol(sym.SEMICOLON); }
    "=="                         { return createSymbol(sym.EQ); } 
    "-"                             { return createSymbol(sym.MINUS); } 
    "("                             { return createSymbol(sym.LPAREN); } 
    ")"                             { return createSymbol(sym.RPAREN);} 
    ","                                { return createSymbol(sym.COMA); } //FIX
    "*"                             { return createSymbol(sym.TIMES); } 
    "["                                { return createSymbol(sym.LBRAC); } 
    "]"                                 { return createSymbol(sym.RBRAC); } 
    "{"                             { return createSymbol(sym.LCURLY); } 
    "}"                             { return createSymbol(sym.RCURLY); }
    ">"                            {return createSymbol(sym.GREATER_THAN);}
    "<"                            {return createSymbol(sym.LESS_THAN);} 
    "<="                           {return createSymbol(sym.LESS_OR_EQUAL);}   
    ">="                           {return createSymbol(sym.GREATER_OR_EQUAL);}
    "!="                           {return createSymbol(sym.NOT_EQUAL);}
    "/"                            {return createSymbol(sym.DIVISION);}
    "%"                            {return createSymbol(sym.MOD);} //FIX
    "&&"                           {return createSymbol(sym.AND);} //FIX
    "||"                           {return createSymbol(sym.OR);} //FIX
    "!"                            {return createSymbol(sym.NOT);} //FIX


    /* comments */
    {Comment}                      { /* ignore */ }

    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }
}

<STRING> {
    \"                             { yybegin(YYINITIAL);
                                     return createSymbol(sym.STRING_LITERAL, sb.toString());
                                   }

    [^\n\r\"\\]+                   { sb.append(yytext()); }
    \\t                            { sb.append('\t'); }
    \\n                            { sb.append('\n'); }

    \\r                            { sb.append('\r'); }
    \\\"                           { sb.append('\"'); }
    \\                             { sb.append('\\'); }
}


/* error fallback */
[^]                                { throw new RuntimeException("IllegalCharacterException "+(yyline+1) + ":" + (yycolumn+1) + ": illegal character <"+ yytext()+">"); }