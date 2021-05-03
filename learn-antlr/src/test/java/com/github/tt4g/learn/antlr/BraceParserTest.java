package com.github.tt4g.learn.antlr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BraceParserTest {

    private BraceParser braceParser = new BraceParser();


    @Test
    public void parseWhenSingleBracesThenParseIdentifier() {
        assertThat(this.braceParser.parse("{{foo}}"))
            .containsOnly("foo");
        assertThat(this.braceParser.parse("{{01foo23}}"))
            .containsOnly("01foo23");
        assertThat(this.braceParser.parse("{{0}}"))
            .containsOnly("0");

        assertThat(this.braceParser.parse("Hello, {{bar}}"))
            .containsOnly("bar");
        assertThat(this.braceParser.parse("Hello,\n{{bar}}!"))
            .containsOnly("bar");

        assertThat(this.braceParser.parse("{{ {{foo}} }}"))
            .containsOnly("foo");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "{{}}",
        "{[{{}}}}",
        "{{FOO}}",
        "{{Foo}}",
        "{{fOO}}",
        "{{foo }}",
        "{{ foo}}",
        "{{ foo }}",
        "{{f o o}}",
        "{{ foo }}",
        "{ { foo } }",
        "{foo}",
        "{ {foo} }",
        "{{foo bar baz}}",
        "{{___❨╯°□°❩╯︵┻━┻___}}",
        "{{{{foo}{foo}",
        "{{foo\n}}",
        "{{\nfoo}}",
        "{{\nfoo\n}}",
        "{{foo\r\n}}",
        "{{\r\nfoo}}",
        "{{\r\nfoo\r\n}}"
    })
    public void parseWhenSingleInvalidBracesThenNotParsed(String input) {
        assertThat(this.braceParser.parse(input)).isEmpty();
    }

    @Test
    public void parseWhenRepeatBracesThenParseIdentifier() {
        assertThat(this.braceParser.parse("{{ {{foo}} }}"))
            .containsOnly("foo");
        assertThat(this.braceParser.parse("{{ {{ {{foo}} }} }}"))
            .containsOnly("foo");
        assertThat(this.braceParser.parse("{{ {{ foo }} }}"))
            .isEmpty();
        assertThat(this.braceParser.parse("{}{foo}}}"))
            .isEmpty();
        assertThat(this.braceParser.parse("{{{{}foo}}}"))
            .isEmpty();

        assertThat(this.braceParser.parse("{{foo}}{{bar}}"))
            .containsOnly("foo", "bar");
        assertThat(this.braceParser.parse("{{foo}}   {{bar}}"))
            .containsOnly("foo", "bar");
        assertThat(this.braceParser.parse("{{foo}}{{bar}}}}{{{{baz}}}}"))
            .containsOnly("foo", "bar", "baz");

        assertThat(this.braceParser.parse("{{ foo }} {{bar }} {{ baz}}"))
            .isEmpty();
        assertThat(this.braceParser.parse("{{foo}{{bar}}{{baz}"))
            .containsOnly("bar");
        assertThat(this.braceParser.parse("{{ foo}}{{bar }}"))
            .isEmpty();
        assertThat(this.braceParser.parse("foo}} {{bar baz}}"))
            .isEmpty();

        assertThat(this.braceParser.parse("{{\"foo\"}}"))
            .isEmpty();
    }

}
