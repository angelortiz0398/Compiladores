package src;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Comillas */
( "\"" ) {return new Symbol(sym.Comillas, yychar, yyline, yytext());}

/* Tipos de datos */
( byte | int | char | long | float | double  | Caracter | Largo | Flotante | Doble ) {return new Symbol(sym.Tipo, yychar, yyline, yytext());}

/* Tipo de dato Int (Para el main) */
( "int" ) {return new Symbol(sym.Int, yychar, yyline, yytext());}

/* Tipo de dato String */
( String ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}

/* Palabra reservada If */
( if ) {return new Symbol(sym.If, yychar, yyline, yytext());}
( Si ) {return new Symbol(sym.Si, yychar, yyline, yytext());}

/* Palabra reservada Else */
( else ) {return new Symbol(sym.Else, yychar, yyline, yytext());}
( Entonces ) {return new Symbol(sym.Entonces, yychar, yyline, yytext());}

/* Palabra reservada Do */
( do ) {return new Symbol(sym.Do, yychar, yyline, yytext());}
( Hacer ) {return new Symbol(sym.Hacer, yychar, yyline, yytext());}

/* Palabra reservada While */
( while ) {return new Symbol(sym.While, yychar, yyline, yytext());}
( Mientras ) {return new Symbol(sym.Mientras, yychar, yyline, yytext());}

/* Palabra reservada For */
( for ) {return new Symbol(sym.For, yychar, yyline, yytext());}
( Para ) {return new Symbol(sym.Para, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Operador Suma */
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}

/* Operador Resta */
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}

/* Operador Multiplicacion */
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}

/* Operador Division */
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}

/* Operadores logicos */
( "&&" | "||" | "!" | "&" | "|" ) {return new Symbol(sym.Op_logico, yychar, yyline, yytext());}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return new Symbol(sym.Op_relacional, yychar, yyline, yytext());}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" | "=" ) {return new Symbol(sym.Op_atribucion, yychar, yyline, yytext());}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return new Symbol(sym.Op_incremento, yychar, yyline, yytext());}

/*Operadores Booleanos*/
( true | false ) {return new Symbol(sym.Op_booleano, yychar, yyline, yytext());}

/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.ParIzq, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.ParDer, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.LlaveIzq, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.LlaveDer, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.CorcheteIzq, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.CorcheteDer, yychar, yyline, yytext());}

/* Pseudocodigo */
delocontrario {return new Symbol(sym.Sino, yychar, yyline, yytext());}
siDefinido {return new Symbol(sym.SiDefinido, yychar, yyline, yytext());}
finDefinido {return new Symbol(sym.FinDefinido, yychar, yyline, yytext());;}

/* Atributos */
final {return new Symbol(sym.Final, yychar, yyline, yytext());}
Final {return new Symbol(sym.Final, yychar, yyline, yytext());}
class {return new Symbol(sym.Class, yychar, yyline, yytext());}
Clase {return new Symbol(sym.Clase, yychar, yyline, yytext());}
Static {return new Symbol(sym.Static, yychar, yyline, yytext());}
Estatico {return new Symbol(sym.Estatico, yychar, yyline, yytext());}
Void {return new Symbol(sym.Void, yychar, yyline, yytext());}
Vacio {return new Symbol(sym.Vacio, yychar, yyline, yytext());}
public {return new Symbol(sym.Public, yychar, yyline, yytext());}
Publico {return new Symbol(sym.Publico, yychar, yyline, yytext());}

/* Ciclos */
continue {return new Symbol(sym.Continue, yychar, yyline, yytext());}
Continuar {return new Symbol(sym.Continuar, yychar, yyline, yytext());}
break {return new Symbol(sym.Break, yychar, yyline, yytext());}
Romper {return new Symbol(sym.Romper, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "main" ) {return new Symbol(sym.Main, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}

