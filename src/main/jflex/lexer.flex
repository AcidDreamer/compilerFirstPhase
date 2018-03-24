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

%{
    // user custom code 

    StringBuffer sb = new StringBuffer();

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
    {Identifier}                   { out.println("id:" + yytext()); }
    /* literals */
    {IntegerLiteral}               { if(yytext().length() <= 10 ) {
                                        out.println("integer:" + yytext());
                                    }else{
                                        throw new RuntimeException("IntegerSizeException " + (yyline+1) + ":" + (yycolumn+1) + ": integers should not be longer than 10 digits");
                                    } }
    {CharacterLiteral}             {  if(yytext().length() == 3 ){
                                        out.println("character:" + yytext().charAt(1));
                                       }else{ 
                                        out.println("character:" + yytext().charAt(1) + yytext().charAt(2)); 
                                    } }
    {FloatLiteral}                 { out.println("float: " + yytext()); }

    \"                             { sb.setLength(0); yybegin(STRING); }

    /* operators */
    "="                            { out.println("ASSIGN"); }
    "+"                            { out.println("PLUS"); }
    ";"                            { out.println("SEMICOLON"); }
    "=="                         { out.println("EQUALS"); } 
    "-"                             { out.println("MINUS"); } 
    "("                             { out.println("LEFT_PARENTHESIS"); } 
    ")"                             { out.println("RIGHT_PARENTHESIS"); } 
    ","                                { out.println("COMMA"); } 
    "*"                             { out.println("STAR"); } 
    "["                                { out.println("LEFT_SQUARE_BRACKET"); } 
    "]"                                 { out.println("RIGHT_SQUARE_BRACKET"); } 
    "{"                             { out.println("LEFT_CURLY_BRACKET"); } 
    "}"                             { out.println("RIGHT_CURLY_BRACKET"); }
    ">"                            { out.println("GREATER THAN");}
    "<"                            {out.println("LESS THAN");} 
    "<="                           {out.println("EQUAL OR LESS THAN");}   
    ">="                           {out.println("EQUAL OR GREATER THAN");}
    "!="                           {out.println("NOT EQUAL");}
    "/"                            {out.println("DIVISION");}
    "%"                            {out.println("MOD");}
    "&&"                           {out.println("AND");} 
    "||"                           {out.println("OR");} 
    "!"                            {out.println("NOT");}


    /* comments */
    {Comment}                      { /* ignore */ }

    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }
}

<STRING> {
    \"                             { yybegin(YYINITIAL);
                                     out.println("string:" + sb.toString()); 
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