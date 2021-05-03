/**
 * Parse ___${IDENTIFIER}___ pattern.
 */

lexer grammar TripleUnderscoreLexer;

@header {
package com.github.tt4g.learn.antlr;
}

TRIPLE_UNDERSCORE_IDENTIFIER: TRIPLE_UNDERSCORE [a-z0-9]+ TRIPLE_UNDERSCORE ;
fragment TRIPLE_UNDERSCORE: UNDERSCORE UNDERSCORE UNDERSCORE;
fragment UNDERSCORE: '_' ;
// Ignore other pattern.
IGNORE: . -> skip ;

