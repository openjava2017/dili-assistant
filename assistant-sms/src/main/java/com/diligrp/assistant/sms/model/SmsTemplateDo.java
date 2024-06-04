package com.diligrp.assistant.sms.model;

import com.diligrp.assistant.shared.domain.BaseDo;

import java.time.LocalDateTime;

public class SmsTemplateDo extends BaseDo {
    // 模版ID
    private String templateId;
    // 服务通道
    private Integer pipeline;
    // 模版类型
    private Integer type;
    // 模版名称
    private String name;
    // 模版内容
    private String content;
    // 模版状态
    private Integer state;
    // 备注
    private String description;
    // 外部模版ID
    private String outTemplateId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTemplateId() {
        return outTemplateId;
    }

    public void setOutTemplateId(String outTemplateId) {
        this.outTemplateId = outTemplateId;
    }

    public static SmsTemplateDo.Builder builder() {
        return new SmsTemplateDo().new Builder();
    }

    public class Builder {
        public Builder templateId(String templateId) {
            SmsTemplateDo.this.templateId = templateId;
            return this;
        }

        public Builder pipeline(int pipeline) {
            SmsTemplateDo.this.pipeline = pipeline;
            return this;
        }

        public Builder type(int type) {
            SmsTemplateDo.this.type = type;
            return this;
        }

        public Builder name(String name) {
            SmsTemplateDo.this.name = name;
            return this;
        }

        public Builder content(String content) {
            SmsTemplateDo.this.content = content;
            return this;
        }

        public Builder state(int state) {
            SmsTemplateDo.this.state = state;
            return this;
        }

        public Builder description(String description) {
            SmsTemplateDo.this.description = description;
            return this;
        }

        public Builder outTemplateId(String outTemplateId) {
            SmsTemplateDo.this.outTemplateId = outTemplateId;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            SmsTemplateDo.this.createdTime = createdTime;
            return this;
        }

        public Builder modifiedTime(LocalDateTime modifiedTime) {
            SmsTemplateDo.this.modifiedTime = modifiedTime;
            return this;
        }

        public SmsTemplateDo build() {
            return SmsTemplateDo.this;
        }
    }
}
