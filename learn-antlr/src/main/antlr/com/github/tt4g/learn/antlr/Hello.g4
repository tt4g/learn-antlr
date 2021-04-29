// https://github.com/antlr/antlr4/blob/4.9.2/doc/getting-started.md

grammar Hello;
import HelloTokenLexer;

@header {
package com.github.tt4g.learn.antlr;
}

r : 'hello' ID ;
