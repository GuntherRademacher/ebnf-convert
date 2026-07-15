grammar AssignStmt;

stmt
    : id '=' expr ';'
    ;

expr
    : id '+' number
    ;

id
    : 'a'
    | 'b'
    | 'x'
    | 'y'
    ;

number
    : '1'
    | '2'
    | '3'
    ;