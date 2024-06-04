package com.diligrp.assistant.uid.domain;

import java.time.LocalDate;

public class SequenceKeyDTO {
    /**
     * KEY标识
     */
    private String key;
    /**
     * Key名称
     */
    private String name;
    /**
     * 起始值
     */
    private Long value;
    /**
     * 步长
     */
    private Integer step;
    /**
     * ID格式
     */
    private String pattern;
    /**
     * 有效日期
     */
    private LocalDate expiredOn;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public LocalDate getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(LocalDate expiredOn) {
        this.expiredOn = expiredOn;
    }
}
