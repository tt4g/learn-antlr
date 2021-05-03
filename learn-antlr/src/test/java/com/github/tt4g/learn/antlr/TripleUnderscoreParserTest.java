package com.github.tt4g.learn.antlr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TripleUnderscoreParserTest {

    private TripleUnderscoreParser tripleUnderscoreParser =
        new TripleUnderscoreParser();

    @Test
    public void parseWhenSingleValidTripleUnderScoreThenParsedIdentifier() {
        assertThat(this.tripleUnderscoreParser.parse("___foo0123___"))
            .containsOnly("foo0123");
        assertThat(this.tripleUnderscoreParser.parse("Hello, \n___bar___\n  World!"))
            .containsOnly("bar");
        assertThat(this.tripleUnderscoreParser.parse("Hello, \r\n___0___\r\n  World!"))
            .containsOnly("0");
        assertThat(this.tripleUnderscoreParser.parse("___ ___foo___ ___"))
            .containsOnly("foo");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "______",
        "______ ______",
        "___FOO___",
        "___Foo___",
        "___fOO___",
        "___foo ___",
        "___ foo___",
        "___ f o o___",
        "___foo bar baz___",
        "___❨╯°□°❩╯︵┻━┻___",
        "______foo__ ___foo__",
    })
    public void parseWhenSingleInvalidTripleUnderScoreThenNotParsed(String input) {
        assertThat(this.tripleUnderscoreParser.parse(input))
            .isEmpty();
    }

    @Test
    public void parseWhenRepeatTripleUnderScore() {
        assertThat(this.tripleUnderscoreParser.parse("__ _foo_ __"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("__ _ foo _ __"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("_ __foo__ _"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("_ __ foo __ _"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("_ _ _foo_ _ _"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("_ _ _ foo _ _ _"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ _foo_ ___"))
            .isEmpty();

        assertThat(this.tripleUnderscoreParser.parse("___ ___foo___ ___"))
            .containsOnly("foo");
        assertThat(this.tripleUnderscoreParser.parse("___ ___ ___foo___ ___ ___"))
            .containsOnly("foo");
        assertThat(this.tripleUnderscoreParser.parse("___ ___ foo ___ ___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ ___ ___ foo ___ ___ ___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ ___ ______ foo ______ ___ ___"))
            .isEmpty();

        assertThat(this.tripleUnderscoreParser.parse("___foo___ ___bar___"))
            .containsExactly("foo", "bar");
        assertThat(this.tripleUnderscoreParser.parse("___foo______bar______baz___"))
            .containsExactly("foo", "bar", "baz");
        assertThat(this.tripleUnderscoreParser.parse("______foo______bar______baz______"))
            .containsExactly("foo", "bar", "baz");

        assertThat(this.tripleUnderscoreParser.parse("__foo______foo__"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse(
            "______ foo ______ ______ foo ______ ______ foo ______"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ foo ___bar___ qux___"))
            .containsOnly("bar");

        assertThat(this.tripleUnderscoreParser.parse("___ foo___  ___bar ___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___foo ___  ___bar ___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ foo___  ___ bar___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ foo___ bar___ baz___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___foo ___bar ___baz"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___foo ___bar ___baz ___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("foo___ bar___ baz___"))
            .isEmpty();
        assertThat(this.tripleUnderscoreParser.parse("___ foo___ bar___ baz___"))
            .isEmpty();

        assertThat(this.tripleUnderscoreParser.parse("___\"foo\"___"))
            .isEmpty();

        assertThat(this.tripleUnderscoreParser.parse("foo___bar___baz___"))
            .containsOnly("bar");
        assertThat(this.tripleUnderscoreParser.parse("foo___bar____baz___"))
            .containsOnly("bar");
        assertThat(this.tripleUnderscoreParser.parse("foo___bar_____baz___"))
            .containsOnly("bar");
        assertThat(this.tripleUnderscoreParser.parse("foo___bar______baz___"))
            .containsOnly("bar", "baz");
        assertThat(this.tripleUnderscoreParser.parse("___foo___bar______baz___"))
            .containsOnly("foo", "baz");
        assertThat(this.tripleUnderscoreParser.parse("___foo______bar___baz___"))
            .containsOnly("foo", "bar");
    }

}
