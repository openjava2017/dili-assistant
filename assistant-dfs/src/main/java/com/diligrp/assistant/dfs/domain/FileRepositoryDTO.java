package com.diligrp.assistant.dfs.domain;

public class FileRepositoryDTO {
    // 名称
    private String name;
    // 文件存储通道
    private Integer pipeline;
    // 描述
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPipeline() {
        return pipeline;
    }

    public void setPipeline(Integer pipeline) {
        this.pipeline = pipeline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
