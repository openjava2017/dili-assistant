package com.diligrp.assistant.sms.service;

import com.diligrp.assistant.sms.domain.SmsTemplateDTO;

public interface SmsTemplateService {
    /**
     * 创建短信模版
     *
     * @param smsTemplate - 短信模版
     * @return 模版ID
     */
    String createSmsTemplate(SmsTemplateDTO smsTemplate);

    /**
     * 修改短信模版
     *
     * @param smsTemplate - 短信模版
     */
    void modifySmsTemplate(SmsTemplateDTO smsTemplate);

    /**
     * 短信模版审批通过
     *
     * @param templateId - 短信模版ID
     */
    void approveSmsTemplate(String templateId);

    /**
     * 禁用短信模版
     *
     * @param templateId - 短信模版ID
     */
    void disableSmsTemplate(String templateId);

    /**
     * 启用短信模版
     *
     * @param templateId - 短信模版ID
     */
    void enableSmsTemplate(String templateId);

    /**
     * 删除短信模版
     *
     * @param templateId - 短信模版ID
     */
    void deleteSmsTemplate(String templateId);
}
