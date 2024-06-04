package com.diligrp.assistant.sms.domain;

import java.util.List;

public class SmsMessage {
    // 短信服务通道模版标识 - 支持短信模版的服务通道使用
    private String templateId;
    // 手机号
    private String telephone;
    // 解析模版变量后的短信内容 - 不支持短信模版的服务通道使用
    private String content;
    // 模版变量
    private List<ParamPair> params;

    public SmsMessage(String templateId, String telephone, String content, List<ParamPair> params) {
        this.templateId = templateId;
        this.telephone = telephone;
        this.content = content;
        this.params = params;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getContent() {
        return content;
    }

    public List<ParamPair> getParams() {
        return params;
    }
}
