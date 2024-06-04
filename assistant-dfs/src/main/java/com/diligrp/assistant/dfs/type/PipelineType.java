package com.diligrp.assistant.dfs.type;

import com.diligrp.assistant.shared.type.IEnumType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum PipelineType implements IEnumType {
    DFS_OSS("阿里云对象存储服务", 1),

    DFS_MINIO("MINIO对象存储服务", 2);

    private String name;
    private int code;

    PipelineType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Optional<PipelineType> getType(int code) {
        Stream<PipelineType> GENDERS = Arrays.stream(values());
        return GENDERS.filter(type -> type.getCode() == code).findFirst();
    }

    public static String getName(int code) {
        Stream<PipelineType> TYPES = Arrays.stream(values());
        Optional<String> result = TYPES.filter(type -> type.getCode() == code)
            .map(PipelineType::getName).findFirst();
        return result.isPresent() ? result.get() : null;
    }

    public static List<PipelineType> getTypes() {
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