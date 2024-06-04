package com.diligrp.assistant.sms.pipeline;

import org.springframework.beans.factory.DisposableBean;

import java.util.ArrayList;
import java.util.List;

public class DefaultSmsPipelineManager implements SmsPipelineManager, DisposableBean {
    private List<SmsPipeline> pipelines;

    public DefaultSmsPipelineManager() {
        this.pipelines = new ArrayList<>();
    }

    @Override
    public void registerPipeline(SmsPipeline pipeline) {
        this.pipelines.add(pipeline);
    }

    @Override
    public List<SmsPipeline> pipelines() {
        return this.pipelines;
    }

    @Override
    public void destroy() {
        for (SmsPipeline pipeline : pipelines) {
            pipeline.destroy();
        }
    }
}
