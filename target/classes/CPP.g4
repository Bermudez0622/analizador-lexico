grammar CPP;

program : (declaration | function_definition)+;

declaration : type_specifier declarator ';' ;

type_specifier : 'int' | 'char' | 'float' | 'double' ;

declarator : ID | '*' declarator ;

function_definition : type_specifier declarator '(' parameter_list ')' compound_statement ;

parameter_list : (type_specifier declarator (',' type_specifier declarator)*)? ;

compound_statement : '{' statement* '}' ;

statement : expression_statement | compound_statement | selection_statement | iteration_statement ;

selection_statement : 'if' '(' boolean_expression ')' compound_statement ;

iteration_statement : 'for' '(' expression_statement boolean_expression ';' expression ')' ;

expression_statement : expression? ';' ;

expression : assignment_expression ;

assignment_expression : additive_expression ('=' additive_expression)* ;

additive_expression : multiplicative_expression (('+'|'-') multiplicative_expression)* ;

multiplicative_expression : unary_expression ((''|'/'|'%') unary_expression) ;

unary_expression : ('+'|'-'|'!')? primary_expression ;

boolean_expression : additive_expression? | additive_expression ('<' | '>' | '>=' | '<=' | '==' | '!=') additive_expression ;

primary_expression : ID | INT ;

ID : [a-zA-Z_][a-zA-Z_0-9]* ;

INT : [0-9]+ ;