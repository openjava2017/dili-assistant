package com.diligrp.assistant.product.service;

import com.diligrp.assistant.product.domain.CategoryDTO;
import com.diligrp.assistant.product.domain.CategoryPageQuery;
import com.diligrp.assistant.product.model.Category;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 创建品类
     *
     * @param request - 品类
     * @return 品类ID
     */
    long createCategory(CategoryDTO request);

    /**
     * 修改品类
     *
     * @param request - 品类
     */
    void updateCategory(CategoryDTO request);

    /**
     * 删除指定的品类
     *
     * @param id - 品类ID
     */
    void deleteCategory(Long id);

    /**
     * 根据ID查询品类
     *
     * @param id - 品类ID
     * @return 品类
     */
    Category findCategoryById(Long id);

    /**
     * 根据ID查询父级品类
     *
     * @param id - 品类ID
     * @return 父级品类
     */
    Category findParentCategoryById(Long id);

    /**
     * 根据ID查询所有的祖先品类，查询结果包含自身且按照等级降序排序
     *
     * @param id - 品类ID
     * @return 祖先品类
     */
    List<Category> listParentsById(Long id);

    /**
     * 根据父品类ID分页查询子品类
     *
     * @param query 查询条件
     * @return 子品类
     */
    List<Category> listChildrenById(CategoryPageQuery query);

    /**
     * 根据品类级别分页查询品类
     *
     * @param query 查询条件
     * @return 品类
     */
    List<Category> listCategoriesByLevel(CategoryPageQuery query);
}
