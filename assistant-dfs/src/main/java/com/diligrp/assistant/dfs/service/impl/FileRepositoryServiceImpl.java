package com.diligrp.assistant.dfs.service.impl;

import com.diligrp.assistant.dfs.Constants;
import com.diligrp.assistant.dfs.dao.FileObjectDao;
import com.diligrp.assistant.dfs.dao.FileRepositoryDao;
import com.diligrp.assistant.dfs.domain.DfsAccessToken;
import com.diligrp.assistant.dfs.domain.DfsFile;
import com.diligrp.assistant.dfs.model.FileObject;
import com.diligrp.assistant.dfs.model.FileRepository;
import com.diligrp.assistant.dfs.pipeline.DfsPipeline;
import com.diligrp.assistant.dfs.pipeline.DfsPipelineManager;
import com.diligrp.assistant.dfs.service.FileRepositoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service("fileRepositoryService")
public class FileRepositoryServiceImpl implements FileRepositoryService {
    private final ExecutorService executorService = new ThreadPoolExecutor(Constants.CORE_POOL_SIZE, Constants.MAX_POOL_SIZE,
        1, TimeUnit.MINUTES, new LinkedBlockingQueue(1000), new ThreadPoolExecutor.CallerRunsPolicy());

    @Resource
    private FileRepositoryDao fileRepositoryDao;

    @Resource
    private FileObjectDao fileObjectDao;

    @Resource
    private DfsPipelineManager dfsPipelineManager;

    /**
     * 创建文件仓库，调用三方文件服务(如阿里OSS)创建Bucket存储
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createFileRepository(DfsAccessToken accessToken, FileRepository repository) {
        DfsPipeline pipeline = dfsPipelineManager.findPipelineByCode(accessToken.getPipeline());
        fileRepositoryDao.insertFileRepository(repository);

        return pipeline.createFileRepository(repository.getRepositoryId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFileRepository(DfsAccessToken accessToken) {
        String repositoryId = accessToken.getRepositoryId();
        DfsPipeline pipeline = dfsPipelineManager.findPipelineByCode(accessToken.getPipeline());
        if (pipeline.deleteFileRepository(repositoryId)) {
            fileRepositoryDao.deleteFileRepository(repositoryId);
        }
    }

    @Override
    public String uploadFile(DfsAccessToken accessToken, DfsFile file) {
        DfsPipeline pipeline = dfsPipelineManager.findPipelineByCode(accessToken.getPipeline());
        String repositoryId = accessToken.getRepositoryId();
        pipeline.putFile(repositoryId, file);
        // 为提高文件上传性能，异步持久化文件对象
        executorService.submit(() -> {
            LocalDateTime now = LocalDateTime.now();
            FileObject fileObject = FileObject.builder().repositoryId(repositoryId).fileId(file.getId())
                .fileName(file.getName()).mimeType(file.getMetadata().getMimeType()).hits(0).state(0)
                .createdTime(now).modifiedTime(now).build();
            // TODO: need transaction?
            fileObjectDao.insertFileObject(fileObject);
        });
        return file.getId();
    }

    @Override
    public DfsFile downloadFile(DfsAccessToken accessToken, String fileId, String style) {
        DfsPipeline pipeline = dfsPipelineManager.findPipelineByCode(accessToken.getPipeline());
        DfsFile file = pipeline.getFile(accessToken.getRepositoryId(), fileId, style);
        // 异步增加文件访问次数
        executorService.submit(() -> {
            // TODO: need transaction?
            fileObjectDao.hitFileObject(fileId, LocalDateTime.now());
        });
        return file;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(DfsAccessToken accessToken, String fileId) {
        DfsPipeline pipeline = dfsPipelineManager.findPipelineByCode(accessToken.getPipeline());
        if (pipeline.deleteFile(accessToken.getRepositoryId(), fileId)) {
            fileObjectDao.deleteFileObject(fileId);
        }
    }
}
