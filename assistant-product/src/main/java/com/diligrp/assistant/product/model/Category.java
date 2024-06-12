package com.diligrp.assistant.product.model;

import com.diligrp.assistant.shared.domain.BaseDo;

import java.time.LocalDateTime;

public class Category extends BaseDo {
    // 父品类ID
    private Long parentId;
    // 名称
    private String name;
    // 别名
    private String alias;
    // 级别
    private Integer level;
    // 拼音
    private String pyCode;
    // 简拼
    private String shortCode;
    // 路径
    private String path;
    // 图标
    private String icon;
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

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public static Builder builder() {
        return new Category().new Builder();
    }

    public class Builder {
        public Builder parentId(Long parentId) {
            Category.this.parentId = parentId;
            return this;
        }

        public Builder name(String name) {
            Category.this.name = name;
            return this;
        }

        public Builder alias(String alias) {
            Category.this.alias = alias;
            return this;
        }

        public Builder level(Integer level) {
            Category.this.level = level;
            return this;
        }

        public Builder pyCode(String pyCode) {
            Category.this.pyCode = pyCode;
            return this;
        }

        public Builder shortCode(String shortCode) {
            Category.this.shortCode = shortCode;
            return this;
        }

        public Builder path(String path) {
            Category.this.path = path;
            return this;
        }

        public Builder icon(String icon) {
            Category.this.icon = icon;
            return this;
        }

        public Builder state(Integer state) {
            Category.this.state = state;
            return this;
        }

        public Builder version(Integer version) {
            Category.this.version = version;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            Category.this.createdTime = createdTime;
            return this;
        }

        public Builder modifiedTime(LocalDateTime modifiedTime) {
            Category.this.modifiedTime = modifiedTime;
            return this;
        }

        public Category build() {
            return Category.this;
        }
    }
}
