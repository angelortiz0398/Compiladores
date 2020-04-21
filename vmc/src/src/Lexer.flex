package src;
import static src.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%
( "\"" ) {lexeme=yytext(); return Comillas;}

/* Tipos*/
(Int| byte | int | char | long | float | double | Entero | Caracter | Largo | Flotante | Doble ) {lexeme=yytext(); return Tipo;}

/* Incluir */
define {lexeme = yytext(); return Define;}
include {lexeme = yytext(); return Include;}
incluye {lexeme = yytext(); return Incluye;}

( String ) {lexeme=yytext(); return Cadena;}

/* Palabra reservada If */
( if ) {lexeme=yytext(); return If;}
( Si ) {lexeme=yytext(); return Si;}

/* Palabra reservada Else */
( else ) {lexeme=yytext(); return Else;}
( Entonces ) {lexeme=yytext(); return Entonces;}

/* Palabra reservada Do */
( do ) {lexeme=yytext(); return Do;}
( Hacer ) {lexeme=yytext(); return Hacer;}

/* Palabra reservada While */
( while ) {lexeme=yytext(); return While;}
( Mientras ) {lexeme=yytext(); return Mientras;}

/* Palabra reservada For */
( for ) {lexeme=yytext(); return For;}
( Para ) {lexeme=yytext(); return Para;}

/* Pseudocodigo */
delocontrario {lexeme = yytext(); return Sino;}
siDefinido {lexeme = yytext(); return SiDefinido;}
finDefinido {lexeme = yytext(); return FinDefinido;}

/* Atributos */
final {lexeme = yytext(); return Final;}
Final {lexeme = yytext(); return Final;}
class {lexeme = yytext(); return Class;}
Clase {lexeme = yytext(); return Clase;}
Static {lexeme = yytext(); return Static;}
Estatico {lexeme = yytext(); return Estatico;}
Void {lexeme = yytext(); return Void;}
Vacio {lexeme = yytext(); return Vacio;}
public {lexeme = yytext(); return Public;}
Publico {lexeme = yytext(); return Publico;}

/* Ciclos */
continue {lexeme = yytext(); return Continue;}
Continuar {lexeme = yytext(); return Continuar;}
break {lexeme = yytext(); return Break;}
Romper {lexeme = yytext(); return Romper;}

(espacio) {/*Ignore*/}
"//".* {/*Ignore*/}
" " {/*Ignore*/}
"  " {/*Ignore*/}
"=" {lexeme = yytext(); return Igual;}
"\n" {lexeme = yytext(); return Linea;}
"==" {lexeme = yytext();return Asignacion;}
"+" {lexeme = yytext(); return Suma;}
"-" {lexeme = yytext(); return Resta;}
"*" {lexeme = yytext(); return Multiplicacion;}
"/" {lexeme = yytext(); return Division;}
"(" {lexeme = yytext(); return ParIzq;}
")" {lexeme = yytext(); return ParDer;}
"{" {lexeme = yytext(); return LlaveIzq;}
"}" {lexeme = yytext(); return LlaveDer;}
"[" {lexeme = yytext(); return CorcheteIzq;}
"]" {lexeme = yytext(); return CorcheteDer;}

/* Operadores logicos */
( "&&" | "||" | "!" | "&" | "|" ) {lexeme=yytext(); return Op_logico;}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {lexeme = yytext(); return Op_relacional;}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" ) {lexeme = yytext(); return Op_atribucion;}

/* Operadores Incremento y decremento */
( "++" | "--" ) {lexeme = yytext(); return Op_incremento;}

/*Operadores Booleanos*/
(true | false)      {lexeme = yytext(); return Op_booleano;}

"main" {lexeme = yytext(); return Main;}

";" {lexeme = yytext(); return P_coma;}

{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}