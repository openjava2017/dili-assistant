package com.diligrp.assistant.uid.pattern;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.uid.domain.SequenceKey;
import com.diligrp.assistant.uid.exception.UidException;

import java.time.format.DateTimeFormatter;

public class DateConverter extends Converter<SequenceKey> {
    private static final String DEFAULT_FORMAT = "yyyyMMdd";

    private final String format;

    public DateConverter(String format) {
        if (format != null) {
            try {
                DateTimeFormatter.ofPattern(format);
            } catch (Exception ex) {
                throw new UidException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Invalid date format");
            }
            this.format = format;
        } else {
            this.format = DEFAULT_FORMAT;
        }
    }

    @Override
    public String convert(SequenceKey context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return context.getWhen().format(formatter);
    }
}
