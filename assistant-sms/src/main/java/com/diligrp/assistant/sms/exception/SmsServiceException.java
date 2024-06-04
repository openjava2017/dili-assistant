package com.diligrp.assistant.sms.exception;

import com.diligrp.assistant.shared.exception.PlatformServiceException;

public class SmsServiceException extends PlatformServiceException {
    public SmsServiceException(String message) {
        super(message);
    }

    public SmsServiceException(int code, String message) {
        super(code, message);
    }

    public SmsServiceException(String message, Throwable ex) {
        super(message, ex);
    }
}
