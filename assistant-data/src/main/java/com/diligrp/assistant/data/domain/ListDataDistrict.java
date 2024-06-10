package com.diligrp.assistant.data.domain;

public class ListDataDistrict {
    // 页号
    private Integer pageNo = 1;
    // 每页记录数
    private Integer pageSize = 20;

    // 区域ID
    private Long id;
    // 区域级别
    private Integer level;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
