package com.udistrital.lexer.tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Imports {

    private Imports() {}

    protected static final Map<String, Integer> includes = new HashMap<>();

    public static void add(String identifier) {
        if(includes.containsKey(identifier)) {
            includes.put(
                identifier,
                includes.get(identifier) + 1
            );
        } else {
            includes.put(identifier, 1);
        }
    }

    public static Set<Entry<String, Integer>> getIncludes() {
        return includes.entrySet();
    }
}
