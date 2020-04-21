package src;

import static src.AnaLex.Ins;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 * @author 9
 */
public class AnaLex {

    public static Tokens Instruc;
    public static String[] Ins;
    public static String dir;
    public static int conta = 0;

    public static void main(String[] args) {
        String path = "..\\vmc\\src\\src\\" + dir;
        int i = 0;
        try {
            Reader lector = new BufferedReader(new FileReader(path));
            Yylex lexer = new Yylex(lector);
            String resultado = "";
            Ins = new String[300];
            while (true) {
                Instruc = lexer.yylex();
                if (Instruc == null) {
                    resultado += "Finalizo analizador lexico";
                    break;
                }
                Ins[i] = Instruc.name();
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error a en analisis lexico ");
        } catch (IOException ex) {
            System.out.println("Error b en analisis lexico");
        }
        try {
            Reader lector = new BufferedReader(new FileReader(path));
            String resultado = "";
            Lexer lexer = new Lexer(lector);
            System.out.println("Inicio del analisis lexico y sintactico: \n");
            while (true) {
                Tokens token = lexer.yylex();
                if (token == null) {
                    resultado += "Finalizo analizador lexico";
                    System.out.println(resultado);
                    System.out.println("\n");
                    return;
                }
                switch (token) {
                    case Linea:
                        conta++;
                        resultado += "LINEA " + conta + "\n";
                        break;
                    case Comillas:
                        resultado += "  <Comillas>\t\t" + lexer.lexeme + "\n";
                        break;
                    case Cadena:
                        resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                        break;
                    case Tipo:
                        resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                        break;
                    case If:
                        resultado += "  <Reservada if>\t" + lexer.lexeme + "\n";
                        break;
                    case Else:
                        resultado += "  <Reservada else>\t" + lexer.lexeme + "\n";
                        break;
                    case Do:
                        resultado += "  <Reservada do>\t" + lexer.lexeme + "\n";
                        break;
                    case While:
                        resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                        break;
                    case For:
                        resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                        break;
                    case Igual:
                        resultado += "  <Operador igual>\t" + lexer.lexeme + "\n";
                        break;
                    case Suma:
                        resultado += "  <Operador suma>\t" + lexer.lexeme + "\n";
                        break;
                    case Resta:
                        resultado += "  <Operador resta>\t" + lexer.lexeme + "\n";
                        break;
                    case Multiplicacion:
                        resultado += "  <Operador multiplicacion>\t" + lexer.lexeme + "\n";
                        break;
                    case Division:
                        resultado += "  <Operador division>\t" + lexer.lexeme + "\n";
                        break;
                    case Op_logico:
                        resultado += "  <Operador logico>\t" + lexer.lexeme + "\n";
                        break;
                    case Op_incremento:
                        resultado += "  <Operador incremento>\t" + lexer.lexeme + "\n";
                        break;
                    case Op_relacional:
                        resultado += "  <Operador relacional>\t" + lexer.lexeme + "\n";
                        break;
                    case Op_atribucion:
                        resultado += "  <Operador atribucion>\t" + lexer.lexeme + "\n";
                        break;
                    case Op_booleano:
                        resultado += "  <Operador booleano>\t" + lexer.lexeme + "\n";
                        break;
                    case ParIzq:
                        resultado += "  <Parentesis de apertura>\t" + lexer.lexeme + "\n";
                        break;
                    case ParDer:
                        resultado += "  <Parentesis de cierre>\t" + lexer.lexeme + "\n";
                        break;
                    case LlaveIzq:
                        resultado += "  <Llave de apertura>\t" + lexer.lexeme + "\n";
                        break;
                    case LlaveDer:
                        resultado += "  <Llave de cierre>\t" + lexer.lexeme + "\n";
                        break;
                    case CorcheteIzq:
                        resultado += "  <Corchete de apertura>\t" + lexer.lexeme + "\n";
                        break;
                    case CorcheteDer:
                        resultado += "  <Corchete de cierre>\t" + lexer.lexeme + "\n";
                        break;
                    case Main:
                        resultado += "  <Reservada main>\t" + lexer.lexeme + "\n";
                        break;
                    case P_coma:
                        resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                        break;
                    case Identificador:
                        resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                        break;
                    case Numero:
                        resultado += "  <Numero>\t\t" + lexer.lexeme + "\n";
                        break;
                    case ERROR:
                        resultado += "  <Simbolo no definido>\n";
                        break;
                    default:
                        resultado += "  < " + lexer.lexeme + " >\n";
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error en el analisis lexico" + e);
        }
    }

    AnaLex(String fuente) {
        AnaLex.dir = fuente;
        AnaLex.main(Ins);
    }

}
