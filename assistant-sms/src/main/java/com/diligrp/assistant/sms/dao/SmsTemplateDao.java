package com.diligrp.assistant.sms.dao;

import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import com.diligrp.assistant.sms.model.SmsTemplateDo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("smsTemplateDao")
public interface SmsTemplateDao extends MybatisMapperSupport {
    void insertSmsTemplate(SmsTemplateDo template);

    Optional<SmsTemplateDo> findSmsTemplateById(String templateId);

    int updateSmsTemplate(SmsTemplateDo template);

    int deleteSmsTemplate(String templateId);
}
