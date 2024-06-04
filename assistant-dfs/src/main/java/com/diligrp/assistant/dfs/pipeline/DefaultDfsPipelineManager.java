package com.diligrp.assistant.dfs.pipeline;

import org.springframework.beans.factory.DisposableBean;

import java.util.ArrayList;
import java.util.List;

public class DefaultDfsPipelineManager implements DfsPipelineManager, DisposableBean {
    private List<DfsPipeline> pipelines;

    public DefaultDfsPipelineManager() {
        this.pipelines = new ArrayList<>();
    }

    @Override
    public void registerPipeline(DfsPipeline pipeline) {
        this.pipelines.add(pipeline);
    }

    @Override
    public List<DfsPipeline> pipelines() {
        return this.pipelines;
    }

    @Override
    public void destroy() {
        for (DfsPipeline pipeline : pipelines) {
            pipeline.destroy();
        }
    }
}
