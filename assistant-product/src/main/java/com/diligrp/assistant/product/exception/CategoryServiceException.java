package com.diligrp.assistant.product.exception;

import com.diligrp.assistant.shared.exception.PlatformServiceException;

public class CategoryServiceException extends PlatformServiceException {
    public CategoryServiceException(String message) {
        super(message);
    }

    public CategoryServiceException(int code, String message) {
        super(code, message);
    }

    public CategoryServiceException(String message, Throwable ex) {
        super(message, ex);
    }
}
