package com.diligrp.assistant.dfs.pipeline;

import com.diligrp.assistant.dfs.client.FileRepositoryClient;
import com.diligrp.assistant.dfs.client.OssFileRepositoryClient;
import com.diligrp.assistant.dfs.domain.DfsFile;
import com.diligrp.assistant.dfs.type.PipelineType;

/**
 * 阿里OSS文件存储服务通道
 */
// TODO: 数据库中进行通道配置
public class OssPipeline extends DfsPipeline {

    private FileRepositoryClient fileRepositoryClient;

    public OssPipeline(int code, String name, String uri, String accessKeyId, String accessKeySecret) {
        super(code, name, PipelineType.DFS_OSS);
        this.fileRepositoryClient = new OssFileRepositoryClient(uri, accessKeyId, accessKeySecret);
    }

    @Override
    public String createFileRepository(String repositoryId) {
        return fileRepositoryClient.createFileRepository(repositoryId);
    }

    @Override
    public boolean deleteFileRepository(String repositoryId) {
        return fileRepositoryClient.deleteFileRepository(repositoryId);
    }

    @Override
    public void putFile(String repositoryId, DfsFile file) {
        fileRepositoryClient.putFile(repositoryId, file);
    }

    @Override
    public DfsFile getFile(String repositoryId, String fileId, String style) {
        return fileRepositoryClient.getFile(repositoryId, fileId, style);
    }

    @Override
    public boolean deleteFile(String repositoryId, String fileId) {
        return fileRepositoryClient.deleteFile(repositoryId, fileId);
    }

    public void destroy() {
        fileRepositoryClient.destroy();
    }
}
