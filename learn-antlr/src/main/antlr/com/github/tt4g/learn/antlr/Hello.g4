// https://github.com/antlr/antlr4/blob/4.9.2/doc/getting-started.md

grammar Hello;

@header {
package com.github.tt4g.learn.antlr;
}

r : 'hello' ID ;
ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;
