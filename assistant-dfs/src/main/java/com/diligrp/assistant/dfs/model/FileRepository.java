package com.diligrp.assistant.dfs.model;

import com.diligrp.assistant.shared.domain.BaseDo;

import java.time.LocalDateTime;

public class FileRepository extends BaseDo {
    // 文件仓库
    private String repositoryId;
    // 名称
    private String name;
    // 服务通道
    private Integer pipeline;
    // 描述
    private String description;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Integer getPipeline() {
        return pipeline;
    }

    public void setPipeline(Integer pipeline) {
        this.pipeline = pipeline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Builder builder() {
        return new FileRepository().new Builder();
    }

    public class Builder {
        public Builder repositoryId(String repositoryId) {
            FileRepository.this.repositoryId = repositoryId;
            return this;
        }

        public Builder name(String name) {
            FileRepository.this.name = name;
            return this;
        }

        public Builder pipeline(int pipeline) {
            FileRepository.this.pipeline = pipeline;
            return this;
        }

        public Builder description(String description) {
            FileRepository.this.description = description;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            FileRepository.this.createdTime = createdTime;
            return this;
        }

        public FileRepository build() {
            return FileRepository.this;
        }
    }
}
