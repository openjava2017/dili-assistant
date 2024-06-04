package com.diligrp.assistant.dfs.exception;

import com.diligrp.assistant.shared.exception.PlatformServiceException;

public class DfsServiceException extends PlatformServiceException {
    public DfsServiceException(String message) {
        super(message);
    }

    public DfsServiceException(int code, String message) {
        super(code, message);
    }

    public DfsServiceException(String message, Throwable ex) {
        super(message, ex);
    }
}
