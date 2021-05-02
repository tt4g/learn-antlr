package com.github.tt4g.learn.antlr;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 * Parse <code>___${IDENTIFIER}___</code>.<br>
 */
public class TripleUnderscoreParser {

    @NonNull
    List<String> parse(@NonNull String input) {
        TripleUnderscoreLexer tripleUnderscoreLexer =
            createTripleUnderscoreLexer(CharStreams.fromString(input));

        List<String> identifiers = new ArrayList<>();
        for (Token token = tripleUnderscoreLexer.nextToken();
             token.getType() != Token.EOF;
             token = tripleUnderscoreLexer.nextToken()) {

            switch (token.getType()) {
                case TripleUnderscoreLexer.IDENTIFIER:
                    identifiers.add(token.getText());
                    break;
            }
        }

        return identifiers;
    }

    private TripleUnderscoreLexer createTripleUnderscoreLexer(
        @NonNull CharStream charStream) {

        TripleUnderscoreLexer tripleUnderscoreLexer =
            new TripleUnderscoreLexer(charStream);

        // Remove default listeners (`ConsoleErrorListener`).
        tripleUnderscoreLexer.removeErrorListeners();
        tripleUnderscoreLexer.addErrorListener(
            new TripleUnderscoreErrorListener());

        return tripleUnderscoreLexer;
    }

}
