package com.diligrp.assistant.shared.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class ContainerSupport extends HashMap<String, Object> {
    public ContainerSupport attach(Object object) {
        put(object.getClass().getName(), object);
        return this;
    }

    public ContainerSupport attach(String key, Object object) {
        put(key, object);
        return this;
    }

    public Long getLong(String param) {
        Object value = get(param);
        if (value != null) {
            return value instanceof Long ? (Long)value : Long.parseLong(value.toString());
        }
        return null;
    }

    public Integer getInteger(String param) {
        Object value = get(param);
        if (value != null) {
            return value instanceof Integer ? (Integer)value : Integer.parseInt(value.toString());
        }
        return null;
    }

    public String getString(String param) {
        Object value = get(param);
        return value != null ? value.toString() : null;
    }

    public <T> T getObject(String param, Class<T> type) {
        Object value = get(param);
        return value == null ? null : type.cast(value);
    }

    public <T> T getObject(Class<T> type) {
        Object value = get(type.getName());
        return value == null ? null : type.cast(value);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getObject(String param) {
        Object value = get(param);
        return Optional.ofNullable ((T) value);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<List<T>> getObjects(String param) {
        Object value = get(param);
        return Optional.ofNullable ((List<T>) value);
    }
}
