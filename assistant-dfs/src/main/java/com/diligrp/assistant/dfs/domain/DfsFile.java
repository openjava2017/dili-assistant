package com.diligrp.assistant.dfs.domain;

import com.diligrp.assistant.shared.util.RandomUtils;

import java.io.InputStream;

public class DfsFile {
    // 文件唯一标识
    private String id;
    // 文件名称
    private String name;
    // 文件流
    private InputStream stream;
    // option参数
    private FileMetadata metadata;

    public DfsFile(String name, InputStream stream, FileMetadata metadata) {
        this(RandomUtils.randomUUID(), name, stream, metadata);
    }

    public DfsFile(String id, String name, InputStream stream, FileMetadata metadata) {
        this.id = id;
        this.name = name;
        this.stream = stream;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InputStream getStream() {
        return stream;
    }

    public FileMetadata getMetadata() {
        return metadata;
    }
}
