package com.diligrp.assistant.sms.model;

import com.diligrp.assistant.shared.domain.BaseDo;

import java.time.LocalDateTime;

public class SmsMessageDo extends BaseDo {
    // 模版ID
    private String templateId;
    // 服务通道
    private Integer pipeline;
    // 短信类型
    private Integer type;
    // 消息ID
    private String messageId;
    // 电话号码
    private String telephone;
    // 消息内容
    private String content;
    // 消息状态
    private Integer state;
    // 外部请求ID
    private String outMessageId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getPipeline() {
        return pipeline;
    }

    public void setPipeline(Integer pipeline) {
        this.pipeline = pipeline;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOutMessageId() {
        return outMessageId;
    }

    public void setOutMessageId(String outMessageId) {
        this.outMessageId = outMessageId;
    }

    public static Builder builder() {
        return new SmsMessageDo().new Builder();
    }

    public class Builder {
        public Builder templateId(String templateId) {
            SmsMessageDo.this.templateId = templateId;
            return this;
        }

        public Builder pipeline(int pipeline) {
            SmsMessageDo.this.pipeline = pipeline;
            return this;
        }

        public Builder type(int type) {
            SmsMessageDo.this.type = type;
            return this;
        }

        public Builder messageId(String messageId) {
            SmsMessageDo.this.messageId = messageId;
            return this;
        }

        public Builder telephone(String telephone) {
            SmsMessageDo.this.telephone = telephone;
            return this;
        }

        public Builder content(String content) {
            SmsMessageDo.this.content = content;
            return this;
        }

        public Builder state(int state) {
            SmsMessageDo.this.state = state;
            return this;
        }

        public Builder outMessageId(String outMessageId) {
            SmsMessageDo.this.outMessageId = outMessageId;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            SmsMessageDo.this.createdTime = createdTime;
            return this;
        }

        public Builder modifiedTime(LocalDateTime modifiedTime) {
            SmsMessageDo.this.modifiedTime = modifiedTime;
            return this;
        }

        public SmsMessageDo build() {
            return SmsMessageDo.this;
        }
    }
}
