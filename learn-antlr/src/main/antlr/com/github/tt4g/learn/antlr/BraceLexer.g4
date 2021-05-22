/**
 * Parse "{{" ${IDENTIFIER} "}}" pattern.
 */

lexer grammar BraceLexer;

import BraceTokenLexer;

// Ignore other pattern.
IGNORE : . -> skip ;

