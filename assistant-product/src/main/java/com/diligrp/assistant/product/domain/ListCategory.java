package com.diligrp.assistant.product.domain;

public class ListCategory {
    // 页号
    private Integer pageNo = 1;
    // 每页记录数
    private Integer pageSize = 20;

    // 品类ID
    private Long id;
    // 品类级别
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
