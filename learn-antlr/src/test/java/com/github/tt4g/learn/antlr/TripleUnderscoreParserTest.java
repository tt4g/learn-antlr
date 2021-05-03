package com.github.tt4g.learn.antlr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TripleUnderscoreParserTest {

    private TripleUnderscoreParser tripleUnderscoreParser =
        new TripleUnderscoreParser();

    @Test
    public void parseWhenSingleTripleUnderScoreThenParsedIdentifier() {
        assertThat(this.tripleUnderscoreParser.parse("___foo0123___"))
            .containsExactly("foo0123");
        assertThat(this.tripleUnderscoreParser.parse("Hello, \n___bar___\n  World!"))
            .containsExactly("bar");
        assertThat(this.tripleUnderscoreParser.parse("Hello, \r\n___0___\r\n  World!"))
            .containsExactly("0");
        assertThat(this.tripleUnderscoreParser.parse("___ ___foo___ ___"))
            .containsExactly("foo");
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
        "__ _foo_ __",
        "__ _ foo _ __,",
        "_ __foo__ _",
        "_ __ foo __ _",
        "_ _ _foo_ _ _",
        "_ _ _ foo _ _ _",
        "___ _foo_ ___",
        "___foo bar baz___",
        "___❨╯°□°❩╯︵┻━┻___",
        "______foo__ ___foo__",
        "___foo\n___",
        "___\nfoo___",
        "___\nfoo\n___",
        "___foo\r\n___",
        "___\r\nfoo___",
        "___\r\nfoo\r\n___"
    })
    public void parseWhenSingleInvalidTripleUnderScoreThenNotParsed(String input) {
        assertThat(this.tripleUnderscoreParser.parse(input))
            .isEmpty();
    }

    @Test
    public void parseWhenRepeatTripleUnderScore() {
        assertThat(this.tripleUnderscoreParser.parse("___ ___foo___ ___"))
            .containsExactly("foo");
        assertThat(this.tripleUnderscoreParser.parse("___ ___ ___foo___ ___ ___"))
            .containsExactly("foo");
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
            .containsExactly("bar");

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
            .containsExactly("bar");
        assertThat(this.tripleUnderscoreParser.parse("foo___bar____baz___"))
            .containsExactly("bar");
        assertThat(this.tripleUnderscoreParser.parse("foo___bar_____baz___"))
            .containsExactly("bar");
        assertThat(this.tripleUnderscoreParser.parse("foo___bar______baz___"))
            .containsExactly("bar", "baz");
        assertThat(this.tripleUnderscoreParser.parse("___foo___bar______baz___"))
            .containsExactly("foo", "baz");
        assertThat(this.tripleUnderscoreParser.parse("___foo______bar___baz___"))
            .containsExactly("foo", "bar");
    }

}
