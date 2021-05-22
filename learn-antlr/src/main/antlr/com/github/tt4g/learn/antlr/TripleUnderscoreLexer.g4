/**
 * Parse "___" ${IDENTIFIER} "___" pattern.
 */

lexer grammar TripleUnderscoreLexer;

import TripleUnderscoreTokenLexer;

// Ignore other pattern.
IGNORE : . -> skip ;
