package com.diligrp.assistant.dfs.domain;

public class FileMetadata {
    // MimeType or MediaType
    private String mimeType;

    public FileMetadata(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}
