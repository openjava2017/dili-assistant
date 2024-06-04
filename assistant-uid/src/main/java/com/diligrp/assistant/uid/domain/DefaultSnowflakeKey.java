package com.diligrp.assistant.uid.domain;

import com.diligrp.assistant.uid.service.impl.SnowflakeKeyManager;

public class DefaultSnowflakeKey implements SnowflakeKeyManager.SnowflakeKey {
    private final String sequenceKey;

    public DefaultSnowflakeKey(String sequenceKey) {
        this.sequenceKey = sequenceKey;
    }

    public String identifier() {
        return this.sequenceKey;
    }
}
