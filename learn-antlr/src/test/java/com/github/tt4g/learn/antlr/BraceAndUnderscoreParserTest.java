package com.github.tt4g.learn.antlr;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BraceAndUnderscoreParserTest {

    private BraceAndUnderscoreParser braceAndUnderscoreParser =
        new BraceAndUnderscoreParser();

    @Test
    public void parseWhenAppearOnlyBraces() {
        assertThat(this.braceAndUnderscoreParser.parse("{{foo}}"))
            .containsExactly("foo");
    }

    @Test
    public void parseWhenAppearOnlyTripleUnderscore() {
        assertThat(this.braceAndUnderscoreParser.parse("___foo___"))
            .containsExactly("foo");
    }

    @Test
    public void parseWhenAppearBracesAndTripleUnderscore() {
        assertThat(
            this.braceAndUnderscoreParser.parse(
                "Hello, {{foo}}!\nI'm ___bar___!"))
            .containsExactly("foo", "bar");
        assertThat(
            this.braceAndUnderscoreParser.parse(
                "Hello, ___foo___!\r\nI'm {{bar}}!"))
            .containsExactly("foo", "bar");

        assertThat(this.braceAndUnderscoreParser.parse("___{{}}___"))
            .isEmpty();
        assertThat(this.braceAndUnderscoreParser.parse("{{______}}"))
            .isEmpty();

        assertThat(this.braceAndUnderscoreParser.parse("{{___foo___}}"))
            .containsExactly("foo");
        assertThat(this.braceAndUnderscoreParser.parse("{{ ___foo___ }}"))
            .containsExactly("foo");
        assertThat(this.braceAndUnderscoreParser.parse("{{___ foo ___}}"))
            .isEmpty();
        assertThat(this.braceAndUnderscoreParser.parse("___{{foo}}___"))
            .containsExactly("foo");
        assertThat(this.braceAndUnderscoreParser.parse("___ {{foo}} ___"))
            .containsExactly("foo");
        assertThat(this.braceAndUnderscoreParser.parse("___{{ foo }}___"))
            .isEmpty();
        assertThat(this.braceAndUnderscoreParser.parse("{{foo___bar___baz}}"))
            .containsExactly("bar");
        assertThat(this.braceAndUnderscoreParser.parse("___foo{{bar}}baz___"))
            .containsExactly("bar");
        assertThat(this.braceAndUnderscoreParser.parse("{{___{{foo}}___}}"))
            .containsExactly("foo");
        assertThat(this.braceAndUnderscoreParser.parse("___{{___foo___}}___"))
            .containsExactly("foo");

        assertThat(this.braceAndUnderscoreParser.parse("__{{_foo_}}__"))
            .isEmpty();
        assertThat(this.braceAndUnderscoreParser.parse("_{{__foo__}}_"))
            .isEmpty();
    }

}
