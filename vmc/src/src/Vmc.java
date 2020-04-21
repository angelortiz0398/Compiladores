package src;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vmc {

    /*
    El archivo Lexer fue necesario para JFlex y Yylex es el resultado de la compilacion y es usado para AnaLex 
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Compilador sin pilas");
        System.out.println("Beta 0.9 Java");
        System.out.print("> Archivo a compilar: ");
        Scanner sc = new Scanner(System.in);
        String fuente = sc.nextLine();
        AnaLex lex = new AnaLex(fuente);
        AnaSin sin = new AnaSin(fuente);
        sin.Sintactic();
        
    }

}
