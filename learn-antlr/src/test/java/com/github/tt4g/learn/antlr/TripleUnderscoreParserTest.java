package com.github.tt4g.learn.antlr;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TripleUnderscoreParserTest {

    private TripleUnderscoreParser tripleUnderscoreParser =
        new TripleUnderscoreParser();

    @Test
    public void parseWhenSingleValidTripleUnderScoreThenParsedIdentifier() {
        String input = "___foobarbaz___";

        assertThat(this.tripleUnderscoreParser.parse(input))
            .containsOnly("foobarbaz");
    }

}
