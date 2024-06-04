package com.diligrp.assistant.sms.domain;

public class SmsTemplateDTO {
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
    // 备注
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
