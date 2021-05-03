package com.github.tt4g.learn.antlr;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 * Parse <code>"{{"${IDENTIFIER}"}}"</code>.<br>
 */
public class BraceParser {

    @NonNull
    public List<String> parse(@NonNull String input) {
        BraceLexer braceLexer =
            createBraceLexer(CharStreams.fromString(input));

        List<String> identifiers = new ArrayList<>();
        for (Token token = braceLexer.nextToken();
             token.getType() != Token.EOF;
             token = braceLexer.nextToken()) {

            switch (token.getType()) {
                case BraceLexer.BRACES_IDENTIFIER:
                    identifiers.add(parseIdentifier(token));
                    break;
            }
        }

        return identifiers;
    }

    @NonNull
    static String parseIdentifier(@NonNull Token token) {
        String text = token.getText();

        // Remove prefix ("{{") and suffix ("}}") because
        // `BraceLexer.BRACES_IDENTIFIER` matches prefix, identifier and suffix.
        return text.substring(2, text.length() - 2);
    }

    @NonNull
    private BraceLexer createBraceLexer(@NonNull CharStream charStream) {
        BraceLexer braceLexer = new BraceLexer(charStream);

        LexerUtils.removeDefaultErrorListener(braceLexer);
        braceLexer.addErrorListener(new BraceErrorListener());

        return braceLexer;
    }

}
