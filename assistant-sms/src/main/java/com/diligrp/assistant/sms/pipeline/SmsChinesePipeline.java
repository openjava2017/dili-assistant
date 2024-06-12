package com.diligrp.assistant.sms.pipeline;

import com.diligrp.assistant.sms.client.SmsChineseHttpClient;
import com.diligrp.assistant.sms.domain.SmsMessage;
import com.diligrp.assistant.sms.type.PipelineType;

/**
 * 网建短信服务通道
 */
// TODO: 数据库中进行通道配置
public class SmsChinesePipeline extends SmsPipeline {

    private final SmsChineseHttpClient client;

    public SmsChinesePipeline(int code, String name, String uri, String uid, String secretKey) {
        super(code, name, PipelineType.SMS_CHINESE);
        this.client = new SmsChineseHttpClient(uri, uid, secretKey);
    }

    @Override
    public boolean templateSupported() {
        return false;
    }

    @Override
    public String sendSmsMessage(SmsMessage message) {
        return client.sendSmsMessage(message.getTelephone(), message.getContent());
    }
}
