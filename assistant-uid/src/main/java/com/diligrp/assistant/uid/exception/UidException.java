package com.diligrp.assistant.uid.exception;

import com.diligrp.assistant.shared.exception.PlatformServiceException;

public class UidException extends PlatformServiceException {
    public UidException(String message) {
        super(message);
    }

    public UidException(int code, String message) {
        super(code, message);
    }

    public UidException(String message, Throwable ex) {
        super(message, ex);
    }
}
