package com.github.tt4g.learn.antlr;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 * Parse <code>"{{"${IDENTIFIER}"}}"</code> and <code>"___"${IDENTIFIER}"___"</code>.<br>
 */
public class BraceAndUnderscoreParser {

    @NonNull
    public List<String> parse(@NonNull String input) {
        BraceAndUnderscoreLexer braceAndUnderscoreLexer =
            createBraceAndUnderscoreLexer(CharStreams.fromString(input));

        List<String> identifiers = new ArrayList<>();
        for (Token token = braceAndUnderscoreLexer.nextToken();
             token.getType() != Token.EOF;
             token = braceAndUnderscoreLexer.nextToken()) {

            switch (token.getType()) {
                case BraceAndUnderscoreLexer.BRACES_IDENTIFIER:
                    identifiers.add(BraceParser.parseIdentifier(token));
                    break;

                case BraceAndUnderscoreLexer.TRIPLE_UNDERSCORE_IDENTIFIER:
                    identifiers.add(TripleUnderscoreParser.parseIdentifier(token));
                    break;
            }
        }

        return identifiers;
    }

    @NonNull
    private BraceAndUnderscoreLexer createBraceAndUnderscoreLexer(@NonNull CharStream charStream) {
        BraceAndUnderscoreLexer braceAndUnderscoreLexer =
            new BraceAndUnderscoreLexer(charStream);

        LexerUtils.removeDefaultErrorListener(braceAndUnderscoreLexer);
        braceAndUnderscoreLexer.addErrorListener(new BraceAndUnderscoreErrorListener());

        return braceAndUnderscoreLexer;
    }


}
