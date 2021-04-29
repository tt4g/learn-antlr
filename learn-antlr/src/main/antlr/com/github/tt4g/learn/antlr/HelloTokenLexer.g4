// https://github.com/antlr/antlr4/blob/4.9.2/doc/getting-started.md

lexer grammar HelloTokenLexer;

ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;
