package com.udistrital.lexer.tokens;

import java.util.HashMap;
import java.util.Map;

public class Preprocessors {

    private Preprocessors() {}

    protected static final Map<String, Integer> macros = new HashMap<>();

    static {
        macros.put("#include", 0);
    }

    public static boolean isPreprocessor(String token) {
        return macros.containsKey(token);
    }

    public static void add(String token) {
        if(macros.containsKey(token)) {
            macros.put(
                token,
                macros.get(token) + 1
            );
        }
    }
    
}
