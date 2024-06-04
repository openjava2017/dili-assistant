package com.diligrp.assistant.sms.pipeline;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.sms.exception.SmsServiceException;

import java.util.List;
import java.util.Optional;

public interface SmsPipelineManager {

    void registerPipeline(SmsPipeline pipeline);

    List<SmsPipeline> pipelines();

    default SmsPipeline findPipelineByCode(int code) {
        Optional<SmsPipeline> pipeline = pipelines().stream().filter(p -> p.code == code).findAny();
        return pipeline.orElseThrow(() -> new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "系统未配置此短信服务通道"));
    }
}
