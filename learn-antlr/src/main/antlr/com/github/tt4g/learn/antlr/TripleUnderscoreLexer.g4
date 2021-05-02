/**
 * Parse ___${IDENTIFIER}___ pattern.
 */

lexer grammar TripleUnderscoreLexer;

@header {
package com.github.tt4g.learn.antlr;
}

PUSH_TRIPLE_UNDERSCORE: TRIPLE_UNDERSCORE -> mode(IN_TRIPLE_UNDERSCORE_MODE) ;
fragment TRIPLE_UNDERSCORE: UNDERSCORE UNDERSCORE UNDERSCORE;
fragment UNDERSCORE: '_' ;
WS : [ \t\r\n]+ -> skip ;
IGNORE: . -> skip ;

mode IN_TRIPLE_UNDERSCORE_MODE;
IDENTIFIER: [a-z0-9]+ ;
POP_TRIPLE_UNDERSCORE: TRIPLE_UNDERSCORE -> mode(DEFAULT_MODE) ;
// Ignore other pattern.
NOT_IDENTIFIER: . -> mode(DEFAULT_MODE) ;
