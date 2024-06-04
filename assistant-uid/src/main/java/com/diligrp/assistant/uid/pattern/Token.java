package com.diligrp.assistant.uid.pattern;

import com.diligrp.assistant.uid.domain.SequenceKey;

public abstract class Token {
    protected final String token;

    protected String option;

    public Token(String token) {
        this.token = token;
    }

    public void setOption(String option) {
        this.option = option;
    }

    abstract Converter<SequenceKey> getConverter();
}
