package com.udistrital.lexer.tokens;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Developer {

    private Developer() {}

    protected static final Map<String, Integer> identifiers = new LinkedHashMap<>();

    public static void add(String identifier) {
        if(identifiers.containsKey(identifier.replace(";", "").replace("()", ""))) {
            identifiers.put(
                identifier.replace(";", "").replace("()", ""),
                identifiers.get(identifier.replace(";", "").replace("()", "")) + 1
            );
        } else {
            identifiers.put(identifier.replace(";", "").replace("()", ""), 1);
        }
    }

    public static boolean exists(String token) {
        return identifiers.containsKey(token.replace(";", "").replace("()", ""));
    }

    public static Set<Entry<String, Integer>> getIdentifiers() {
        return identifiers.entrySet();
    }
}
