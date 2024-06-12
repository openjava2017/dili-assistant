package com.diligrp.assistant.product.dao;

import com.diligrp.assistant.product.domain.CategoryPageQuery;
import com.diligrp.assistant.product.model.Category;
import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("productCategoryDao")
public interface ProductCategoryDao extends MybatisMapperSupport {
    void insertCategory(Category category);

    int updateCategory(Category category);

    Optional<Category> findCategoryById(Long id);

    Optional<Category> findParentCategoryById(Long id);

    List<Category> findCategoriesByIds(List<Long> ids);

    List<Category> listChildrenById(CategoryPageQuery query);

    Long countChildrenById(Long id);

    List<Category> listCategoriesByLevel(CategoryPageQuery query);
}
