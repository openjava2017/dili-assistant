package com.diligrp.assistant.data.model;

import com.diligrp.assistant.shared.domain.BaseDo;

public class DataDistrict extends BaseDo {
    // 父区域ID
    private Long parentId;
    // 名称
    private String name;
    // 简称
    private String shortName;
    // 级别
    private Integer level;
    // 全称
    private String fullName;
    // 区号
    private String areaCode;
    // 拼音
    private String pyCode;
    // 简拼
    private String shortPy;
    // 路径
    private String path;
    // 路径名称
    private String pathName;
    // 经度
    private String longitude;
    // 纬度
    private String latitude;
    // 状态
    private Integer state;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getShortPy() {
        return shortPy;
    }

    public void setShortPy(String shortPy) {
        this.shortPy = shortPy;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
