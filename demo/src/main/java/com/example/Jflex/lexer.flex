/*CABECERA*/
package com.example.Jflex;
import com.example.Token.TipoToken;
import com.example.Token.Token;
import java.util.ArrayList;


%%
/*CONFIGURACIONES*/
%class Lexer
%public
%unicode
%line
%column
%type Token

/*CODIGO JAVA*/
%{
    ArrayList<Token> tokens = new ArrayList<>();
    public void imprimirToken(String tipoToken,String lexema,int linea, int columna){
        System.out.println(tipoToken + " lexema: " + lexema + "  linea: " + linea + " columna: " + columna);
    }

    public void guardarToken(Token t){
        tokens.add(t);
    }

    public ArrayList<Token> getTokens(){
        return tokens;
    }
    
%}

/*EXPRESIONES REGULARES  IDENTIFICADORES, NUMEROS, DECIMALES, PALABRAS RESERVADAS */

Identificador = [:jletter:] [:jletterdigit:]*
DIGITO = [0-9]
Numero = 0 | [1-9]{DIGITO}*
Decimal = ({Numero}\.{DIGITO}+ | \.{DIGITO}+)
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

%%
/*ACCIONES*/
"+"                 {guardarToken(new Token(TipoToken.MAS,yytext(),yyline+1,yycolumn+1));}
"-"                 {guardarToken(new Token(TipoToken.MENOS,yytext(),yyline+1,yycolumn+1));}
"*"                 {guardarToken(new Token(TipoToken.POR,yytext(),yyline+1,yycolumn+1));}
"/"                 {guardarToken(new Token(TipoToken.DIV,yytext(),yyline+1,yycolumn+1));}
";"                 {guardarToken(new Token(TipoToken.PCOMA,yytext(),yyline+1,yycolumn+1));}
"="                 {guardarToken(new Token(TipoToken.IGUAL,yytext(),yyline+1,yycolumn+1));}
"escribir"          {guardarToken(new Token(TipoToken.ESCRIBIR,yytext(),yyline+1,yycolumn+1));}
"entero"            {guardarToken(new Token(TipoToken.ENTERO,yytext(),yyline+1,yycolumn+1));}
{Identificador}     {guardarToken(new Token(TipoToken.ID,yytext(),yyline+1,yycolumn+1));}
{Numero}            {guardarToken(new Token(TipoToken.NUMERO,yytext(),yyline+1,yycolumn+1));}
{Decimal}           {guardarToken(new Token(TipoToken.DECIMAL,yytext(),yyline+1,yycolumn+1));}
{LineTerminator}    {/*IGNORARLO*/}
{WhiteSpace}        {/*IGNORAMOS*/}
[^]                 {System.out.println("CARACTER INVALIDO");}
