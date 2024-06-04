package com.diligrp.assistant.dfs.pipeline;

import com.diligrp.assistant.dfs.domain.DfsFile;
import com.diligrp.assistant.dfs.exception.DfsServiceException;
import com.diligrp.assistant.dfs.type.PipelineType;
import com.diligrp.assistant.shared.ErrorCode;

public abstract class DfsPipeline {
    // 通道编码
    protected int code;
    // 通道名称
    protected String name;
    // 通道类型
    protected PipelineType type;

    public DfsPipeline(int code, String name, PipelineType type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    /**
     * 创建文件仓库，并返回仓库唯一标识：名称
     *
     * @param repositoryId - 仓库ID
     * @return 仓库名称，同仓库ID
     */
    public String createFileRepository(String repositoryId) {
        throw new DfsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "文件存储服务通道不支持此操作");
    }

    /**
     * 删除文件仓库 - 空仓库才允许删除
     *
     * @param repositoryId - 仓库ID
     * @return true/false
     */
    public boolean deleteFileRepository(String repositoryId) {
        throw new DfsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "文件存储服务通道不支持此操作");
    }

    /**
     * 存放文件到仓库
     *
     * @param repositoryId - 仓库ID
     * @param file - 文件
     */
    public abstract void putFile(String repositoryId, DfsFile file);

    /**
     * 从文件仓库中获取文件
     *
     * @param repositoryId - 仓库ID
     * @param fileId - 文件标识
     * @param style- OSS图片处理参数, 参见https://help.aliyun.com/zh/oss/user-guide/adjust-image-quality?spm=a2c4g.11186623.0.0.1c2d2759lemWct
     * @return - 文件
     */
    public abstract DfsFile getFile(String repositoryId, String fileId, String style);

    /**
     * 从文件仓库中删除文件
     *
     * @param repositoryId - 仓库ID
     * @param fileId - 文件标识
     * @return - true/false
     */
    public abstract boolean deleteFile(String repositoryId, String fileId);

    public void destroy() {
    }

    /**
     * 获取通道code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取通道编码
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取通道类型
     */
    public PipelineType getType() {
        return this.type;
    }
}
