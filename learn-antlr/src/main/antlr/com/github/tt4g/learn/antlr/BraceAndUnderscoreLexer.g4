/**
 * Parse "{{" ${IDENTIFIER} "}}" pattern and "___" ${IDENTIFIER} "___" pattern.
 */

lexer grammar BraceAndUnderscoreLexer;

import BraceTokenLexer, TripleUnderscoreTokenLexer;

// Ignore other pattern.
IGNORE : . -> skip ;
