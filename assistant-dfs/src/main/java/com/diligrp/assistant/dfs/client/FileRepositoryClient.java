package com.diligrp.assistant.dfs.client;

import com.diligrp.assistant.dfs.domain.DfsFile;

public interface FileRepositoryClient {
    /**
     * 创建文件仓库，并返回仓库唯一标识：名称
     *
     * @param repositoryId - 仓库ID
     * @return 仓库名称，同仓库ID
     */
    String createFileRepository(String repositoryId);

    /**
     * 删除文件仓库 - 空仓库才允许删除
     *
     * @param repositoryId - 仓库ID
     * @return true/false
     */
    boolean deleteFileRepository(String repositoryId);

    /**
     * 存放文件到仓库
     *
     * @param repositoryId - 仓库ID
     * @param file - 文件
     */
    void putFile(String repositoryId, DfsFile file);

    /**
     * 从文件仓库中获取文件
     *
     * @param repositoryId - 仓库ID
     * @param fileId - 文件标识
     * @param style - 图片处理参数, 参见https://help.aliyun.com/zh/oss/user-guide/adjust-image-quality?spm=a2c4g.11186623.0.0.1c2d2759lemWct
     * @return - 文件
     */
    DfsFile getFile(String repositoryId, String fileId, String style);

    /**
     * 从文件仓库中删除文件
     *
     * @param repositoryId - 仓库ID
     * @param fileId - 文件标识
     * @return - true/false
     */
    boolean deleteFile(String repositoryId, String fileId);

    void destroy();
}
