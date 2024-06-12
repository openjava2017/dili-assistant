package com.diligrp.assistant.shared.exception;

import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(PlatformServiceException.class)
    public Message<?> platformServiceException(PlatformServiceException ex) {
        LOG.warn("assistant platform service exception", ex);
        return Message.failure(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Message<?> illegalArgumentException(IllegalArgumentException ex) {
        LOG.warn("assistant platform service exception", ex);
        return Message.failure(ErrorCode.ILLEGAL_ARGUMENT_ERROR, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Message<?> defaultExceptionHandler(Exception ex) {
        LOG.warn("assistant platform service exception", ex);
        return Message.failure(ErrorCode.SYSTEM_UNKNOWN_ERROR, ErrorCode.MESSAGE_UNKNOWN_ERROR);
    }
}
