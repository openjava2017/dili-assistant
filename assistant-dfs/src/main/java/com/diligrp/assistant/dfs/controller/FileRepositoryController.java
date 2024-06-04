package com.diligrp.assistant.dfs.controller;

import com.diligrp.assistant.dfs.Constants;
import com.diligrp.assistant.dfs.DfsProperties;
import com.diligrp.assistant.dfs.domain.DfsAccessToken;
import com.diligrp.assistant.dfs.domain.FileRepositoryDTO;
import com.diligrp.assistant.dfs.exception.DfsServiceException;
import com.diligrp.assistant.dfs.model.FileRepository;
import com.diligrp.assistant.dfs.pipeline.DfsPipeline;
import com.diligrp.assistant.dfs.pipeline.DfsPipelineManager;
import com.diligrp.assistant.dfs.service.FileRepositoryService;
import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import com.diligrp.assistant.shared.util.RandomUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dfs")
public class FileRepositoryController {

    @Resource
    private DfsProperties dfsProperties;

    @Resource
    private FileRepositoryService fileRepositoryService;

    @Resource
    private DfsPipelineManager dfsPipelineManager;

    @RequestMapping(value = "/repository/create.do")
    public Message<?> create(@RequestBody FileRepositoryDTO request) {
        AssertUtils.notEmpty(request.getName(), "name missed");
        AssertUtils.notNull(request.getPipeline(), "pipeline missed");

        DfsPipeline pipeline = dfsPipelineManager.findPipelineByCode(request.getPipeline());
        DfsAccessToken accessToken = new DfsAccessToken(pipeline.getCode(), RandomUtils.randomUUID());
        FileRepository repository = FileRepository.builder().repositoryId(accessToken.getRepositoryId())
            .name(request.getName()).pipeline(request.getPipeline()).description(request.getDescription()).build();
        fileRepositoryService.createFileRepository(accessToken, repository);
        return Message.success(accessToken.toString(dfsProperties.getPrivateKey()));
    }

    @RequestMapping(value = "/repository/delete.do")
    public Message<?> deleteRepository(@RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        DfsAccessToken accessToken = accessAuthorization(authorization);
        fileRepositoryService.deleteFileRepository(accessToken);
        return Message.success();
    }

    private DfsAccessToken accessAuthorization(String authorization) {
        if (authorization == null) {
            throw new DfsServiceException(ErrorCode.UNAUTHORIZED_ACCESS_ERROR, ErrorCode.MESSAGE_ACCESS_DENIED);
        }
        return DfsAccessToken.of(authorization, dfsProperties.getPublicKey());
    }
}
