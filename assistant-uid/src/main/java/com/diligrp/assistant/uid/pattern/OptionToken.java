package com.diligrp.assistant.uid.pattern;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.uid.domain.SequenceKey;
import com.diligrp.assistant.uid.exception.UidException;

public class OptionToken extends Token {
    public OptionToken(String token) {
        super(token);
    }

    public String getToken() {
        return this.token;
    }

    @Override
    Converter<SequenceKey> getConverter() {
        throw new UidException(ErrorCode.OPERATION_NOT_ALLOWED, "Not supported converter");
    }

    public String toString() {
        return String.format("option(%s)", token);
    }
}
