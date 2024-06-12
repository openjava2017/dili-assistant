package com.diligrp.assistant.sms.pipeline;

import com.diligrp.assistant.sms.domain.SmsMessage;
import com.diligrp.assistant.sms.type.PipelineType;

// TODO: ali sms pipeline implement
public class AliSmsPipeline extends SmsPipeline {
    public AliSmsPipeline(int code, String name, PipelineType type) {
        super(code, name, type);
    }

    @Override
    public boolean templateSupported() {
        return false;
    }

    @Override
    public String sendSmsMessage(SmsMessage message) {
        return null;
    }
}
