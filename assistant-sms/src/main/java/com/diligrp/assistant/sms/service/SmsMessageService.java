package com.diligrp.assistant.sms.service;

import com.diligrp.assistant.sms.domain.ParamPair;

import java.util.List;

public interface SmsMessageService {
    /**
     * 发送短信
     *
     * @param templateId - 短信模版ID
     * @param telephone - 手机号
     * @param params - 参数
     */
    void sendSmsMessage(String templateId, String telephone, List<ParamPair> params);
}
