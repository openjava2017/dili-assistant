package com.diligrp.assistant.data.exception;

import com.diligrp.assistant.shared.exception.PlatformServiceException;

public class DataAccessException extends PlatformServiceException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(int code, String message) {
        super(code, message);
    }

    public DataAccessException(String message, Throwable ex) {
        super(message, ex);
    }
}
