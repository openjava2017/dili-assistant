package com.diligrp.assistant.sms.type;

import com.diligrp.assistant.shared.type.IEnumType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 允许的状态变更
 *
 * 待审核 -> 审核通过/审核失败
 * 审核通过 -> 禁用
 */
public enum TemplateState implements IEnumType {
    PENDING("待审核", 1),

    SUCCESS("审核通过", 2),

    FAILED("审核失败", 3),

    DISABLED("禁用", 0);

    private String name;
    private int code;

    TemplateState(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Optional<TemplateState> getState(int code) {
        Stream<TemplateState> states = Arrays.stream(values());
        return states.filter(state -> state.getCode() == code).findFirst();
    }

    public static String getName(int code) {
        Stream<TemplateState> states = Arrays.stream(values());
        Optional<String> result = states.filter(state -> state.getCode() == code)
            .map(TemplateState::getName).findFirst();
        return result.isPresent() ? result.get() : null;
    }

    public static List<TemplateState> getStates() {
        return Arrays.asList(values());
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }

    public boolean equalsTo(int code) {
        return this.code == code;
    }

    public String toString() {
        return this.name;
    }
}