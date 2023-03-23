package com.udistrital.lexer.reviewer;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.udistrital.lexer.stacks.Delimiters;
import com.udistrital.lexer.tokens.Developer;
import com.udistrital.lexer.tokens.Imports;
import com.udistrital.lexer.tokens.Keywords;
import com.udistrital.lexer.tokens.Operators;
import com.udistrital.lexer.tokens.Preprocessors;

public class Extractor {
    private Extractor() {}

    private static final Logger log = Logger.getGlobal();

    public static void verifyLine(String line) throws IOException {

        List<String> elements = new LinkedList<>(Arrays.asList(line.split(" ")));

                if(!elements.isEmpty()) {
                    elements.removeIf(element -> element.isBlank() || element.isEmpty());
                }

        verifyPreprocessors(elements);
        verifyKeywords(elements);
        verifyIdentifiers(elements);
        verifyOperators(elements);

    }

    private static void verifyPreprocessors(List<String> elements) throws IOException {
        boolean containsInclude = false;
        for(String element : elements) {

            if(containsInclude) {
                if(Reviewer.isImport(element)) {
                    Imports.add(element);
                } else {
                    throw new IOException(
                        String.format("Error en el elemento: %s", element)
                    );
                }
            }

            if(Preprocessors.isPreprocessor(element)) {
                containsInclude = true;

                Preprocessors.add(element);
            }
        }
    }
    private static void verifyOperators(List<String> elements) throws IOException {
        for(String element : elements) {
            if(Operators.containsOperator(element)) {
                log.log(Level.INFO, "Contiene operador {0}", element);
                Operators.add(element);
            }
            if(Operators.isDelimiter(element) && !Delimiters.add(element)) {
                throw new IOException(
                    String.format("Mal manejo de delimitadores: %s", element)
                );
            }
        }
    }
    private static void verifyKeywords(List<String> elements) {
        for(String element : elements) {
            if(Keywords.isKeyword(element)) {
                Keywords.add(element);
            }
        }
    }
    private static void verifyIdentifiers(List<String> elements) throws IOException {
        boolean hasTypeDefinition = false;
        for(String element : elements) {
            if(!Keywords.isKeyword(element) && !Preprocessors.isPreprocessor(element) && !Operators.isOperator(element) && !Imports.exists(element)) {
                if(!Developer.exists(element) && !hasTypeDefinition) {
                    throw new IOException(
                        String.format("Variable no declarada: %s", element)
                    );
                } else if(Developer.exists(element) && hasTypeDefinition) {
                    throw new IOException(
                        String.format("La variable ya ha sido declarada: %s", element)
                    );
                } else {
                    Developer.add(element);
                }
            }
            if(Keywords.isTypeDefinition(element) || Keywords.isNamespace(element)) {
                hasTypeDefinition = true;
            }
        }
    }

}
