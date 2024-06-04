package com.diligrp.assistant.sms.service.impl;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.sms.dao.SmsTemplateDao;
import com.diligrp.assistant.sms.domain.SmsTemplate;
import com.diligrp.assistant.sms.domain.SmsTemplateDTO;
import com.diligrp.assistant.sms.exception.SmsServiceException;
import com.diligrp.assistant.sms.model.SmsTemplateDo;
import com.diligrp.assistant.sms.pipeline.SmsPipeline;
import com.diligrp.assistant.sms.pipeline.SmsPipelineManager;
import com.diligrp.assistant.sms.service.SmsTemplateService;
import com.diligrp.assistant.sms.type.TemplateState;
import com.diligrp.assistant.uid.service.impl.KeyGeneratorManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("smsTemplateService")
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Resource
    private SmsTemplateDao smsTemplateDao;

    @Resource
    private SmsPipelineManager smsPipelineManager;

    @Resource
    private KeyGeneratorManager keyGeneratorManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createSmsTemplate(SmsTemplateDTO smsTemplate) {
        String outTemplateId = null;
        LocalDateTime now = LocalDateTime.now();

        SmsPipeline pipeline = smsPipelineManager.findPipelineByCode(smsTemplate.getPipeline());
        if (pipeline.templateSupported()) {
            SmsTemplate request = new SmsTemplate(smsTemplate.getType(), smsTemplate.getName(),
                smsTemplate.getContent(), smsTemplate.getDescription());
            outTemplateId = pipeline.createSmsTemplate(request);
        }

        SmsTemplateDo.Builder template = SmsTemplateDo.builder();
        String templateId = keyGeneratorManager.getKeyGenerator("SMS_TEMPLATE_KEY").nextId();
        template.templateId(templateId).pipeline(smsTemplate.getPipeline()).type(smsTemplate.getType())
            .name(smsTemplate.getName()).content(smsTemplate.getContent()).state(TemplateState.PENDING.getCode())
            .description(smsTemplate.getDescription()).outTemplateId(outTemplateId).createdTime(now).modifiedTime(now);
        smsTemplateDao.insertSmsTemplate(template.build());
        return templateId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifySmsTemplate(SmsTemplateDTO smsTemplate) {
        LocalDateTime now = LocalDateTime.now();

        SmsTemplateDo template = smsTemplateDao.findSmsTemplateById(smsTemplate.getTemplateId())
            .orElseThrow(() -> new SmsServiceException(ErrorCode.OBJECT_NOT_FOUND, "短信模版不存在"));
        // 与阿里云模版管理逻辑保持一致：只允许修改未审核通过的模版，审核通过的模版只能删除
        if (!TemplateState.PENDING.equalsTo(template.getState()) && !TemplateState.FAILED.equalsTo(template.getState())) {
            String error = String.format("不能修改状态为\"%s\"的短信模版", TemplateState.getName(template.getState()));
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, error);
        }

        SmsTemplateDo smsTemplateDo = SmsTemplateDo.builder().templateId(smsTemplate.getTemplateId()).type(smsTemplate.getType())
            .name(smsTemplate.getName()).content(smsTemplate.getContent()).description(smsTemplate.getDescription())
            .modifiedTime(now).build();
        smsTemplateDao.updateSmsTemplate(smsTemplateDo);

        SmsPipeline pipeline = smsPipelineManager.findPipelineByCode(smsTemplate.getPipeline());
        if (pipeline.templateSupported()) {
            SmsTemplate request = new SmsTemplate(template.getOutTemplateId(), smsTemplate.getType(),
                smsTemplate.getName(), smsTemplate.getContent(), smsTemplate.getDescription());
            pipeline.modifySmsTemplate(request);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveSmsTemplate(String templateId) {
        LocalDateTime now = LocalDateTime.now();

        SmsTemplateDo smsTemplate = smsTemplateDao.findSmsTemplateById(templateId)
            .orElseThrow(() -> new SmsServiceException(ErrorCode.OBJECT_NOT_FOUND, "短信模版不存在"));
        if (TemplateState.SUCCESS.equalsTo(smsTemplate.getState())) {
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信模版已审批通过");
        }
        if (TemplateState.DISABLED.equalsTo(smsTemplate.getState())) {
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信模版已被禁用");
        }
        SmsPipeline pipeline = smsPipelineManager.findPipelineByCode(smsTemplate.getPipeline());
        if (pipeline.templateSupported()) {
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "短信服务通道不允许进行人工审批");
        }

        SmsTemplateDo template = SmsTemplateDo.builder().templateId(templateId).state(TemplateState.SUCCESS.getCode())
            .modifiedTime(now).build();
        smsTemplateDao.updateSmsTemplate(template);
    }

    @Override
    public void disableSmsTemplate(String templateId) {
        SmsTemplateDo smsTemplate = smsTemplateDao.findSmsTemplateById(templateId)
            .orElseThrow(() -> new SmsServiceException(ErrorCode.OBJECT_NOT_FOUND, "短信模版不存在"));
        if (!TemplateState.SUCCESS.equalsTo(smsTemplate.getState())) {
            String error = String.format("不能禁用状态为\"%s\"的短信模版", TemplateState.getName(smsTemplate.getState()));
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, error);
        }

        // 仅仅允许审核通过的短信模版被禁用
        SmsTemplateDo template = SmsTemplateDo.builder().templateId(templateId).state(TemplateState.DISABLED.getCode())
            .modifiedTime(LocalDateTime.now()).build();
        smsTemplateDao.updateSmsTemplate(template);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableSmsTemplate(String templateId) {
        SmsTemplateDo smsTemplate = smsTemplateDao.findSmsTemplateById(templateId)
            .orElseThrow(() -> new SmsServiceException(ErrorCode.OBJECT_NOT_FOUND, "短信模版不存在"));
        if (!TemplateState.DISABLED.equalsTo(smsTemplate.getState())) {
            throw new SmsServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "仅仅被禁用的短信模版才能被启用");
        }

        SmsTemplateDo template = SmsTemplateDo.builder().templateId(templateId).state(TemplateState.SUCCESS.getCode())
            .modifiedTime(LocalDateTime.now()).build();
        smsTemplateDao.updateSmsTemplate(template);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSmsTemplate(String templateId) {
        SmsTemplateDo smsTemplate = smsTemplateDao.findSmsTemplateById(templateId)
            .orElseThrow(() -> new SmsServiceException(ErrorCode.OBJECT_NOT_FOUND, "短信模版不存在"));

        SmsPipeline pipeline = smsPipelineManager.findPipelineByCode(smsTemplate.getPipeline());
        if (pipeline.templateSupported()) {
            pipeline.deleteSmsTemplate(templateId);
        }

        smsTemplateDao.deleteSmsTemplate(templateId);
    }
}
