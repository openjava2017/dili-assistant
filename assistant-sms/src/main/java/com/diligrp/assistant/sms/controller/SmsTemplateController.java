package com.diligrp.assistant.sms.controller;

import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import com.diligrp.assistant.sms.Constants;
import com.diligrp.assistant.sms.SmsProperties;
import com.diligrp.assistant.sms.domain.SmsAccessToken;
import com.diligrp.assistant.sms.domain.SmsTemplateDTO;
import com.diligrp.assistant.sms.model.SmsTemplateDo;
import com.diligrp.assistant.sms.service.SmsTemplateService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/template")
public class SmsTemplateController {

    @Resource
    private SmsProperties smsProperties;

    @Resource
    private SmsTemplateService smsTemplateService;

    @RequestMapping(value = "/create.do")
    public Message<String> create(@RequestBody SmsTemplateDTO request, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        AssertUtils.notNull(request.getPipeline(), "pipeline missed");
        AssertUtils.notNull(request.getType(), "type missed");
        AssertUtils.notEmpty(request.getName(), "name missed");
        AssertUtils.notEmpty(request.getContent(), "content missed");

        String templateId = smsTemplateService.createSmsTemplate(request);
        return Message.success(templateId);
    }

    @RequestMapping(value = "/modify.do")
    public Message<?> modify(@RequestBody SmsTemplateDTO request, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        AssertUtils.notEmpty(request.getTemplateId(), "templateId missed");
        AssertUtils.notNull(request.getType(), "type missed");
        AssertUtils.notEmpty(request.getName(), "name missed");
        AssertUtils.notEmpty(request.getContent(), "content missed");

        smsTemplateService.modifySmsTemplate(request);
        return Message.success();
    }

    @RequestMapping(value = "/approve.do")
    public Message<?> approve(@RequestParam("templateId") String templateId, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        smsTemplateService.approveSmsTemplate(templateId);
        return Message.success();
    }

    @RequestMapping(value = "/disable.do")
    public Message<?> disable(@RequestParam("templateId") String templateId, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        smsTemplateService.disableSmsTemplate(templateId);
        return Message.success();
    }

    @RequestMapping(value = "/enable.do")
    public Message<?> enable(@RequestParam("templateId") String templateId, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        smsTemplateService.enableSmsTemplate(templateId);
        return Message.success();
    }

    @RequestMapping(value = "/delete.do")
    public Message<?> delete(@RequestParam("templateId") String templateId, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        smsTemplateService.deleteSmsTemplate(templateId);
        return Message.success();
    }
}
