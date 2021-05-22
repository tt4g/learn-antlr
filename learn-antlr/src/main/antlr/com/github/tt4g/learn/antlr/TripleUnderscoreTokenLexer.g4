/**
 * Parse "___" ${IDENTIFIER} "___" pattern.
 */

lexer grammar TripleUnderscoreTokenLexer;

TRIPLE_UNDERSCORE_IDENTIFIER : TRIPLE_UNDERSCORE [a-z0-9]+ TRIPLE_UNDERSCORE ;
fragment TRIPLE_UNDERSCORE : UNDERSCORE UNDERSCORE UNDERSCORE;
fragment UNDERSCORE: '_' ;
