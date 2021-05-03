package com.github.tt4g.learn.antlr;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 * Parse <code>"___"${IDENTIFIER}"___"</code>.<br>
 */
public class TripleUnderscoreParser {

    @NonNull
    public List<String> parse(@NonNull String input) {
        TripleUnderscoreLexer tripleUnderscoreLexer =
            createTripleUnderscoreLexer(CharStreams.fromString(input));

        List<String> identifiers = new ArrayList<>();
        for (Token token = tripleUnderscoreLexer.nextToken();
             token.getType() != Token.EOF;
             token = tripleUnderscoreLexer.nextToken()) {

            switch (token.getType()) {
                case TripleUnderscoreLexer.TRIPLE_UNDERSCORE_IDENTIFIER:
                    identifiers.add(parseIdentifier(token));
                    break;
            }
        }

        return identifiers;
    }

    @NonNull
    private String parseIdentifier(@NonNull Token token) {
        String text = token.getText();

        // Remove prefix ("___") and suffix ("___") because
        // `TripleUnderscoreLexer.TRIPLE_UNDERSCORE_IDENTIFIER` matches
        // prefix, identifier and suffix.
        return text.substring(3, text.length() - 3);
    }

    @NonNull
    private TripleUnderscoreLexer createTripleUnderscoreLexer(
        @NonNull CharStream charStream) {

        TripleUnderscoreLexer tripleUnderscoreLexer =
            new TripleUnderscoreLexer(charStream);

        LexerUtils.removeDefaultErrorListener(tripleUnderscoreLexer);
        tripleUnderscoreLexer.addErrorListener(
            new TripleUnderscoreErrorListener());

        return tripleUnderscoreLexer;
    }

}
