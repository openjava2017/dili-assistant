package com.diligrp.assistant.dfs.pipeline;

import com.diligrp.assistant.dfs.exception.DfsServiceException;
import com.diligrp.assistant.shared.ErrorCode;

import java.util.List;
import java.util.Optional;

public interface DfsPipelineManager {

    void registerPipeline(DfsPipeline pipeline);

    List<DfsPipeline> pipelines();

    default DfsPipeline findPipelineByCode(int code) {
        Optional<DfsPipeline> pipeline = pipelines().stream().filter(p -> p.code == code).findAny();
        return pipeline.orElseThrow(() -> new DfsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "系统未配置此文件存储服务通道"));
    }
}
