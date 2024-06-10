package com.diligrp.assistant.data.domain;

public class DataDistrictDTO {
    // ID
    private Long id;
    // 父区域ID
    private Long parentId;
    // 名称
    private String name;
    // 级别
    private Integer level;
    // 全称
    private String fullName;

    public static DataDistrictDTO of(Long id, Long parentId, String name, Integer level, String fullName) {
        DataDistrictDTO district = new DataDistrictDTO();
        district.id = id;
        district.parentId = parentId;
        district.name = name;
        district.level = level;
        district.fullName = fullName;

        return district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
