package com.udistrital.lexer.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import com.udistrital.lexer.tokens.Operators;

public class Delimiters {

    private Delimiters() {}

    protected static final Deque<String> stack = new ArrayDeque<>();

    public static boolean add(String token) {
        if(!Operators.isDelimiter(token)) {
            return false;
        }

        if(Operators.isCloseDelimiter(token) && Objects.isNull(stack.peek())) {
            return false;
        }

        if(Operators.isCloseDelimiter(token) && !Operators.correspond(token, stack.peek())) {
            return false;
        }

        if(Operators.correspond(token, stack.peek())) {
            stack.pop();
            return true;
        }

        stack.add(token);

        return true;
    }
    
}
