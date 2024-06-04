package com.diligrp.assistant.dfs.model;

import com.diligrp.assistant.shared.domain.BaseDo;

import java.time.LocalDateTime;

public class FileObject extends BaseDo {
    // 仓库ID
    private String repositoryId;
    // 服务通道
    private Integer pipeline;
    // 文件ID
    private String fileId;
    // 文件名称
    private String fileName;
    // MIME类型
    private String mimeType;
    // 访问次数
    private Integer hits;
    // 文件状态
    private Integer state;

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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public static Builder builder() {
        return new FileObject().new Builder();
    }

    public class Builder {
        public Builder repositoryId(String repositoryId) {
            FileObject.this.repositoryId = repositoryId;
            return this;
        }

        public Builder pipeline(int pipeline) {
            FileObject.this.pipeline = pipeline;
            return this;
        }

        public Builder fileId(String fileId) {
            FileObject.this.fileId = fileId;
            return this;
        }

        public Builder fileName(String fileName) {
            FileObject.this.fileName = fileName;
            return this;
        }

        public Builder mimeType(String mimeType) {
            FileObject.this.mimeType = mimeType;
            return this;
        }

        public Builder hits(int hits) {
            FileObject.this.hits = hits;
            return this;
        }

        public Builder state(int state) {
            FileObject.this.state = state;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            FileObject.this.createdTime = createdTime;
            return this;
        }

        public Builder modifiedTime(LocalDateTime modifiedTime) {
            FileObject.this.modifiedTime = modifiedTime;
            return this;
        }

        public FileObject build() {
            return FileObject.this;
        }
    }
}
