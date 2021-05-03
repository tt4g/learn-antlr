/**
 * Parse "{{" ${IDENTIFIER} "}}" pattern and "___" ${IDENTIFIER} "___" pattern.
 */

lexer grammar BraceAndUnderscoreLexer;

import BraceTokenLexer, TripleUnderscoreTokenLexer;

@header {
package com.github.tt4g.learn.antlr;
}

// Ignore other pattern.
IGNORE: . -> skip ;
