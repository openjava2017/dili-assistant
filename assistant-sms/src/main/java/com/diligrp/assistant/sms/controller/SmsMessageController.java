package com.diligrp.assistant.sms.controller;

import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import com.diligrp.assistant.sms.Constants;
import com.diligrp.assistant.sms.SmsProperties;
import com.diligrp.assistant.sms.domain.SmsAccessToken;
import com.diligrp.assistant.sms.domain.SmsMessage;
import com.diligrp.assistant.sms.service.SmsMessageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms/message")
public class SmsMessageController {
    @Resource
    private SmsProperties smsProperties;

    @Resource
    private SmsMessageService smsMessageService;

    @RequestMapping(value = "/send.do")
    public Message<?> send(@RequestBody SmsMessage request, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        SmsAccessToken.of(authorization, smsProperties.getPublicKey());
        AssertUtils.notEmpty(request.getTemplateId(), "templateId missed");
        AssertUtils.notNull(request.getTelephone(), "telephone missed");
        smsMessageService.sendSmsMessage(request.getTemplateId(), request.getTelephone(), request.getParams());
        return Message.success();
    }
}
