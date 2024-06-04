package com.diligrp.assistant.data.domain;

public class DataDictionaryDTO {
    // 类型
    private Integer type;
    // 分组编码
    private String groupCode;
    // 编码
    private String code;
    // 名称
    private String name;
    // 字典值
    private String value;
    // 描述
    private String description;

    public static DataDictionaryDTO from(Integer type, String groupCode, String code, String name,
                                         String value, String description) {
        DataDictionaryDTO dictionary = new DataDictionaryDTO();
        dictionary.type = type;
        dictionary.groupCode = groupCode;
        dictionary.code = code;
        dictionary.name = name;
        dictionary.value = value;
        dictionary.description = description;
        return dictionary;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
