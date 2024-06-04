package com.diligrp.assistant.dfs;

import com.diligrp.assistant.dfs.pipeline.DefaultDfsPipelineManager;
import com.diligrp.assistant.dfs.pipeline.DfsPipeline;
import com.diligrp.assistant.dfs.pipeline.DfsPipelineManager;
import com.diligrp.assistant.dfs.pipeline.OssPipeline;
import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.diligrp.assistant.dfs")
@MapperScan(basePackages =  {"com.diligrp.assistant.dfs.dao"}, markerInterface = MybatisMapperSupport.class)
public class DfsConfiguration {

    @Bean
    @ConfigurationProperties("dfs")
    public DfsProperties dfsProperties() {
        return new DfsProperties();
    }

    @Bean
    public DfsPipelineManager dfsPipelineManager(DfsProperties properties) {
        DfsPipelineManager pipelineManager = new DefaultDfsPipelineManager();
        DfsProperties.Oss oss = properties.getOss();
        if (oss != null) {
            // 可利用数据库进行通道配置, 前期并没有必要
            DfsPipeline pipeline = new OssPipeline(1, "OSS文件存储服务", oss.getUri(),
                oss.getAccessKeyId(), oss.getAccessKeySecret());
            pipelineManager.registerPipeline(pipeline);
        }
        return pipelineManager;
    }
}