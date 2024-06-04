package com.diligrp.assistant.uid.controller;

import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import com.diligrp.assistant.uid.domain.DefaultSnowflakeKey;
import com.diligrp.assistant.uid.domain.PersistentSequenceKey;
import com.diligrp.assistant.uid.domain.SequenceKeyDTO;
import com.diligrp.assistant.uid.service.KeyGenerator;
import com.diligrp.assistant.uid.service.SequenceKeyService;
import com.diligrp.assistant.uid.service.impl.KeyGeneratorManager;
import com.diligrp.assistant.uid.service.impl.SnowflakeKeyManager;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uid")
public class SequenceKeyController {

    @Resource
    private SequenceKeyService sequenceKeyService;

    @Resource
    private KeyGeneratorManager keyGeneratorManager;

    @Resource
    private SnowflakeKeyManager snowflakeKeyManager;

    @RequestMapping(value = "/sequence/register.do")
    public Message<?> register(@RequestBody SequenceKeyDTO request) {
        AssertUtils.notEmpty(request.getKey(), "key missed");
        AssertUtils.notEmpty(request.getName(), "name missed");
        AssertUtils.notNull(request.getValue(), "value missed");
        AssertUtils.notNull(request.getStep(), "step missed");

        PersistentSequenceKey sequenceKey = PersistentSequenceKey.builder().key(request.getKey())
            .name(request.getName()).value(request.getValue()).step(request.getStep()).pattern(request.getPattern())
            .expiredOn(request.getExpiredOn()).version(0).build();
        sequenceKeyService.registerSequenceKey(sequenceKey);
        return Message.success();
    }

    @RequestMapping(value = "/sequence/get.do")
    public Message<?> getSequenceKey(@RequestParam(value = "sequenceKey", required = false) String sequenceKey) {
        AssertUtils.notEmpty(sequenceKey, "sequenceKey missed");
        KeyGenerator keyGenerator = keyGeneratorManager.getKeyGenerator(sequenceKey);
        return Message.success(keyGenerator.nextId());
    }

    @RequestMapping(value = "/snowflake/get.do")
    public Message<?> getSnowflakeKey(@RequestParam(value = "sequenceKey", required = false) String sequenceKey) {
        AssertUtils.notEmpty(sequenceKey, "sequenceKey missed");
        DefaultSnowflakeKey snowflakeKey = new DefaultSnowflakeKey(sequenceKey);
        KeyGenerator keyGenerator = snowflakeKeyManager.getKeyGenerator(snowflakeKey);
        return Message.success(keyGenerator.nextId());
    }
}
