package com.udistrital.lexer.reviewer;

import java.util.regex.Pattern;

public class Reviewer {
    private Reviewer() {}

    public static Pattern include = Pattern.compile("#include\\s+\\b<.+>\\b|\\b\".+\"\\b");
}


/**
 * package com.udistrital.lexer.reviewer;

import java.util.regex.Pattern;

import com.udistrital.lexer.tokens.Preprocessors;

public class Reviewer {
    private Reviewer() {}

    public boolean isMacro(String line) {

        String macro = line.split(" ")[0];

        if(Preprocessors.isPreprocessor(macro)) {
            Preprocessors.add(macro);

            

            return true;
        }

        return false;

    }
}

 */