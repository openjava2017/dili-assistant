package com.diligrp.assistant.data.domain;

import com.diligrp.assistant.shared.domain.PageQuery;

public class DistrictPageQuery extends PageQuery {
    // 区域ID
    private Long id;
    // 区域级别
    private Integer level;

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
