package com.diligrp.assistant.uid.pattern;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.uid.domain.SequenceKey;
import com.diligrp.assistant.uid.exception.UidException;

public class KeywordToken extends Token {
    public KeywordToken(String token) {
        super(token);
    }

    @Override
    Converter<SequenceKey> getConverter() {
        if ("d".equals(token) || "date".equals(token)) {
            return new DateConverter(option);
        } else if ("n".equals(token)) {
            return new SequenceConverter(option);
        } else {
            throw new UidException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Unrecognized keyword " + token);
        }
    }

    public String toString() {
        return String.format("keyword(%s)", token);
    }
}
