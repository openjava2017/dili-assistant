package com.diligrp.assistant.uid.pattern;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.uid.domain.SequenceKey;
import com.diligrp.assistant.uid.exception.UidException;

public class SequenceConverter extends Converter<SequenceKey> {
    private static final int DEFAULT_LENGTH = 4;

    private final int minLength;

    public SequenceConverter(String minLength) {
        if (minLength != null) {
            try {
                this.minLength = Integer.parseInt(minLength);
            } catch (Exception ex) {
                throw new UidException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Invalid minLength for service");
            }
        } else {
            this.minLength = DEFAULT_LENGTH;
        }
    }

    @Override
    public String convert(SequenceKey context) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(context.getSequence());
        int length = buffer.length();
        if (length < minLength) {
            for (int i = length; i < minLength; i++) {
                buffer.insert(0, "0");
            }
        }
        return buffer.toString();
    }
}
