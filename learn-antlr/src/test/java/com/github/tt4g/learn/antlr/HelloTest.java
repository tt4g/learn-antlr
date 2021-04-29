package com.github.tt4g.learn.antlr;

import java.util.ArrayList;
import java.util.List;

import com.github.tt4g.learn.antlr.HelloParser.RContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloTest {

    @Test
    public void helloWorld() {
        HelloLexer helloLexer = createHelloLexer("hello world");

        HelloTestListener helloTestListener = walkHello(helloLexer);

        assertThat(helloTestListener.rTexts)
            .containsExactly("hello", "world");
        assertThat(helloTestListener.idsTexts)
            .containsExactly("world");
        assertThat(helloTestListener.errorNodeTexts)
            .isEmpty();
    }

    @Test
    public void helloWorldWhenMissingHello() {
        HelloLexer helloLexer = createHelloLexer("el WORLD");

        HelloTestListener helloTestListener = walkHello(helloLexer);

        assertThat(helloTestListener.rTexts)
            .containsExactly("<missing 'hello'>", "el");
        assertThat(helloTestListener.idsTexts)
            .containsOnly("el");
        assertThat(helloTestListener.errorNodeTexts)
            .containsOnly("<missing 'hello'>");
    }

    @Test
    public void helloWorldWhenMissingID() {
        HelloLexer helloLexer = createHelloLexer("hello WORLD");

        HelloTestListener helloTestListener = walkHello(helloLexer);

        assertThat(helloTestListener.rTexts)
            .containsExactly("hello", "<missing ID>");
        assertThat(helloTestListener.idsTexts)
            .containsExactly("<missing ID>");
        assertThat(helloTestListener.errorNodeTexts)
            .containsExactly("<missing ID>");
    }

    private HelloLexer createHelloLexer(String input) {
        CharStream inputStream = CharStreams.fromString(input);
        HelloLexer helloLexer = new HelloLexer(inputStream);

        // Remove default error listeners.
        helloLexer.removeErrorListeners();

        return helloLexer;
    }

    private HelloTestListener walkHello(HelloLexer helloLexer) {
        CommonTokenStream commonTokenStream = new CommonTokenStream(helloLexer);

        HelloParser helloParser = new HelloParser(commonTokenStream);
        HelloTestListener helloTestListener = new HelloTestListener();

        ParseTreeWalker.DEFAULT.walk(helloTestListener, helloParser.r());

        return helloTestListener;
    }

    private static class HelloTestListener implements HelloListener {

        private List<String> rTexts = new ArrayList<>();

        private List<String> idsTexts = new ArrayList<>();

        private List<String> errorNodeTexts = new ArrayList<>();

        @Override
        public void enterR(RContext ctx) {
            collectTreeText(ctx, this.rTexts);
            this.idsTexts.add(ctx.ID().getText());
        }

        @Override
        public void exitR(RContext ctx) {

        }

        @Override
        public void visitTerminal(TerminalNode node) {

        }

        @Override
        public void visitErrorNode(ErrorNode node) {
            collectTreeText(node, this.errorNodeTexts);
        }

        @Override
        public void enterEveryRule(ParserRuleContext ctx) {

        }

        @Override
        public void exitEveryRule(ParserRuleContext ctx) {

        }

        private void collectTreeText(ParseTree tree, List<String> accumulator) {
            final int childCount = tree.getChildCount();

            if (childCount == 0) {
                String treeText = tree.getText();

                if (!treeText.isEmpty()) {
                    accumulator.add(treeText);
                }

                return;
            }

            for (int index = 0; index < childCount; index++) {
                ParseTree child = tree.getChild(index);

                accumulator.add(child.getText());
            }
        }

    }

}
