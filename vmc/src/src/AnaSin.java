package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java_cup.runtime.Symbol;
import static src.AnaLex.dir;

public class AnaSin {

    int codErr = 0;
    Tokens t;
    String str = "";
    int conta = 0;
    public static String dir;

    AnaSin(Tokens tokens, String s) {
        t = tokens;
        str = s;
    }

    AnaSin(String fuente) {
        AnaSin.dir = fuente;
    }

    void recorrido(Tokens token, String s, String[] Ins, int cont) {
        conta = cont;
        asignacion(token, s, Ins, conta);
    }

    private int asignacion(Tokens token, String s, String[] Ins, int cont) {
        codErr = 1;
        String Id = "";
        String temp = "";
        if (t.equals(Tokens.Identificador)) {
            Id = s;
            System.out.println("POP POP asigna PUSH");
            temp = saltar(Ins, conta, token, Tokens.Igual.name());
            if (temp.equals(Tokens.Igual.name())) {

                System.out.println("PUSH: " + Id);
                codErr = expresion(token, s, Ins, conta);
            } else {
//                System.out.println("Error: No se encontro un igual");
            }
        } else {
//            System.out.println("Error: se esperaba Id");
        }
        return codErr;
    }

    private int expresion(Tokens token, String s, String[] Ins, int cont) {
        codErr = 1;
        codErr = termino(token, s, Ins, conta);
        if (codErr == 0) {
            codErr = masTerminos(token, s, Ins, conta);
        }
        return codErr;
    }

    private int termino(Tokens token, String s, String[] Ins, int cont) {
        codErr = 1;
        codErr = factor(token, s, Ins, conta);
        if (codErr == 0) {
            codErr = masFactores(token, s, Ins, conta);
        }
        return codErr;
    }

    private int masTerminos(Tokens token, String s, String[] Ins, int cont) {
        int RegistroA, RegistroB, RegistroC;
        String temp1 = "";
        String temp2 = "";
        codErr = 0;
        temp1 = saltar(Ins, conta, token, Tokens.Suma.name());
        temp2 = saltar(Ins, conta, token, Tokens.Resta.name());

        if (temp1.equals(Tokens.Suma.name()) || temp2.equals(Tokens.Resta.name())) {
            if (temp1.equals(Tokens.Suma.name())) {
                codErr = termino(token, s, Ins, conta);

                //Ejecutar la microinstruccion
                System.out.println("POP POP add PUSH");
                codErr = masTerminos(token, s, Ins, conta);
            } else if (temp2.equals(Tokens.Resta.name())) {
                codErr = termino(token, s, Ins, conta);

                //Ejecutar la microinstruccion
                System.out.println("POP POP substract PUSH");

                codErr = masTerminos(token, s, Ins, conta);
            }
        }

        return codErr;
    }

    private int factor(Tokens token, String s, String[] Ins, int cont) {
        codErr = 1;
        String temp = "";
        temp = saltar(Ins, conta, token, Tokens.ParIzq.name());
        if (temp.equals(Tokens.ParIzq.name())) {
            System.out.println("POP: (");
            codErr = expresion(token, s, Ins, conta);
            if (codErr == 0) {
                temp = saltar(Ins, conta, token, Tokens.ParDer.name());
                if (temp.equals(Tokens.ParDer.name())) {
                    System.out.println("POP: )");
                    codErr = 0;
                }
            }
        }
        temp = saltar(Ins, conta, token, Tokens.Identificador.name());
        if (temp.equals(Tokens.Identificador.name())) {
            System.out.println("Push: " + "Id");
            codErr = 0;
        }
        temp = saltar(Ins, conta, token, Tokens.Numero.name());
        if (temp.equals(Tokens.Numero.name())) {
            System.out.println("Push: " + "Numero");
            codErr = 0;
        }
        return codErr;
    }

    private int masFactores(Tokens token, String s, String[] Ins, int cont) {
        int RegistroA, RegistroB, RegistroC;
        String temp1 = "";
        String temp2 = "";
        temp1 = saltar(Ins, conta, token, Tokens.Multiplicacion.name());
        temp2 = saltar(Ins, conta, token, Tokens.Division.name());
        codErr = 0;
        if (temp1.equals(Tokens.Multiplicacion.name()) || temp2.equals(Tokens.Division.name())) {
            if (temp1.equals(Tokens.Multiplicacion.name())) {
                codErr = factor(token, s, Ins, conta);
//
//                //Ejecutar la microinstruccion
                System.out.println("POP POP  mult PUSH ");

                codErr = masFactores(token, s, Ins, conta);
            } else if (temp2.equals(Tokens.Division.name())) {
                codErr = factor(token, s, Ins, conta);
//
//                //Ejecutar la microinstruccion
                System.out.println("POP POP  divide PUSH");

                codErr = masFactores(token, s, Ins, conta);
            }
        }

        return codErr;
    }

    String saltar(String[] Inst, int cont, Tokens t, String s) {
        this.conta = cont;
        if (Inst.length != conta) {
            if (Inst[this.conta + 1] != null && Inst[this.conta + 1].equals(s)) {
                this.conta++;
                return s;
            }

        }
        return "fail";
    }

    public void Sintactic() throws FileNotFoundException {
        String path = "..\\vmc\\src\\src\\" + dir;
        Reader lector = new BufferedReader(new FileReader(path));
        Sintax s = new Sintax(new src.LexerCup(lector));
        try {
            Symbol sym = s.getS();
            s.parse();
            System.out.println("Analisis sintactico realizado correctamente");

        } catch (Exception ex) {
            Symbol sym = s.getS();
            if (sym.sym == 0) {
                System.out.println("Intente con un archivo que no este vacio ");
            } else {
                System.out.println("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");

            }
        }
    }

}
