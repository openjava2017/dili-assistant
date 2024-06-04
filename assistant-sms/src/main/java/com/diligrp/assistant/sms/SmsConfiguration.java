package com.diligrp.assistant.sms;

import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import com.diligrp.assistant.sms.pipeline.DefaultSmsPipelineManager;
import com.diligrp.assistant.sms.pipeline.SmsChinesePipeline;
import com.diligrp.assistant.sms.pipeline.SmsPipeline;
import com.diligrp.assistant.sms.pipeline.SmsPipelineManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.diligrp.assistant.sms")
@MapperScan(basePackages =  {"com.diligrp.assistant.sms.dao"}, markerInterface = MybatisMapperSupport.class)
public class SmsConfiguration {
    @Bean
    @ConfigurationProperties("sms")
    public SmsProperties smsProperties() {
        return new SmsProperties();
    }

    @Bean
    public SmsPipelineManager smsPipelineManager(SmsProperties properties) {
        SmsPipelineManager pipelineManager = new DefaultSmsPipelineManager();
        SmsProperties.SmsChinese chinese = properties.getSmschinese();
        if (chinese != null) {
            // 可利用数据库进行通道配置, 前期并没有必要
            SmsPipeline pipeline = new SmsChinesePipeline(1001, "网建短信服务通道", chinese.getUri(),
                    chinese.getUid(), chinese.getSecretKey());
            pipelineManager.registerPipeline(pipeline);
        }
        return pipelineManager;
    }
}