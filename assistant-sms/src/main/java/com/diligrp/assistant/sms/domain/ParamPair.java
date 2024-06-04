package com.diligrp.assistant.sms.domain;

public class ParamPair {
    private String key;
    private String value;

    public ParamPair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
