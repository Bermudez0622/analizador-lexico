package com.udistrital.lexer.tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Developer {

    private Developer() {}

    protected static final Map<String, Integer> identifiers = new HashMap<>();

    public static void add(String identifier) {
        if(identifiers.containsKey(identifier)) {
            identifiers.put(
                identifier,
                identifiers.get(identifier) + 1
            );
        } else {
            identifiers.put(identifier, 1);
        }
    }

    public static Set<Entry<String, Integer>> getIdentifiers() {
        return identifiers.entrySet();
    }
}
