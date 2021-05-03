package com.github.tt4g.learn.antlr;

import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BraceAndUnderscoreErrorListener implements ANTLRErrorListener {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(BraceAndUnderscoreErrorListener.class);

    @Override
    public void syntaxError(
        Recognizer<?, ?> recognizer,
        Object offendingSymbol,
        int line,
        int charPositionInLine,
        String msg,
        RecognitionException e) {

        LOGGER.debug("Syntax error. line:pos={}:{}", line, charPositionInLine);

        throw new ParsingException(
            "Syntax error. line:pos=" + line + ':' + charPositionInLine,
            e);
    }

    @Override
    public void reportAmbiguity(
        Parser recognizer,
        DFA dfa,
        int startIndex,
        int stopIndex,
        boolean exact,
        BitSet ambigAlts,
        ATNConfigSet configs) {

        LOGGER.debug("Report ambiguity. startIndex={} stopIndex={}",
            startIndex,
            stopIndex);

        // This method is never called by Lexer. (it is called by the Parser).
        throw new ParsingException(
            "Report ambiguity, but this method is never called by Lexer.");
    }

    @Override
    public void reportAttemptingFullContext(
        Parser recognizer,
        DFA dfa,
        int startIndex,
        int stopIndex,
        BitSet conflictingAlts,
        ATNConfigSet configs) {

        LOGGER.debug("Report attempting. startIndex={} stopIndex={}",
            startIndex,
            stopIndex);

        // This method is never called by Lexer. (it is called by the Parser).
        throw new ParsingException(
            "Report attempting, but this method is never called by Lexer.");
    }

    @Override
    public void reportContextSensitivity(
        Parser recognizer,
        DFA dfa,
        int startIndex,
        int stopIndex,
        int prediction,
        ATNConfigSet configs) {

        LOGGER.debug("Report context sensitivity. startIndex={} stopIndex={}",
            startIndex,
            stopIndex);

        // This method is never called by Lexer. (it is called by the Parser).
        throw new ParsingException(
            "Report context sensitivity, but this method is never called by Lexer.");
    }
}
