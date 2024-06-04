package com.diligrp.assistant.sms.domain;

public class SmsTemplate {
    // 模版ID
    private String id;
    // 模版类型
    private Integer type;
    // 模版名称
    private String name;
    // 模版内容
    private String content;
    // 模版描述
    private String description;

    public SmsTemplate(Integer type, String name, String content, String description) {
        this(null, type, name, content, description);
    }

    public SmsTemplate(String id, Integer type, String name, String content, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.content = content;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }
}
