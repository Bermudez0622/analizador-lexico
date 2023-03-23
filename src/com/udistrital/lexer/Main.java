package com.udistrital.lexer;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.udistrital.lexer.reviewer.Extractor;
import com.udistrital.lexer.tokens.Tokens;

public class Main {

    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {

        File file = null;

        for(String arg : args) {
            file = new File(arg);
            try(Scanner reader = new Scanner(file)) {
        
                log.log(
                    Level.INFO,
                    "Inicia: {0}",
                    file.getName()
                );

                while(reader.hasNextLine()) {
                    String line = reader.nextLine();
            
                    Extractor.verifyLine(line);
                }

                log.log(
                    Level.INFO,
                    "Finaliza: {0}",
                    file.getName()
                );
            } catch(Exception e) {
                log.log(Level.SEVERE, "ERROR EN LA LECTURA DEL ARCHIVO [{0}]", e.getMessage());
            }

            log.log(
                Level.INFO,
                "Tabla de simbolos"
            );

            Tokens.getAllTokens().entrySet().forEach(tokens -> {
                log.log(Level.INFO, tokens.getKey());

                tokens.getValue().forEach(count -> System.out.printf("%-10s\t%-8s%n", count.getKey(), count.getValue()));
            });

        }

    }
}