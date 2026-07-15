grammar Block;

block
    : stmt
    | '{' stmts '}'
    ;

stmts
    :
    | stmt stmts
    ;

stmt
    : expr ';'
    | 'if' '(' expr ')' block
    | 'while' '(' expr ')' block
    | 'do' block 'while' '(' expr ')' ';'
    | block
    ;

expr
    : ID
    | CONST
    | expr '+' expr
    | expr '-' expr
    | expr '*' expr
    ;

ID
    : [a-zA-Z] [a-zA-Z0-9]*
    ;

CONST
    : [0-9]+
    ;

WS
    : [ \t\r\n]+ -> skip
    ;