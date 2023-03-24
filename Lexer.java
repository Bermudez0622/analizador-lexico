import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexer {

    private BufferedReader reader;
    private String line;
    private int index;

    public Lexer(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        line = reader.readLine();
        index = 0;
    }

    public Token getNextToken() throws IOException {
        while (line != null) {
            // Ignorar espacios en blanco
            while (index < line.length() && Character.isWhitespace(line.charAt(index))) {
                index++;
            }
            if (index == line.length()) {
                // Final de línea
                line = reader.readLine();
                index = 0;
                continue;
            }
            // Identificadores y palabras clave
            if (Character.isLetter(line.charAt(index))) {
                int startIndex = index;
                index++;
                while (index < line.length() && Character.isLetterOrDigit(line.charAt(index))) {
                    index++;
                }
                String lexeme = line.substring(startIndex, index);
                if (lexeme.equals("include") || lexeme.equals("int") || lexeme.equals("char") || lexeme.equals("bool")) {
                    return new Token(TokenType.KEYWORD, lexeme);
                } else {
                    return new Token(TokenType.IDENTIFIER, lexeme);
                }
            }
            // Literales de cadena
            if (line.charAt(index) == '"') {
                int startIndex = index;
                index++;
                while (index < line.length() && line.charAt(index) != '"') {
                    index++;
                }
                if (index == line.length()) {
                    throw new RuntimeException("Literal de cadena sin terminar");
                }
                index++;
                String lexeme = line.substring(startIndex, index);
                return new Token(TokenType.STRING_LITERAL, lexeme);
            }
            // Literales de caracteres
            if (line.charAt(index) == '\'') {
                int startIndex = index;
                index++;
                if (index < line.length() && line.charAt(index) == '\\') {
                    index++;
                }
                index++;
                if (index == line.length() || line.charAt(index) != '\'') {
                    throw new RuntimeException("Literal de caracter mal formado");
                }
                index++;
                String lexeme = line.substring(startIndex, index);
                return new Token(TokenType.CHARACTER_LITERAL, lexeme);
            }
            // Operadores y signos de puntuación
            switch (line.charAt(index)) {
                case '+':
                    index++;
                    return new Token(TokenType.PLUS
