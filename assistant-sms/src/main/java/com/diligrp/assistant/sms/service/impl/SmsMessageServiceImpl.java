package com.diligrp.assistant.sms.service.impl;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.shared.util.ObjectUtils;
import com.diligrp.assistant.shared.util.RandomUtils;
import com.diligrp.assistant.sms.dao.SmsMessageDao;
import com.diligrp.assistant.sms.dao.SmsTemplateDao;
import com.diligrp.assistant.sms.domain.ParamPair;
import com.diligrp.assistant.sms.domain.SmsMessage;
import com.diligrp.assistant.sms.exception.SmsServiceException;
import com.diligrp.assistant.sms.model.SmsMessageDo;
import com.diligrp.assistant.sms.model.SmsTemplateDo;
import com.diligrp.assistant.sms.pipeline.SmsPipeline;
import com.diligrp.assistant.sms.pipeline.SmsPipelineManager;
import com.diligrp.assistant.sms.service.SmsMessageService;
import com.diligrp.assistant.sms.type.SmsState;
import com.diligrp.assistant.sms.type.TemplateState;
import jakarta.annotation.Resource;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("smsMessageService")
public class SmsMessageServiceImpl implements SmsMessageService {

    @Resource
    private SmsTemplateDao smsTemplateDao;

    @Resource
    private SmsMessageDao smsMessageDao;

    @Resource
    private SmsPipelineManager smsPipelineManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendSmsMessage(String templateId, String telephone, List<ParamPair> params) {
        SmsTemplateDo smsTemplate = smsTemplateDao.findSmsTemplateById(templateId)
            .orElseThrow(() -> new SmsServiceException(ErrorCode.OBJECT_NOT_FOUND, "短信模版不存在"));
        if (TemplateState.PENDING.equalsTo(smsTemplate.getState()) || TemplateState.FAILED.equalsTo(smsTemplate.getState())) {
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信模版未审核通过，不能发送短信");
        }
        if (TemplateState.DISABLED.equalsTo(smsTemplate.getState())) {
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信模版被禁用，不能发送短信");
        }

        LocalDateTime now = LocalDateTime.now();
        SmsPipeline pipeline = smsPipelineManager.findPipelineByCode(smsTemplate.getPipeline());
        String content = smsTemplate.getContent();
        if (!ObjectUtils.isEmpty(params)) { // 解析模版变量
            Map<String, String> paramMap = new HashMap<>(params.size());
            params.forEach(p -> paramMap.put(p.getKey(), p.getValue()));
            StringSubstitutor engine = new StringSubstitutor(paramMap);
            content = engine.replace(content);
        }

        String messageId = RandomUtils.randomUUID();
        SmsMessage smsMessage = new SmsMessage(smsTemplate.getOutTemplateId(), telephone, content, params);
        String outMessageId = pipeline.sendSmsMessage(smsMessage);
        SmsMessageDo message = SmsMessageDo.builder().templateId(templateId).pipeline(smsTemplate.getPipeline())
            .type(smsTemplate.getType()).messageId(messageId).telephone(telephone).content(content)
            .state(SmsState.SUCCESS.getCode()).outMessageId(outMessageId).createdTime(now).modifiedTime(now).build();
        smsMessageDao.insertSmsMessage(message);
    }
}
