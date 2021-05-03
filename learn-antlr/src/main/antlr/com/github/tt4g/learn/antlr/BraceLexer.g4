/**
 * Parse "{{" ${IDENTIFIER} "}}" pattern.
 */

lexer grammar BraceLexer;

import BraceTokenLexer;

@header {
package com.github.tt4g.learn.antlr;
}

// Ignore other pattern.
IGNORE: . -> skip ;

