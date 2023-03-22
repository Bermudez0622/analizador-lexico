package com.udistrital.lexer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import com.udistrital.lexer.reviewer.Reviewer;

public class Main {

    private static final Logger log = Logger.getGlobal();
    public static void main(String[] args) throws FileNotFoundException {

        File file;
        Scanner reader;

        for(String arg : args) {
            file = new File(arg);
            reader = new Scanner(file);
            
            log.log(
                Level.INFO,
                "Inicia: {0}",
                file.getName()
            );

            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                log.log(
                    Level.INFO,
                    "{0}",
                    line
                );

                Matcher math = Reviewer.include.matcher(line);

                log.log(
                    Level.INFO,
                    "{0}",
                    math.matches()
                );

            }

            log.log(
                Level.INFO,
                "Finaliza: {0}",
                file.getName()
            );

            reader.close();
        }

    }
}