/**
 * Parse "{{" ${IDENTIFIER} "}}" pattern.
 */

lexer grammar BraceTokenLexer;

BRACES_IDENTIFIER : '{{' [a-z0-9]+ '}}' ;
