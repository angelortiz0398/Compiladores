/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author 9
 */
public class CreateLex {

    public static void main(String[] args) throws IOException, Exception {
        String path = "C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/Lexer.flex";
        String path1 = "C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/LexerCup.flex";
        String[] ruta = {
            "-parser",
            "Sintax",
            "C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/Sintax.cup"
        };
        Generar(path, path1, ruta);
    }

    public static void Generar(String s1, String s2, String[] path) throws IOException, Exception {
        File Archivo = new File(s1);
        JFlex.Main.generate(Archivo);
        File Archivo1 = new File(s2);
        JFlex.Main.generate(Archivo1);
        java_cup.Main.main(path);

        Path rutaSim = Paths.get("C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/sym.java");
        if (Files.exists(rutaSim)) {
            Files.delete(rutaSim);
        }

        Files.move(
                Paths.get("C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/sym.java"),
                Paths.get("C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/sym.java")
        );

        Path rutaSin = Paths.get("C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }

        Files.move(
                Paths.get("C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/Sintax.java"),
                Paths.get("C:/Users/9/OneDrive/Documentos/NetBeansProjects/vmc/src/src/Sintax.java")
        );

    }
}
