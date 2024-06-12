package com.diligrp.assistant.product.service.impl;

import com.diligrp.assistant.product.dao.ProductCategoryDao;
import com.diligrp.assistant.product.domain.CategoryDTO;
import com.diligrp.assistant.product.domain.CategoryPageQuery;
import com.diligrp.assistant.product.exception.CategoryServiceException;
import com.diligrp.assistant.product.model.Category;
import com.diligrp.assistant.product.service.ProductCategoryService;
import com.diligrp.assistant.shared.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private ProductCategoryDao productCategoryDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long createCategory(CategoryDTO request) {
        LocalDateTime now = LocalDateTime.now();
        Category parent = findCategoryById(request.getParentId());
        Category category = Category.builder().parentId(request.getParentId()).name(request.getName())
            .alias(request.getAlias()).level(parent.getLevel() + 1).pyCode(request.getPyCode())
            .shortCode(request.getShortCode()).path(parent.getPath()).icon(request.getIcon()).state(1).version(0)
            .createdTime(now).modifiedTime(now).build();
        productCategoryDao.insertCategory(category);

        // 更新路径信息, 将自身ID添加到路径信息中
        String path = parent.getPath() + "," + category.getId();
        Category update = new Category();
        update.setId(category.getId());
        update.setPath(path);
        productCategoryDao.updateCategory(update);
        return category.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(CategoryDTO request) {
        LocalDateTime now = LocalDateTime.now();
        Category category = Category.builder().name(request.getName())
            .alias(request.getAlias()).pyCode(request.getPyCode()).shortCode(request.getShortCode())
            .icon(request.getIcon()).modifiedTime(now).build();
        category.setId(request.getId());
        if (productCategoryDao.updateCategory(category) == 0) {
            throw new CategoryServiceException(ErrorCode.OBJECT_NOT_FOUND, "品类不存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Long id) {
        LocalDateTime now = LocalDateTime.now();
        long children = productCategoryDao.countChildrenById(id);
        if (children > 0) {
            throw new CategoryServiceException(ErrorCode.OPERATION_NOT_ALLOWED, "该品类不能被删除: 存在子品类");
        }

        Category update = new Category();
        update.setId(id);
        update.setState(0);
        update.setModifiedTime(now);
        if (productCategoryDao.updateCategory(update) == 0) {
            throw new CategoryServiceException(ErrorCode.OBJECT_NOT_FOUND, "该品类删除失败: 品类不存在");
        }
    }

    @Override
    public Category findCategoryById(Long id) {
        return productCategoryDao.findCategoryById(id).orElseThrow(() ->
            new CategoryServiceException(ErrorCode.OBJECT_NOT_FOUND, "品类不存在"));
    }

    @Override
    public Category findParentCategoryById(Long id) {
        return productCategoryDao.findParentCategoryById(id).orElseThrow(() ->
            new CategoryServiceException(ErrorCode.OBJECT_NOT_FOUND, "品类不存在"));
    }

    @Override
    public List<Category> listParentsById(Long id) {
        Category self = findCategoryById(id);
        String path = self.getPath();

        if (Objects.nonNull(path)) {
            List<Long> ids = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(path, ",");

            while (tokenizer.hasMoreTokens()) {
                ids.add(Long.parseLong(tokenizer.nextToken()));
            }
            if (ids.isEmpty()) {
                ids.add(id);
            }

            return productCategoryDao.findCategoriesByIds(ids);
        } else {
            return Collections.singletonList(self);
        }
    }

    @Override
    public List<Category> listChildrenById(CategoryPageQuery query) {
        return productCategoryDao.listChildrenById(query);
    }

    @Override
    public List<Category> listCategoriesByLevel(CategoryPageQuery query) {
        return productCategoryDao.listCategoriesByLevel(query);
    }
}
