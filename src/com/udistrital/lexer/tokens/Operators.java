package com.udistrital.lexer.tokens;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Operators {

    private Operators() {}

    protected static final Map<String, Integer> arithmetics = new HashMap<>();
    protected static final Map<String, Integer> relational = new HashMap<>();
    protected static final Map<String, Integer> logical = new HashMap<>();
    protected static final Map<String, Integer> special = new HashMap<>();
    protected static final Map<String, Integer> delimiters = new LinkedHashMap<>();

    protected static final Set<String> operators = new LinkedHashSet<>();

    static {
        arithmetics.put("+", 0);
        arithmetics.put("-", 0);
        arithmetics.put("*", 0);
        arithmetics.put("/", 0);
        arithmetics.put("%", 0);

        relational.put("==", 0);
        relational.put(">", 0);
        relational.put("<", 0);
        relational.put("<=", 0);
        relational.put("!=", 0);

        logical.put("&&", 0);
        logical.put("||", 0);

        special.put("!", 0);
        special.put("++", 0);
        special.put("--", 0);

        delimiters.put("(", 0);
        delimiters.put(")", 0);
        delimiters.put("{", 0);
        delimiters.put("}", 0);
        delimiters.put("[", 0);
        delimiters.put("]", 0);
        delimiters.put("<", 0);
        delimiters.put(">", 0);
        delimiters.put("\"", 0);
        delimiters.put("'", 0);

        operators.addAll(arithmetics.keySet());
        operators.addAll(relational.keySet());
        operators.addAll(logical.keySet());
        operators.addAll(special.keySet());
        operators.addAll(delimiters.keySet());
    }

    public static boolean containsOperator(String line) {

        for(String character : line.split("")) {
            if(operators.contains(character)) {
                return true;
            }
        }

        return false;

    }

    public static boolean isOperator(String token) {
        return arithmetics.containsKey(token) ||
                relational.containsKey(token) ||
                   logical.containsKey(token) ||
                   special.containsKey(token) ||
                delimiters.containsKey(token);
    }

    public static boolean isArithmetic(String token) {
        return arithmetics.containsKey(token);
    }

    public static boolean isRelational(String token) {
        return relational.containsKey(token);
    }

    public static boolean isLogical(String token) {
        return logical.containsKey(token);
    }

    public static boolean isSpecial(String token) {
        return special.containsKey(token);
    }

    public static boolean isDelimiter(String token) {
        return delimiters.containsKey(token);
    }

    public static boolean isOpenDelimiter(String token) {
        int index = Arrays.asList(delimiters.keySet().toArray()).indexOf(token);
        return 0 <= index && index <= 7 && index % 2 == 0;
    }

    public static boolean isCloseDelimiter(String token) {
        int index = Arrays.asList(delimiters.keySet().toArray()).indexOf(token);
        return 0 <= index && index <= 7 && index % 2 != 0;
    }

    public static boolean correspond(String openToken, String closeToken) {
        int openIndex = Arrays.asList(delimiters.keySet().toArray()).indexOf(openToken);
        int closeIndex = Arrays.asList(delimiters.keySet().toArray()).indexOf(closeToken);

        return closeIndex - openIndex == 1;
    }
    
}
