package com.udistrital.lexer;
import java.io.File;


public class Principal {
    public void main(String[] args) {
        String ruta = "C:/Users/POWER/Documents/NetBeansProjects/analizador-lexico-main/src/com/udistrital/lexer/Lexer.flex";
        generarLexer(ruta);
    }
    public static void generarLexer(String ruta){
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
}
