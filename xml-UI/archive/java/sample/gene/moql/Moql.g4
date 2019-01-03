/*
* Monitored Object Query Language
*/
grammar Moql;

parse
 : ( moql_stmt_list | error )* EOF
 ;

error
 : UNEXPECTED_CHAR
   {
     throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text);
   }
 ;

moql_stmt_list
 : ';'* moql_stmt ( ';'+ moql_stmt )* ';'*
 ;

moql_stmt
 : ( query_stmt
      | update_stmt
      | delete_stmt )
 ;

query_stmt
 : K_QUERY object_name ( ',' object_name )*
   K_IN class_name
   K_FROM target_vm
   K_WHERE expr
 ;

update_stmt
 : K_UPDATE
 ;

delete_stmt
 : K_DELETE
 ;

expr
 : K_LINE '==' NUMERIC_LITERAL
 ;

target_vm
 : any_name ':' port
 ;

port
 : DIGIT+
 ;

class_name
 : STRING_LITERAL
 ;

object_name
 : var_name
 | field_name
 ;

var_name
 : any_name
 ;

field_name
 : any_name
 ;

any_name
 : IDENTIFIER
 | keyword
 | STRING_LITERAL
 | '(' any_name ')'
 ;

keyword
 : K_QUERY
 | K_UPDATE
 | K_DELETE
 | K_FROM
 | K_WHERE
 | K_IN
 | K_SET
 ;

K_QUERY : Q U E R Y;
K_UPDATE : U P D A T E;
K_DELETE : D E L E T E;
K_FROM  : F R O M;
K_WHERE : W H E R E;
K_IN    : I N;
K_LINE    : L I N E;
K_SET   : S E T;

IDENTIFIER
 : '"' (~'"' | '""')* '"'
 | '`' (~'`' | '``')* '`'
 | '[' ~']'* ']'
 | [a-zA-Z_] [a-zA-Z_0-9]* // TODO check: needs more chars in set
 ;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

UNEXPECTED_CHAR
 : .
 ;

fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];