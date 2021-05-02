package com.github.tt4g.learn.antlr;

import java.io.Serial;

/**
 * Report triple-underscore parsing error.
 */
public class TripleUnderscoreParsingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4061399573451764039L;

    TripleUnderscoreParsingException(String message) {
        super(message);
    }

    TripleUnderscoreParsingException(String message, Throwable cause) {
        super(message, cause);
    }

}
