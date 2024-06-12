package com.diligrp.assistant.product.domain;

public class CategoryVO {
    // ID
    private Long id;
    // 父品类ID
    private Long parentId;
    // 名称
    private String name;
    // 别名
    private String alias;
    // 级别
    private Integer level;
    // 图标
    private String icon;
    // 路径
    private String path;

    public static CategoryVO of(Long id, Long parentId, String name, String alias, Integer level, String icon, String path) {
        CategoryVO category = new CategoryVO();
        category.id = id;
        category.parentId = parentId;
        category.name = name;
        category.alias = alias;
        category.level = level;
        category.icon = icon;
        category.path = path;

        return category;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
