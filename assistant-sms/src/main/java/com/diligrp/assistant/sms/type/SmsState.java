package com.diligrp.assistant.sms.type;

import com.diligrp.assistant.shared.type.IEnumType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum SmsState implements IEnumType {
    SUBMITTED("已提交", 1),

    SUCCESS("发送成功", 2),

    FAILED("发送失败", 3);

    private String name;
    private int code;

    SmsState(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Optional<SmsState> getState(int code) {
        Stream<SmsState> states = Arrays.stream(values());
        return states.filter(state -> state.getCode() == code).findFirst();
    }

    public static String getName(int code) {
        Stream<SmsState> states = Arrays.stream(values());
        Optional<String> result = states.filter(state -> state.getCode() == code)
            .map(SmsState::getName).findFirst();
        return result.isPresent() ? result.get() : null;
    }

    public static List<SmsState> getStates() {
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