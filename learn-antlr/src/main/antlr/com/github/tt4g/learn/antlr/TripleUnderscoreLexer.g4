/**
 * Parse "___" ${IDENTIFIER} "___" pattern.
 */

lexer grammar TripleUnderscoreLexer;

import TripleUnderscoreTokenLexer;

@header {
package com.github.tt4g.learn.antlr;
}

// Ignore other pattern.
IGNORE: . -> skip ;

