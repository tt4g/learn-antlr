package com.github.tt4g.learn.antlr;

import java.io.Serial;

/**
 * Report parsing error.
 */
public class ParsingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2089531637601888320L;

    ParsingException(String message) {
        super(message);
    }

    ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

}
