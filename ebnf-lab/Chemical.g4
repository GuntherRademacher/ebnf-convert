grammar Chemical;

form
    : cmp
    | cmp ion
    ;

cmp
    : term
    | term num
    | cmp cmp
    ;

term
    : elem
    | '(' cmp ')'
    ;

elem
    : 'H'
    | 'He'
    | 'Li'
    | 'Be'
    | 'B'
    | 'C'
    ;

ion
    : '+'
    | '-'
    | ionNum '+'
    | ionNum '-'
    ;

ionNum
    : '2'
    | '3'
    | '4'
    ;

num
    : '1'
    | ionNum
    ;