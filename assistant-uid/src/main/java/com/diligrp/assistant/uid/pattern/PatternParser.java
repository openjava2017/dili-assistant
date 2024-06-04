package com.diligrp.assistant.uid.pattern;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.uid.exception.UidException;

import java.util.ArrayList;
import java.util.List;

public class PatternParser {
    private final String pattern;
    private final int length;
    private TokenizerState state;
    private int index;

    public static void main(String[] args) {
        String pattern = "{Order}%d{yyMMdd%n2";
//        String pattern = "Order%d{yyMMdd}%n{2}";
        PatternParser tokenizer = new PatternParser(pattern);
        List<Token> tokens = tokenizer.parse();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public PatternParser(String pattern) {
        this.pattern = pattern;
        this.length = pattern.length();
        this.index = 0;
        this.state = TokenizerState.LITERAL_STATE;
    }

    public List<Token> parse() {
        List<Token> tokens = new ArrayList();
        StringBuffer buf = new StringBuffer();

        while(index < length) {
            char c = pattern.charAt(index);
            index ++;

            switch (this.state) {
                case LITERAL_STATE:
                    handleLiteralState(c, tokens, buf);
                    break;
                case KEYWORD_STATE:
                    handleKeywordState(c, tokens, buf);
                    break;
                case OPTION_STATE:
                    handleOptionState(c, tokens, buf);
                    break;
            }
        }

        switch (state) {
            case LITERAL_STATE:
                handleLiteralState('%', tokens, buf);
                break;
            case KEYWORD_STATE:
                handleKeywordState('%', tokens, buf);
                break;
            default:
                throw new UidException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Unexpected end of pattern string");
        }

        return tokens;
    }

    private void handleLiteralState(char c, List<Token> tokens, StringBuffer buf) {
        switch (c) {
            case '%':
                addLiteralToken(buf, tokens);
                state = TokenizerState.KEYWORD_STATE;
                break;
            default:
                buf.append(c);
        }
    }

    private void handleKeywordState(char c, List<Token> tokens, StringBuffer buf) {
        switch (c) {
            case '%':
                addKeywordToken(buf, tokens);
                state = TokenizerState.KEYWORD_STATE;
                break;
            case '{':
                this.addKeywordToken(buf, tokens);
                this.state = TokenizerState.OPTION_STATE;
                break;
            default:
                buf.append(c);
        }
    }

    private void handleOptionState(char c, List<Token> tokens, StringBuffer buf) {
        switch (c) {
            case '}':
                addOptionToken(buf, tokens);
                state = TokenizerState.LITERAL_STATE;
                break;
            default:
                buf.append(c);
        }
    }

    private void addLiteralToken(StringBuffer buf, List<Token> tokens) {
        if (buf.length() > 0) {
            tokens.add(new LiteralToken(buf.toString()));
            buf.setLength(0);
        }
    }

    private void addKeywordToken(StringBuffer buf, List<Token> tokens) {
        if (buf.length() > 0) {
            tokens.add(new KeywordToken(buf.toString()));
            buf.setLength(0);
        }
    }

    private void addOptionToken(StringBuffer buf, List<Token> tokens) {
        if (buf.length() > 0) {
            tokens.add(new OptionToken(buf.toString()));
            buf.setLength(0);
        }
    }

    private enum TokenizerState {
        LITERAL_STATE,
        KEYWORD_STATE,
        OPTION_STATE
    }
}
