package com.diligrp.assistant.sms.pipeline;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.sms.domain.SmsMessage;
import com.diligrp.assistant.sms.domain.SmsTemplate;
import com.diligrp.assistant.sms.exception.SmsServiceException;
import com.diligrp.assistant.sms.type.PipelineType;
import com.diligrp.assistant.sms.type.SmsState;
import com.diligrp.assistant.sms.type.TemplateState;

public abstract class SmsPipeline {
    // 通道编码
    protected int code;
    // 通道名称
    protected String name;
    // 通道类型
    protected PipelineType type;

    public SmsPipeline(int code, String name, PipelineType type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    /**
     * 是否支持短信模版功能
     */
    public abstract boolean templateSupported();

    /**
     * 创建短信模版
     *
     * @param template - 短信模版
     * @return - 模版编码/ID
     */
    public String createSmsTemplate(SmsTemplate template) {
        throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信服务通道不支持此操作");
    }

    /**
     * 修改短信模版 - 阿里云短信服务只允许修改审核中的模版，审核通过的模版只能删除
     *
     * @param template - 短信模版
     */
    public void modifySmsTemplate(SmsTemplate template) {
        throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信服务通道不支持此操作");
    }

    /**
     * 根据短信模版编码查询模版审核状态
     *
     * @param templateId - 模版唯一标识
     * @return 模版状态
     */
    public TemplateState querySmsTemplateState(String templateId) {
        throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信服务通道不支持此操作");
    }

    /**
     * 删除短信模版
     *
     * @param templateId - 模版唯一标识
     */
    public void deleteSmsTemplate(String templateId) {
        throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信服务通道不支持此操作");
    }

    /**
     * 根据短信模版发送短信
     *
     * @param message - 短信
     * @return 短信唯一标识
     */
    public abstract String sendSmsMessage(SmsMessage message);

    /**
     * 根据短信唯一标识查询短信查询状态
     *
     * @param messageId - 短信唯一标识
     * @return 发送状态
     */
    public SmsState querySmsMessageState(String messageId) {
        throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信服务通道不支持此操作");
    }

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
