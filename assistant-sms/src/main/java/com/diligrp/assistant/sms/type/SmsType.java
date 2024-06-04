package com.diligrp.assistant.sms.type;

import com.diligrp.assistant.shared.type.IEnumType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 与阿里云短信服务中的模版类型保持一致，请谨慎修改
 */
public enum SmsType implements IEnumType {
    CAPTCHA("验证码", 0),

    INFORMATION("短信通知", 1),

    PROMOTION("推广短信", 2);

    private String name;
    private int code;

    SmsType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Optional<SmsType> getType(int code) {
        Stream<SmsType> types = Arrays.stream(values());
        return types.filter(type -> type.getCode() == code).findFirst();
    }

    public static String getName(int code) {
        Stream<SmsType> TYPES = Arrays.stream(values());
        Optional<String> result = TYPES.filter(type -> type.getCode() == code)
            .map(SmsType::getName).findFirst();
        return result.isPresent() ? result.get() : null;
    }

    public static List<SmsType> getTypes() {
        return Arrays.asList(values());
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return this.name;
    }
}